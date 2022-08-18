package cn.xsdzq.platform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 远程调用配置类
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate  restTemplate(){
        return new RestTemplate();
    }
}
