package cn.com.yusys.yusp.example.common.web.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.com.yusys.yusp.commons.mapper.QueryModel;
import cn.com.yusys.yusp.commons.service.CommonService;
import cn.com.yusys.yusp.commons.web.rest.CommonResource;
import cn.com.yusys.yusp.commons.web.rest.dto.ResultDto;
import cn.com.yusys.yusp.example.common.domain.ExampleDemo;
import cn.com.yusys.yusp.example.common.service.ExampleDemoService;
/**
 * @项目名称：example
 * @类名称：WfiDemoResource
 * @类描述 ： 数据持久化示例、数据权限示例
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
@RequestMapping("/api/wfidemo")
public class ExampleDemoResource extends CommonResource<ExampleDemo, String>{
	@Autowired
	private ExampleDemoService service;
	
	/**
	 * 单表增删查改示例
	 */
	@Override
	protected CommonService getCommonService() {
		 return this.service;
	}
	
	/**
	 * 数据权限示例
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/dataAuthDemo")
    public ResultDto<List<Map<String, Object>>> getAllUserSubscribeInfo(QueryModel model) {
        List<Map<String, Object>> subscribes = service.dataAuthDemo(model);    
        return new ResultDto(subscribes);
    };
    
    
}
