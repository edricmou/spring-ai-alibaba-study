package com.edricmou.controller;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class McpClientController {

    @Resource
    private ChatClient chatClient;
    @Resource
    private ChatModel chatModel;

    @GetMapping("mcpclient/chat")
    public Flux<String> chat(@RequestParam(name = "question", defaultValue = "今天北京天气怎么样") String msg) {
        System.out.println("使用了mcp");
        return chatClient.prompt(msg).stream().content();
    }

    @GetMapping("mcpclient/chat2")
    public Flux<String> chat2(@RequestParam(name = "question", defaultValue = "今天北京天气怎么样") String msg) {
        System.out.println("没有使用mcp");
        return chatModel.stream(msg);
    }
}
