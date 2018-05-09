package cn.com.yusys.yusp.example.common.service;

import cn.com.yusys.yusp.example.common.domain.ExampleDemo;

/**
 * @version 1.0.0
 * @项目名称: example
 * @类名称: CacheDemoService
 * @类描述: 缓存操作示例
 * @功能描述:
 * @创建人: wangyf10@yusys.com.cn
 * @创建时间: 2018-01-31 13:57
 * @修改备注:
 * @修改记录: 修改时间    修改人员    修改原因
 * -------------------------------------------------------------
 * @Copyright (c) 2017宇信科技-版权所有
 */
public interface CacheDemoService {
	
	/**
	 * 插入
	 * @param wfiDemo
	 * @return
	 */
	public ExampleDemo insert(ExampleDemo wfiDemo);
	
	/**
	 * 根据主键查询, 先从重缓存中取，不存在再从数据库取
	 * @param wfiDemo
	 * @return
	 */
	public ExampleDemo selectByPrimaryKey(ExampleDemo wfiDemo);

	/**
	 * 根据主键更新
	 * @param wfiDemo
	 * @return
	 */
	public ExampleDemo updateByPrimaryKey(ExampleDemo wfiDemo);
	
	/**
	 * 根据主键删除
	 * @param wfiDemo
	 * @return
	 */
	public int deleteByPrimaryKey(ExampleDemo wfiDemo);
	
	/**
	 * 根据主键仅仅清除缓存
	 * @param wfiDemo
	 * @return
	 */
	public void cleanCacheByPrimaryKey(ExampleDemo wfiDemo);
	
	/**
	 * 仅仅重缓存中取
	 * @param wfiDemo
	 * @return 
	 */
	public ExampleDemo selectCacheByPrimaryKey(ExampleDemo wfiDemo);
	
	
}
