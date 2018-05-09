package cn.com.yusys.yusp.example.common.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;

import cn.com.yusys.yusp.commons.job.core.executor.XxlJobExecutor;
import io.github.jhipster.security.uaa.LoadBalancedResourceDetails;

/**
 * xxl-job config
 *
 * @author xuxueli 2017-04-28
 */
@Configuration
public class XxlJobConfig {
    private Logger logger = LoggerFactory.getLogger(XxlJobConfig.class);
    
    private final LoadBalancedResourceDetails loadBalancedResourceDetails;

    @Value("${xxl.job.admin.addresses}")
    private String adminAddresses;

    @Value("${xxl.job.executor.appname}")
    private String appName;

    @Value("${xxl.job.executor.ip}")
    private String ip;

    @Value("${xxl.job.executor.port}")
    private int port;

    @Value("${xxl.job.executor.logpath}")
    private String logPath;

    @Value("${xxl.job.executor.logretentiondays}")
    private int logRetentionDays;
    
    public XxlJobConfig(LoadBalancedResourceDetails loadBalancedResourceDetails) {
        this.loadBalancedResourceDetails = loadBalancedResourceDetails;
    }

    public OAuth2FeignRequestInterceptor getOAuth2RequestInterceptor() throws IOException {
        return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), loadBalancedResourceDetails);
    }

    @Bean(initMethod = "start", destroyMethod = "destroy")
    public XxlJobExecutor xxlJobExecutor() throws IOException {
        logger.info(">>>>>>>>>>> xxl-job config init.");
        XxlJobExecutor xxlJobExecutor = new XxlJobExecutor();
        xxlJobExecutor.setAdminAddresses(adminAddresses);
        xxlJobExecutor.setAppName(appName);
        xxlJobExecutor.setIp(ip);
        xxlJobExecutor.setPort(port);
        //xxlJobExecutor.setAccessToken(getOAuth2RequestInterceptor().getToken().getValue());
        xxlJobExecutor.setLogPath(logPath);
        xxlJobExecutor.setLogRetentionDays(logRetentionDays);

        return xxlJobExecutor;
    }

}