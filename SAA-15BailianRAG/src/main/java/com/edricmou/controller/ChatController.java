package com.edricmou.controller;

import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import com.alibaba.cloud.ai.dashscope.rag.DashScopeDocumentRetriever;
import com.alibaba.cloud.ai.dashscope.rag.DashScopeDocumentRetrieverOptions;
import lombok.AllArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.rag.advisor.RetrievalAugmentationAdvisor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@AllArgsConstructor
public class ChatController {

    private ChatClient chatClient;
    private DashScopeApi dashScopeApi;

    @GetMapping("chat")
    public Flux<String> chat(String question) {
        RetrievalAugmentationAdvisor advisor = RetrievalAugmentationAdvisor.builder()
                .documentRetriever(new DashScopeDocumentRetriever(dashScopeApi, DashScopeDocumentRetrieverOptions
                        .builder()
                        // 百炼平台知识库id
                        .withIndexName("ops")
                        .build()))
                .build();

        return chatClient.prompt()
                .user(question)
                .advisors(advisor)
                .stream()
                .content();
    }
}
