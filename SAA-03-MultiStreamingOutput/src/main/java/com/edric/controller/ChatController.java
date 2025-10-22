package com.edric.controller;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ChatController {

    @Resource
    private ChatClient deepseekClient;

    @Resource
    private ChatClient qwenClient;

    @GetMapping("deepseek")
    public Flux<String> deepseekChat(@RequestParam(name = "question", defaultValue = "你是谁") String question) {
        return deepseekClient.prompt(question).stream().content();
    }

    @GetMapping("qwen")
    public Flux<String> qwenChat(@RequestParam(name = "question", defaultValue = "你是谁") String question) {
        return qwenClient.prompt(question).stream().content();
    }
}
