package cn.com.yusys.yusp.example.common.repository.mapper;

import java.util.List;

import cn.com.yusys.yusp.commons.mapper.CommonMapper;
import cn.com.yusys.yusp.commons.mapper.QueryModel;
import cn.com.yusys.yusp.example.common.domain.WfiExceptionDemo;

public interface WfiExceptionDemoMapper extends CommonMapper<WfiExceptionDemo> {
    
    int insertSelective(WfiExceptionDemo record);
    
    List<WfiExceptionDemo> selectAllExceptionDemo(QueryModel model);
}