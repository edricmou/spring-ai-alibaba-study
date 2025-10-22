package com.edricmou.study.controller;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController()
public class ChatController {

    @Resource
    private ChatModel chatModel;

    @Resource
    private ChatClient chatClient;

//    // 构造方法注入
//    public ChatController(ChatClient.Builder builder) {
//        this.chatClient = builder.build();
//    }

    /**
     * 普通输出需要等待所有的文字都准备完毕，然后一次性输出，这样有点慢，有延迟
     *
     * @param msg
     * @return {@link String }
     */
    @GetMapping("chat")
    public String chat(@RequestParam(name = "msg", defaultValue = "你是谁") String msg) {
        return chatClient.prompt(msg).call().content();
    }


    /**
     * 流式输出的好处可以实时一个一个字的慢慢的回复，不用等待，用户体验较好
     *
     * @param msg
     * @return {@link Flux }<{@link String }>
     */
    @GetMapping("streamChat")
    public Flux<String> streamChat(@RequestParam(name = "msg", defaultValue = "你是谁") String msg) {
        return chatClient.prompt(msg).stream().content();
    }
}
