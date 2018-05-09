package cn.com.yusys.yusp.example.common.service.impl;

import cn.com.yusys.yusp.example.common.domain.ExampleDemo;
import cn.com.yusys.yusp.example.common.repository.mapper.ExampleDemoMapper;
import cn.com.yusys.yusp.example.common.service.CacheDemoService;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @项目名称：example
 * @类名称：CacheDemoServiceImpl
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
@Service
public class CacheDemoServiceImpl implements CacheDemoService {
    private final Logger log = LoggerFactory.getLogger(CacheDemoServiceImpl.class);
	
    @Autowired
	private ExampleDemoMapper mapper;
	
	@Override
	@CachePut(value = "WfiDemo", key = "#wfiDemo.logicSeq + 'wfiDemo'")
	public ExampleDemo insert(ExampleDemo wfiDemo) {
		mapper.insert(wfiDemo);
		log.debug("WfiDemo插入成功");
		return mapper.selectByPrimaryKey(wfiDemo);
	}
	
	@Override
	@Cacheable(value = "WfiDemo", key = "#wfiDemo.logicSeq + 'wfiDemo'")
	public ExampleDemo selectByPrimaryKey(ExampleDemo wfiDemo) {
		log.debug("WfiDemo查询成功");
		ExampleDemo wfiDemoT =  mapper.selectByPrimaryKey(wfiDemo);
		return wfiDemoT;
	}
	
	@Override
	@CachePut(value = "WfiDemo", key = "#wfiDemo.logicSeq + 'wfiDemo'")
	public ExampleDemo updateByPrimaryKey(ExampleDemo wfiDemo) {
		mapper.updateByPrimaryKeySelective(wfiDemo);
		log.debug("WfiDemo更新成功");
		return mapper.selectByPrimaryKey(wfiDemo);
	}
	
	@Override
	@CacheEvict(value = "WfiDemo", key = "#wfiDemo.logicSeq + 'wfiDemo'")
	public int deleteByPrimaryKey(ExampleDemo wfiDemo) {
		mapper.deleteByPrimaryKey(wfiDemo);
		log.debug("WfiDemo删除成功");
		return 0;
	}

	@Override
	@CacheEvict(value = "WfiDemo", key = "#wfiDemo.logicSeq + 'wfiDemo'")
	public void cleanCacheByPrimaryKey(ExampleDemo wfiDemo) {
		
	}
	

	@Override
	@Cacheable(value = "WfiDemo", key = "#wfiDemo.logicSeq + 'wfiDemo'")
	public ExampleDemo selectCacheByPrimaryKey(ExampleDemo wfiDemo) {
		return null;	
	}

}
