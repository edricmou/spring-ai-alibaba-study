package com.edricmou.study.config;

import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SaaLLMConfig {


    @Value("spring.ai.dashscope.api-key")
    private String apiKey;


    /**
     * 初始化DashScopeApi，如果是接入阿里的模型，可以不用在这里配置，会自动读取配置文件中的spring.ai.dashscope.api-key，实现自动装配
     * @return {@link DashScopeApi }
     */
    @Bean
    public DashScopeApi dashScopeApi() {
        return DashScopeApi.builder().apiKey(apiKey).build();
    }
}
