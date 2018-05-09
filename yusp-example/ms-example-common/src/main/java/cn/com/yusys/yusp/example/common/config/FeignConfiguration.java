package cn.com.yusys.yusp.example.common.config;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;

@Configuration
@EnableFeignClients(basePackages = { "cn.com.yusys.yusp.admin","cn.com.yusys.yusp.uaa.client","cn.com.yusys.api.client.sequence"})
public class FeignConfiguration {
	@Bean
	Logger.Level feginLogLevel(){
		return Logger.Level.FULL;
	}
}
