package com.edricmou.config;

import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatOptions;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SaaLLMConfig {

    @Value("${spring.ai.dashscope.api-key}")
    private String apiKey;

    private final String DEEPSEEKL_MODAL = "deepseek-v3";

    @Bean(name = "deepseek")
    public ChatModel deepseek() {
        return DashScopeChatModel.builder().dashScopeApi(DashScopeApi.builder().apiKey(apiKey).build())
                .defaultOptions(DashScopeChatOptions.builder().withModel(DEEPSEEKL_MODAL)
                        .build()).build();
    }

    @Bean(name = "qwen")
    public ChatModel qwen() {
        return DashScopeChatModel.builder().dashScopeApi(DashScopeApi.builder().apiKey(apiKey).build())
                .defaultOptions(DashScopeChatOptions.builder().withModel(DashScopeApi.ChatModel.QWEN_MAX.getModel())
                        .build()).build();
    }

    @Bean("deepseekClient")
    public ChatClient deepseekChatClient(@Qualifier("deepseek") ChatModel deepseek) {
        return ChatClient.builder(deepseek).defaultOptions(ChatOptions.builder().model(DEEPSEEKL_MODAL).build()).build();
    }

    @Bean("qwenClient")
    public ChatClient qwenClient(@Qualifier("qwen") ChatModel qwen) {
        return ChatClient.builder(qwen).defaultOptions(ChatOptions.builder().model(DashScopeApi.ChatModel.QWEN_MAX.getModel()).build()).build();
    }
}
