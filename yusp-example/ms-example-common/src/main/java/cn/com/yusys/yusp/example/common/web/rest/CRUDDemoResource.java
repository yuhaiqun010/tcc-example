package cn.com.yusys.yusp.example.common.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.com.yusys.yusp.commons.service.CommonService;
import cn.com.yusys.yusp.commons.web.rest.CommonResource;
import cn.com.yusys.yusp.example.common.domain.ExcelDemo;
import cn.com.yusys.yusp.example.common.service.ExcelDemoService;

/**
 * 
 * @项目名称:ms-example-common
 * @类名称:CRUDDemoResource
 * @类描述:增删改查示例
 * @功能描述:提供增删改查操作示例
 * @创建人:wangxx7@yusys.com.cn
 * @创建时间:2018年5月2日
 * @修改备注:
 * @修改日期		修改人员		修改原因
 * --------    --------		----------------------------------------
 * @version 1.0.0
 * @Copyright (c) 2018宇信科技-版权所有
 */
@RestController
@RequestMapping("/api/crud")
public class CRUDDemoResource extends CommonResource<ExcelDemo, String> {
	
	@Autowired
	private ExcelDemoService demoService;

	/**
	 * 继承CommonResource并提供Domain和Service之后，即完成增删改查功能
	 */
	@Override
	protected CommonService getCommonService() {
		return demoService;
	}
	
	/**
	 * 
	 * @方法名称:pressureTest
	 * @方法描述:操作为空，接收请求后直接响应（用于压力测试）
	 * @参数与返回说明:
	 * @算法描述:
	 */
	@GetMapping("/pressureTest")
	public String pressureTest() {
		return "静态压测数据返回...";
	}
}
