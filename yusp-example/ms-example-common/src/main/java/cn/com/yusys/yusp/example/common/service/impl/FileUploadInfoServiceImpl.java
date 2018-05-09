package cn.com.yusys.yusp.example.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.yusys.yusp.commons.mapper.CommonMapper;
import cn.com.yusys.yusp.example.common.repository.mapper.AdminFileUploadInfoMapper;
import cn.com.yusys.yusp.example.common.service.FileUploadInfoService;
@Service
public class FileUploadInfoServiceImpl extends FileUploadInfoService{

    @Autowired
    private AdminFileUploadInfoMapper adminFileUploadInfoMapper;
    
    @Override
    protected CommonMapper getMapper() {
        return adminFileUploadInfoMapper;
    }
}
