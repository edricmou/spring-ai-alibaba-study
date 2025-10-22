package com.edricmou.controller;

import com.edricmou.utils.DateTimeTools;
import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ChatController {

    @Resource
    private ChatClient chatClient;

    // 工具调用
    @GetMapping("toolcall/chat")
    public Flux<String> chat(@RequestParam(value = "question", defaultValue = "现在是几点") String question) {
        return chatClient.prompt().user(question)
                .tools(new DateTimeTools())
                .stream()
                .content();
    }
}
