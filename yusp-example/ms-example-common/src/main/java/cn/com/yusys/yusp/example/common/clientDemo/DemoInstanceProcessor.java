package cn.com.yusys.yusp.example.common.clientDemo;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.com.yusys.yusp.echain.client.message.InstanceMessage;
import cn.com.yusys.yusp.echain.client.message.InstanceMessageProcessor;
import cn.com.yusys.yusp.example.common.domain.WfiDemo;
import cn.com.yusys.yusp.example.common.service.WfiDemoService;


/**
 * 
 * @项目名称: yusp-echain-server
 * @类名称: DemoInstanceProcessor
 * @类描述: 示于演示流程提交成功后，业务表更新状态(成功/失败),根据flag判断是否失败，失败保存到异常队列中
 * @功能描述: 
 * @创建人: yuhq
 * @创建时间: 2018-04-27 10:18
 * @修改备注: 
 * @修改记录: 修改时间    修改人员    修改原因
 * -------------------------------------------------------------
 * @version 1.0.0
 * @Copyright (c) 2018宇信科技-版权所有
 */
@Component
public class DemoInstanceProcessor implements InstanceMessageProcessor {

	@Autowired
	private WfiDemoService echainJoinDemoService;

	private ObjectMapper mapper = new ObjectMapper();

	@Override
	public boolean should(String message) {
		return true;
	}

	@Override
	public int order() {
		return 0;
	}
	
	@Override
	public void process(String message) throws Exception {
		System.out.println("Echain客户端Queue消息: " + message);
		InstanceMessage instanceMessage = mapper.readValue(message, InstanceMessage.class);
		WfiDemo record = new WfiDemo();
		record.setLogicSeq(instanceMessage.getInstanceInfo().getBizSeqNo());
		record.setInstanceId(instanceMessage.getInstanceInfo().getInstanceId());
		record.setNodeId(instanceMessage.getInstanceInfo().getNodeName());
		record.setNodeUser(instanceMessage.getInstanceInfo().getCurrentNodeUser());
		Map<String, String> map = (Map<String, String>) instanceMessage.getInstanceInfo().getParamMap()
				.get("getNodeFormData");
		if (!"init".equals(instanceMessage.getType())) {
			if ("1".equals(map.get("flag"))) {
				throw new Exception("业务处理失败，进入事务异常队列...");
			} else {
				echainJoinDemoService.updateWfiDemo(record); // 修改实例信息
			}
		}
	}

}
