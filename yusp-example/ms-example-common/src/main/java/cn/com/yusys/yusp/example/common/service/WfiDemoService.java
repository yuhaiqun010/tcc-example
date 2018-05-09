package cn.com.yusys.yusp.example.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.com.yusys.yusp.commons.mapper.QueryModel;
import cn.com.yusys.yusp.example.common.domain.WfiDemo;
import cn.com.yusys.yusp.example.common.domain.WfiExceptionDemo;
import cn.com.yusys.yusp.example.common.repository.mapper.WfiDemoMapper;
import cn.com.yusys.yusp.example.common.repository.mapper.WfiExceptionDemoMapper;

@Service
public class WfiDemoService {

    @Autowired
    private WfiDemoMapper wfiDemoMapper;
    @Autowired
    private WfiExceptionDemoMapper wfiExceptionDemoMapper;
       
    public Boolean addWfiDemo(WfiDemo record) {
        int insert=wfiDemoMapper.insertSelective(record);
        return insert>0;
    }
    
    public List<WfiDemo> selectAllWfiDemo(QueryModel model){
    	PageHelper.startPage(model.getPage(), model.getSize());
    	List<WfiDemo> list=wfiDemoMapper.selectAllWfiDemo(model);
    	PageHelper.clearPage();
    	return list;
    }
    
    public Boolean updateWfiDemo(WfiDemo record) {
        int update=wfiDemoMapper.updateByPrimaryKeySelective(record);
        return update>0;
    }
    
    public Boolean insertWfiExceptionDemo(WfiExceptionDemo record) {
        int insert=wfiExceptionDemoMapper.insertSelective(record);
        return insert>0;
    }
    
    public List<WfiExceptionDemo> selectAllExceptionDemo(QueryModel model){
        PageHelper.startPage(model.getPage(), model.getSize());
        List<WfiExceptionDemo> list=wfiExceptionDemoMapper.selectAllExceptionDemo(model);
        PageHelper.clearPage();
        return list;
    }
    
    public Boolean deleteWfiExceptionDemo(String logicSeq) {
        int delete=wfiExceptionDemoMapper.deleteByPrimaryKey(logicSeq);
        return delete>0;
    }
}
