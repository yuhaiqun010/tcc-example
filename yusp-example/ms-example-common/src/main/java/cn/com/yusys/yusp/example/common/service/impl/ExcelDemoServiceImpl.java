package cn.com.yusys.yusp.example.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.yusys.yusp.commons.mapper.CommonMapper;
import cn.com.yusys.yusp.example.common.repository.mapper.ExcelDemoMapper;
import cn.com.yusys.yusp.example.common.service.ExcelDemoService;
@Service
public class ExcelDemoServiceImpl extends ExcelDemoService{

    @Autowired
    private ExcelDemoMapper excelDemoMapper;
    
    @Override
    protected CommonMapper getMapper() {
        return excelDemoMapper;
    }
}
