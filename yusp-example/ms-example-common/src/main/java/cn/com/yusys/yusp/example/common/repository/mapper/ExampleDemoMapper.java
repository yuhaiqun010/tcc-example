package cn.com.yusys.yusp.example.common.repository.mapper;

import java.util.List;
import java.util.Map;

import cn.com.yusys.yusp.commons.mapper.CommonMapper;
import cn.com.yusys.yusp.commons.mapper.QueryModel;
import cn.com.yusys.yusp.example.common.domain.ExampleDemo;

public interface ExampleDemoMapper extends CommonMapper<ExampleDemo> {
	
	public List<Map<String, Object>> dataAuthDemo(QueryModel model);
}