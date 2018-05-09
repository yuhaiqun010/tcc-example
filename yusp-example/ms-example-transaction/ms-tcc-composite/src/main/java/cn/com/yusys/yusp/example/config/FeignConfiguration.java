package cn.com.yusys.yusp.example.config;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "cn.com.yusys.yusp.example.feign")
public class FeignConfiguration {

}
