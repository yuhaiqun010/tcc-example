package cn.com.yusys.yusp.example.common.web.rest;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.com.yusys.yusp.commons.web.rest.dto.ResultDto;
import cn.com.yusys.yusp.example.common.domain.ExampleDemo;
import cn.com.yusys.yusp.example.common.service.CacheDemoService;
/**
 * @项目名称：example
 * @类名称：CacheDemoResource
 * @类描述 ： 缓存示例
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
@RequestMapping("/api/cachedemo")
public class CacheDemoResource {
	@Autowired
	private CacheDemoService service;  
    
	
	@Autowired
	private RedisTemplate stringRedisTemplate;
	
    /**
	 * 插入
	 * @param wfiDemo
	 * @return
	 */
	@PostMapping(value = "/insert")
	public ResultDto<ExampleDemo> insert(ExampleDemo wfiDemo){
		wfiDemo.setLogicSeq(getUUID());
		return new ResultDto(service.insert(wfiDemo));
	}
	
	/**
	 * 根据主键查询
	 * @param wfiDemo
	 * @return
	 */
	@GetMapping(value = "/select")
	public ResultDto<ExampleDemo> selectByPrimaryKey(ExampleDemo wfiDemo){
		
		/**
		 * 此段代码完全为了前端演示效果，请忽略
		 */
		ExampleDemo wfiDemoTemp = service.selectCacheByPrimaryKey(wfiDemo);
		
		
		ExampleDemo wfiDemoT = service.selectByPrimaryKey(wfiDemo);
		
		/**
		 * 此段代码完全为了前端演示效果，请忽略
		 */
		if(wfiDemoTemp == null){
			wfiDemoT.setCustId(wfiDemoT.getCustName()+" (缓存中不存在，从数据库获取的)");
		}else{
			wfiDemoT.setCustId(wfiDemoT.getCustName()+" (从缓存获取的)");
		}	
		
		return new ResultDto(wfiDemoT);
	}

	/**
	 * 根据主键更新
	 * @param wfiDemo
	 * @return
	 */
	@PostMapping(value = "/update")
	public ResultDto<ExampleDemo> updateByPrimaryKey(ExampleDemo wfiDemo){
		return new ResultDto(service.updateByPrimaryKey(wfiDemo));
	}
	
	/**
	 * 根据主键删除
	 * @param wfiDemo
	 * @return
	 */
	@GetMapping(value = "/delete")
	public ResultDto<Integer> deleteByPrimaryKey(ExampleDemo wfiDemo){
		return new ResultDto(service.deleteByPrimaryKey(wfiDemo));
	}
	
	/**
	 * 根据主键仅仅清除缓存
	 * @param wfiDemo
	 * @return
	 */
	@GetMapping(value = "/clean")
	public ResultDto<Integer> cleanCacheByPrimaryKey(ExampleDemo wfiDemo){
		service.cleanCacheByPrimaryKey(wfiDemo);
		return new ResultDto(0);
	}
	
	/**
	 * @方法名称: getUUID
	 * @方法描述: 主键生成器
	 * @参数与返回说明:
	 * @算法描述:
	 */
	private String getUUID() {
		return UUID.randomUUID().toString().toLowerCase().replace("-", "");
	}
	
}
