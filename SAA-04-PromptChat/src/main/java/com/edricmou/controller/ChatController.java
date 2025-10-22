package com.edricmou.controller;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
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
        return deepseekClient.prompt()
                // 定义ai的功能边距，设定ai的角色和背景，使回答的内容专业且精确
                .system("你是一个医生，请你回答患者问题")
                // 用户提问
                .user(question)
                .stream()
                .content();
    }

    @GetMapping("deepseek2")
    public String deepseekChat2(@RequestParam(name = "question", defaultValue = "你是谁") String question) {
        AssistantMessage assistantMessage = deepseekClient.prompt()
                // 定义ai的功能边距，设定ai的角色和背景，使回答的内容专业且精确
                .system("你是一个医生，请你回答患者问题")
                // 用户提问
                .user(question)
                .call()
                .chatResponse()
                .getResult()
                .getOutput();

        // AI 对用户输入的响应。这不仅仅是一个答案或反应，它对于保持对话的流畅性至关重要。通过跟踪 AI 之前的响应（其“助手角色”消息），
        // 系统可确保连贯且上下文相关的交互。助手消息也可能包含功能工具调用请求信息。
        // 它就像 AI 中的一个特殊功能，在需要执行特定功能（例如计算、获取数据或不仅仅是说话）时使用
        return assistantMessage.getText();
    }

    @GetMapping("qwen")
    public Flux<String> qwenChat(@RequestParam(name = "question", defaultValue = "你是谁") String question) {
        return qwenClient.prompt(question).stream().content();
    }

    // 桥接外部服务，可以进行函数调用如，支付/数据查询等操作，类似调用第三方util工具类
}
