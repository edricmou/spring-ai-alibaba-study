package com.edricmou.controller;

import lombok.AllArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@AllArgsConstructor
public class McpClienAMapController {

    private ChatClient chatClient;
    private ChatModel chatModel;

    /**
     * 添加了MCP调用能力
     * http://localhost:8016/mcp/chat?msg=查询北纬39.9042东经116.4074天气
     * http://localhost:8016/mcp/chat?msg=查询61.149.121.66归属地
     * http://localhost:8016/mcp/chat?msg=查询昌平到天安门路线规划
     *
     * @param question
     * @return
     */
    @GetMapping("/mcp/chat")
    public Flux<String> chat(String question) {
        return chatClient.prompt(question).stream().content();
    }

    /**
     * 没有添加MCP调用能力
     * http://localhost:8016/mcp/chat2?msg=查询北纬39.9042东经116.4074天气
     *
     * @param question
     * @return
     */
    @GetMapping("/mcp/chat2")
    public Flux<String> chat2(String question) {
        return chatModel.stream(question);
    }
}
