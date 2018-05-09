package cn.com.yusys.yusp.example.common.clientDemo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;

import cn.com.yusys.yusp.echain.client.message.InstanceMessage;
import cn.com.yusys.yusp.echain.client.message.InstanceMessageProcessor;
import cn.com.yusys.yusp.echain.client.message.InstanceMessageProcessorFactory;
import cn.com.yusys.yusp.example.common.domain.WfiExceptionDemo;
import cn.com.yusys.yusp.example.common.service.WfiDemoService;

public class EchainMessageListener implements ChannelAwareMessageListener {
    private final Logger log = LoggerFactory.getLogger(EchainMessageListener.class);
    @Autowired
    private InstanceMessageProcessorFactory processorFactory;
    @Autowired
    private WfiDemoService echainJoinDemoService;
    
    private ObjectMapper mapper = new ObjectMapper();
    
    public EchainMessageListener() {
        super();
    }

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long tag = message.getMessageProperties().getDeliveryTag();
        byte[] body = message.getBody();
        String msgStr = new String(body);   
        List<InstanceMessageProcessor> processors = processorFactory.getProcessors(msgStr);
        try {
            for (InstanceMessageProcessor processor : processors) {
                processor.process(msgStr);
            }
            channel.basicAck(tag, false); // 消费成功
        } catch (Exception e) {
            log.error(e.toString(), e);
            channel.basicNack(tag, false, false);// 消费失败并删除
            try {
                //保存至异常事务表
                InstanceMessage instanceMessage=mapper.readValue(msgStr,InstanceMessage.class);
                WfiExceptionDemo record=new WfiExceptionDemo();
                record.setLogicSeq(instanceMessage.getInstanceInfo().getBizSeqNo());
                record.setInstanceId(instanceMessage.getInstanceInfo().getInstanceId());
                record.setNodeId(instanceMessage.getInstanceInfo().getNodeName());
                record.setNodeUser(instanceMessage.getInstanceInfo().getCurrentNodeUser());
                record.setCurrentAction(instanceMessage.getInstanceInfo().getAppId());
                record.setCustId(instanceMessage.getInstanceInfo().getCustId());
                record.setWfSign(instanceMessage.getInstanceInfo().getWfSign());
                record.setWfName(instanceMessage.getInstanceInfo().getWfName());
                record.setPreNodeName(instanceMessage.getInstanceInfo().getPreNodeName());
                record.setPreUser(instanceMessage.getInstanceInfo().getPreUser());
                record.setNodeStartTime(instanceMessage.getInstanceInfo().getNodeStartTime());
                echainJoinDemoService.insertWfiExceptionDemo(record); 
            }catch(Exception ea) {
                ea.printStackTrace();
            }
           
            //channel.basicNack(tag, false, true);// 消费失败并重新进入队列
        }
    }

}
