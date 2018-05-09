package cn.com.yusys.yusp.example.common.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.com.yusys.yusp.commons.mapper.CommonMapper;
import cn.com.yusys.yusp.commons.mapper.QueryModel;
import cn.com.yusys.yusp.example.common.repository.mapper.ExampleDemoMapper;
import cn.com.yusys.yusp.example.common.service.ExampleDemoService;

@Service
public class ExampleDemoServiceImpl extends ExampleDemoService {
	private Logger logger = LoggerFactory.getLogger(ExampleDemoServiceImpl.class);
	@Autowired
	private ExampleDemoMapper mapper;
	
	@Override
	protected CommonMapper<?> getMapper() {
		return this.mapper;
	}
	
	public List<Map<String, Object>> dataAuthDemo(QueryModel model){
		return mapper.dataAuthDemo(model);
	}
}
