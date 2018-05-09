package cn.com.yusys.yusp.example.common.web.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.itextpdf.text.PageSize;

import cn.com.yusys.yusp.commons.view.AbstractPdfView;
import cn.com.yusys.yusp.commons.view.PdfView;
/**
 * @项目名称：example
 * @类名称：PdfDemoResource
 * @类描述 ： 微服务开发示例：pdf预览、下载示例
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
@RequestMapping("/api/pdfdemo")
public class PdfDemoResource {

    @RequestMapping(value = "/gethtml", method = RequestMethod.GET)
    public ModelAndView gethtml() {
        Map<String, Object> pdfData = buildPdfData();
        ModelAndView modelAndView = new ModelAndView("pdftemps/pdfViewDemo");
        modelAndView.addAllObjects(pdfData);
        return modelAndView;
    }

    @RequestMapping(value = "/getpdf", method = RequestMethod.GET)
    public ModelAndView getpdf(@RequestParam(name = "online", defaultValue = "0") String online) {
        // pdf数据
        Map<String, Object> pdfData = buildPdfData();

        AbstractPdfView pdfView = new PdfView();
        // 模板路径
        pdfView.setTemplateName("pdftemps/pdfViewDemo"); 
        if ("1".equals(online)) {// 是否在线查看
            pdfView.setIsOnlineBrowse(true);
        } else {
            pdfView.setIsOnlineBrowse(false);
        }
        
        // 下载后文件名
        pdfView.setDownloadFileName("pdfdemo.pdf");
        // pdf纸张大小
        pdfView.setPageSize(PageSize.A4);

        return new ModelAndView(pdfView, pdfData);
    }

    private Map<String, Object> buildPdfData() {
        Map<String, Object> pdfData = new HashMap<>();
        pdfData.put("A", "王亚飞");
        pdfData.put("Amobile", "13654837902");
        pdfData.put("B", "小红");
        pdfData.put("Bmobile", "17186281150");
        List<Map<String, String>> usrList = new ArrayList<Map<String, String>>();
        for (int i = 0; i < 2; i++) {
            Map<String, String> usr = new HashMap<String, String>();
            usr.put("a", "201" + i+"-10-2"+ i);
            usr.put("b", "201" + i+"-10-2"+ (i+2));
            usr.put("c", "100" + i);
            usr.put("d", "1" + i);
            usr.put("e", "企业贷");
            usr.put("f", "有");
            usr.put("g", "良好");
            usrList.add(usr);
        }
        pdfData.put("AList", usrList);
        pdfData.put("AS1", "2001");
        pdfData.put("AS2", "21");
        
        
        pdfData.put("A1", "宇信科技集团股份有限公司");
        pdfData.put("A2", "2");
        pdfData.put("A3", "17186281150");
        pdfData.put("A4", "5000");
        pdfData.put("A5", "3");
        pdfData.put("A6", "2");
        pdfData.put("A7", "软件研发");
        pdfData.put("A8", "2003-11-25");
        pdfData.put("A9", "5000万");
        pdfData.put("A10", "北京市朝阳区酒仙桥电子城研发中心A5");
        pdfData.put("A11", "北京市朝阳区酒仙桥电子城研发中心A5,3楼");
        List<Map<String, String>> a12 = new ArrayList<Map<String, String>>();
        for (int i = 0; i < 3; i++) {
            Map<String, String> usr = new HashMap<String, String>();
            usr.put("a", "总经理");
            usr.put("b", ""+ (i+12));
            usr.put("c", "" + (i%2+1));
            usr.put("d", "王菲" + i);
            usr.put("e", ""+(i+42));
            usr.put("f", "" + (i%2+1));
            usr.put("g", "376"+i+"1876522919"+i);
            usr.put("h", "17"+i+"67"+(i+1)+"81"+i+"2"+i+"2");
            usr.put("i", "保密");
            a12.add(usr);
        }
        pdfData.put("a12", a12);
        return pdfData;
    }
}
