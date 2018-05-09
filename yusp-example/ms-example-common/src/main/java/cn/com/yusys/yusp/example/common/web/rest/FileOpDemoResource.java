package cn.com.yusys.yusp.example.common.web.rest;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.com.yusys.yusp.commons.config.ApplicationProperties;
import cn.com.yusys.yusp.commons.file.FileManagementCilent;
import cn.com.yusys.yusp.commons.file.FileManagementCilentFactory;
import cn.com.yusys.yusp.commons.service.CommonService;
import cn.com.yusys.yusp.commons.web.rest.CommonResource;
import cn.com.yusys.yusp.commons.web.rest.dto.ResultDto;
import cn.com.yusys.yusp.example.common.domain.AdminFileUploadInfo;
import cn.com.yusys.yusp.example.common.service.FileUploadInfoService;

/**
 * @项目名称：example
 * @类名称：FileOpDemoResource
 * @类描述 ： 微服务开发示例：文件上传、下载、删除示例
 * @功能描述:
 * @创建人：wangyf10@yusys.com.cn
 * @创建时间：2018-04-17 10:31
 * @修改备注：
 * @修改日期		修改人员		修改原因
 * --------    --------		----------------------------------------
 * @version 1.0.0
 * @Copyright (c) 2018宇信科技-版权所有
 */
@RestController
@RequestMapping("/api/fileop")
public class FileOpDemoResource extends CommonResource<AdminFileUploadInfo, String>{
    private Logger logger = LoggerFactory.getLogger(FileOpDemoResource.class);
    
    @Autowired
    FileUploadInfoService service;
    
    @Autowired
    ApplicationProperties applicationProperties;
    
    @Autowired
    FileManagementCilentFactory fileManagementCilentFactory;
  
   
    // 文件存储类型
    @Value("${application.fileStorageType}")
    private String fileStorageType;
    
    // 文件存储类型为磁盘时，文件路径
    @Value("${application.localDiskPath}")
    private String localDiskPath;
    
    // 文件存储类型为fastdfs时，track服务器的地址
    @Value("${application.fastdfsTrackIp}")
    private String fastdfsTrackIp;
    
    @Override
    protected CommonService getCommonService() {
        // TODO Auto-generated method stub
        return this.service;
    }

    @PostMapping(value = "/uploadfile")
    public  ResultDto<AdminFileUploadInfo> uploadFile(MultipartFile file,AdminFileUploadInfo adminFileUploadInfo,HttpServletRequest httpServletRequest,HttpServletResponse res) {
   
        String fileId = null;
        try {
            // 支持跨域
            // res.setHeader("Access-Control-Allow-Origin", "*");
            logger.debug("编码->"+httpServletRequest.getCharacterEncoding());
            if (httpServletRequest.getCharacterEncoding() == null) {
            	//设置编码格式
                httpServletRequest.setCharacterEncoding("UTF-8");
            }
            // 获取文件上传名称
            String fileName = file.getOriginalFilename();
            // String fileName = new String(file.getOriginalFilename().getBytes("GBK"), "UTF-8");
            // String fileName = new String(file.getOriginalFilename().getBytes("UTF-8"), "UTF-8");
            adminFileUploadInfo.setFileName(fileName);
            
            long size = file.getSize()/1024;// KB
            adminFileUploadInfo.setFileSize(new BigDecimal(size));
            
            String fileNameExt = fileName.substring(fileName.lastIndexOf(".") + 1);
            adminFileUploadInfo.setExtName(fileNameExt);
            
            //最近跟新时间
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            adminFileUploadInfo.setUploadTime(df.format(new Date()));
            
            // 上传
            FileManagementCilent fastDFSClient = fileManagementCilentFactory.getFileManagementCilent();       
            fastDFSClient.initConnection();
            
            fileId = fastDFSClient.uploadFile(file.getBytes(), fileNameExt, null);
            fastDFSClient.closeConnection();
            logger.debug("上传完成后的文件路径->"+fileId);
            
            adminFileUploadInfo.setFilePath(fileId);
            service.insertSelective(adminFileUploadInfo);          
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new  ResultDto<AdminFileUploadInfo>(adminFileUploadInfo);
    }

    @GetMapping(value = "/downloadfile")
    public void downloadFile(String fileId, HttpServletResponse res) {
              
        String fileName = fileId.substring(fileId.lastIndexOf("/")+1);
        res.setHeader("content-type", "application/octet-stream");
        //res.setHeader("Access-Control-Allow-Origin", "*");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename=" + fileName);

        OutputStream os = null;
        try {
            os = res.getOutputStream();
            FileManagementCilent fastDFSClient = fileManagementCilentFactory.getFileManagementCilent();    
            fastDFSClient.initConnection();
            byte[] content = fastDFSClient.downloadFile(fileId);
            fastDFSClient.closeConnection();
            os.write(content);
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @GetMapping(value = "/deletefiles")
    public  ResultDto<String> deleteFiles(String  fileIds) {
    	ResultDto<String> resultDto = new ResultDto<String>("");
    	resultDto.setCode(0);
    	resultDto.setMessage("文件删除成功");
        try {
        	String [] files = fileIds.split(",");
        	FileManagementCilent fastDFSClient = fileManagementCilentFactory.getFileManagementCilent();       
            fastDFSClient.initConnection();
        	for(String fileId:files){
        		int result = fastDFSClient.deleteFile(fileId);
        		if(result!=0){
        			logger.debug("文件删除失败->"+fileId);
        			resultDto.setCode(1);
        			resultDto.setMessage("文件删除失败->"+fileId);
        			return resultDto;
        		}else{
        			AdminFileUploadInfo record = new AdminFileUploadInfo();
        			record.setFilePath(fileId);
        			service.delete(record);
        		}        		
        	}
            fastDFSClient.closeConnection();      
        } catch (Exception e) {
            e.printStackTrace();
            resultDto.setCode(1);
			resultDto.setMessage("文件删除失败->"+e.getMessage());
			return resultDto;
        }
        return resultDto;
    }
    
}
