package cn.com.yusys.yusp.example.common.repository.mapper;

import java.util.List;

import cn.com.yusys.yusp.commons.mapper.CommonMapper;
import cn.com.yusys.yusp.commons.mapper.QueryModel;
import cn.com.yusys.yusp.example.common.domain.WfiDemo;

public interface WfiDemoMapper extends CommonMapper<WfiDemo> {
    
    List<WfiDemo> selectAllWfiDemo(QueryModel model);
    
    int insertSelective(WfiDemo record);
    
    int updateByPrimaryKeySelective(WfiDemo record);
}