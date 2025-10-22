package com.edricmou.config;

import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LLMConfig {

    @Value("${spring.ai.dashscope.api-key}")
    private String apiKey;

    @Value("${spring.ai.dashscope.workspace-id}")
    private String workSpaceId;

    @Bean
    public DashScopeApi dashScopeApi() {
        return DashScopeApi.builder()
                // 百炼平台apikey
                .apiKey(apiKey)
                // 百炼平台工作区id
                .workSpaceId(workSpaceId)
                .build();
    }

    @Bean
    public ChatClient chatClient(ChatModel chatModel) {
        return ChatClient.builder(chatModel).build();
    }

}
