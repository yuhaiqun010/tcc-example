package cn.com.yusys.yusp.example.common.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.com.yusys.yusp.echain.client.consts.Consts;
import cn.com.yusys.yusp.example.common.clientDemo.EchainMessageListener;

@Configuration
public class EchainConfiguration {

    @Configuration
    public class QueueInit {
        @Bean
        public Queue echainInstanceQueue() {
            return new Queue(Consts.INSTANCE_MESSAGE_QUEUE_NAME);
        }       

        @Bean
        public EchainMessageListener echainMessageListener() {
            return new EchainMessageListener();
        }

        @Bean
        public SimpleMessageListenerContainer messageContainer(ConnectionFactory connectionFactory) {
            SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
            container.setQueues(echainInstanceQueue());
            container.setAcknowledgeMode(AcknowledgeMode.MANUAL); // 设置确认模式手工确认
            container.setMessageListener(echainMessageListener());
            return container;
        }
    }
}
