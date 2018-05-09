package cn.com.yusys.yusp.example.common.service.impl;

import org.springframework.stereotype.Service;
import cn.com.yusys.yusp.commons.job.core.biz.model.ReturnT;
import cn.com.yusys.yusp.commons.job.core.handler.IJobHandler;
import cn.com.yusys.yusp.commons.job.core.handler.annotation.JobHandler;
/**
 * @项目名称：example
 * @类名称：JobDemoServiceImpl
 * @类描述 ： 微服务开发示例：定时任务
 * @功能描述:
 * @创建人：wangyf10@yusys.com.cn
 * @创建时间：2018-04-17 10:31
 * @修改备注：
 * @修改日期		修改人员		修改原因
 * --------    --------		----------------------------------------
 * @version 1.0.0
 * @Copyright (c) 2018宇信科技-版权所有
 */
@JobHandler(value="exampleJobId") // 全球唯一
@Service
public class JobDemoServiceImpl extends IJobHandler{
	

	@Override
	public ReturnT<String> execute(String param) throws Exception {
		System.out.println("定时任务执行了-------"+System.currentTimeMillis());
		return ReturnT.SUCCESS;
	}
}
