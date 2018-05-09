package cn.com.yusys.yusp.example.common.service;

import java.util.List;
import java.util.Map;

import cn.com.yusys.yusp.commons.mapper.QueryModel;
import cn.com.yusys.yusp.commons.service.CommonService;

public abstract class ExampleDemoService extends CommonService{
	public abstract List<Map<String, Object>> dataAuthDemo(QueryModel model);
}
