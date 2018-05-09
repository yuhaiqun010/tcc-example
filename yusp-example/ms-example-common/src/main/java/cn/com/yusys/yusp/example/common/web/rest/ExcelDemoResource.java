package cn.com.yusys.yusp.example.common.web.rest;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;

import cn.com.yusys.yusp.commons.excel.ExcelImportExportUtil;
import cn.com.yusys.yusp.commons.excel.ExcelTaskManager;
import cn.com.yusys.yusp.commons.file.FileManagementCilent;
import cn.com.yusys.yusp.commons.file.FileManagementCilentFactory;
import cn.com.yusys.yusp.commons.mapper.QueryModel;
import cn.com.yusys.yusp.commons.web.rest.dto.ResultDto;
import cn.com.yusys.yusp.example.common.domain.ExcelDemo;
import cn.com.yusys.yusp.example.common.service.ExcelDemoService;

/**
 *
 * @项目名称:yusp-admin
 * @类名称:AdminExcelIOResource
 * @类描述: Excel导入导出示例
 * @功能描述:
 * @创建人:wangxx7@yusys.com.cn
 * @创建时间:2018年1月8日
 * @修改备注:
 * @修改日期		修改人员		修改原因
 * --------    --------		----------------------------------------
 * @version 1.0.0
 * @Copyright (c) 2018宇信科技-版权所有
 */
@RestController
@RequestMapping("/api/excel")
public class ExcelDemoResource {

    private Logger logger = LoggerFactory.getLogger(ExcelDemoResource.class);

    @Autowired
    ExcelDemoService excelDemoService;
    
    @Autowired
    ExcelTaskManager excelTaskManager;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    FileManagementCilentFactory fileManagementCilentFactory;
   
    /**
    *
    * @方法名称:queryList
    * @方法描述: 普通列表查询
    * @参数与返回说明:
    * @算法描述:
    */
	@GetMapping("/queryList")
	public ResultDto<List<Object>> queryList(QueryModel queryModel) {		
		PageHelper.startPage(queryModel.getPage(), queryModel.getSize());
		List<Object> result = excelDemoService.selectByModel(queryModel);
		PageHelper.clearPage();		
		return new ResultDto<List<Object>>(result);
	}
    /**
     *
     * @方法名称:exportExcel
     * @方法描述: 导出Excel
     * @参数与返回说明:
     * @算法描述:
     */
    @GetMapping("/export")
    public String exportExcel(Integer fetchSize, QueryModel queryModel) {
        String sqlId = "selectExcelDemo";
        String pojoClassName = ExcelDemo.class.getName();
        logger.info("导出Excel");
        // 新增异步导出任务
        String taskId = excelTaskManager.addExcelExportTask(sqlId, fetchSize, queryModel, pojoClassName);
        JSONObject jo = new JSONObject();
        try {
			jo.put("taskId", taskId);
			jo.put("taskType", "export");
		} catch (JSONException e) {
			e.printStackTrace();
		}
        return jo.toString();
    }

    /**
     *
     * @方法名称:importExcelFile
     * @方法描述: 导入Excel（文件上传和新增导入任务为一体）
     * @参数与返回说明:
     * @算法描述:
     */
    @PostMapping("/import")
    public String importExcelFile(@RequestParam("file") MultipartFile multipartFile){
        String sqlId = "insertExcelDemo";
        String pojoClassName = ExcelDemo.class.getName();
        // 新增异步导入任务
        String taskId = excelTaskManager.addExcelImportTask(multipartFile, pojoClassName, sqlId);
        JSONObject jo = new JSONObject();
        try {
			jo.put("taskId", taskId);
			jo.put("taskType", "import");
		} catch (JSONException e) {
			e.printStackTrace();
		}
        return jo.toString();
    }

    /**
     *
     * @方法名称:exportTemplate
     * @方法描述: 导出excel模板
     * @参数与返回说明: fileName可选，以.xlsx结尾
     * @算法描述:
     */
    @GetMapping("/exportTemplate")
    public void exportTemplate(String fileName, HttpServletResponse response) {
        SXSSFWorkbook wb = null;
        OutputStream os = null;
        // 指定导出的模板文件名
        if(StringUtils.isEmpty(fileName)){
            fileName = "导入数据模板.xlsx";
        }else if(!fileName.endsWith(ExcelTaskManager.EXCEL_POSTFIX)){
            fileName = fileName + ExcelTaskManager.EXCEL_POSTFIX;
        }
        try {
            // 生成WorkBook文件
            wb = ExcelImportExportUtil.exportTemplate(ExcelDemo.class);

            // 写出文件
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-disposition", "attachment; filename=" + new String((fileName).getBytes(), "iso-8859-1"));
            os = response.getOutputStream();
            wb.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != wb) {
                    wb.dispose();
                    wb.close();
                }
                if (null != os) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 以下两个为公共接口，其他业务开发可以直接通过taskId来调用
    // 以下两个为公共接口，其他业务开发可以直接通过taskId来调用
    // 以下两个为公共接口，其他业务开发可以直接通过taskId来调用

    /**
     *
     * @方法名称:download
     * @方法描述: 下载导出的文件
     * @参数与返回说明:
     * @算法描述:
     */
    @GetMapping("/download")
    public void download(String taskId, HttpServletResponse response){
        OutputStream os = null;
        FileManagementCilent fastDFSClient = fileManagementCilentFactory.getFileManagementCilent();
        try {
            // 通过TaskId从Redis中拿到任务执行信息
            JSONObject jo = ExcelImportExportUtil.getRedisTaskInfo(taskId, stringRedisTemplate);
            if (null !=  jo) {
                Integer percent = jo.getInt(ExcelTaskManager.REDIS_PERCENT_KEY);
                // 可以下载进度为100%的文件
                if(100 == percent){
                    String fileName = jo.getString(ExcelTaskManager.REDIS_FILENAME_KEY).toString();
                    String fileUuid = jo.get(ExcelTaskManager.REDIS_FASTDFS_KEY).toString();
                    if(StringUtils.isEmpty(fileUuid)){
                        logger.error(ExcelTaskManager.REDIS_FASTDFS_KEY + " is null !");
                    }

                    // 获取文件
                    fastDFSClient.initConnection();
                    byte[] downloadFile = fastDFSClient.downloadFile(fileUuid);

                    // 写出文件
                    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                    response.setHeader("Content-disposition", "attachment; filename=" + new String((fileName).getBytes(), "iso-8859-1"));
                    os = response.getOutputStream();
                    os.write(downloadFile);
                    os.flush();
//                    fastDFSClient.deleteFile(fileUuid);
                }else{
                    logger.error(ExcelTaskManager.REDIS_PERCENT_KEY + " is not 100 !");
                }
            } else {
            	logger.error("TaskId = " + taskId + ", RedisTaskInfo is not null !");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            fastDFSClient.closeConnection();
            if(os!=null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * 
     * @方法名称:queryInfo
     * @方法描述: 查询导出进度
     * @参数与返回说明:数据示例{"taskType":"import","fileName":"ExcelDemo导出.xlsx","percent":100}，其中当percent=-1时表示操作出错
     * @算法描述:
     */
    @GetMapping("/queryInfo") 
    public String queryInfo(@RequestParam("taskId") String taskId ) {
        JSONObject jo = ExcelImportExportUtil.getRedisTaskInfo(taskId, stringRedisTemplate);
        if(null == jo){
            return "failure";
        }
        // 此处可随需求增加返回其他参数
        return jo.toString(); 
    }
}
