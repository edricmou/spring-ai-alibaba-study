package com.edricmou.controller;

import com.edricmou.entity.StudentRecord;
import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ChatController {

    @Resource
    private ChatClient deepseekClient;

    @GetMapping("deepseek")
    public StudentRecord deepseekChat(@RequestParam("name") String name, @RequestParam("age") Integer age) {

        return deepseekClient.prompt()
                // 定义ai的功能边距，设定ai的角色和背景，使回答的内容专业且精确
                .system(promptSystemSpec -> promptSystemSpec.text("学号1001, 我叫{name}, 年龄{age}, 邮箱xxxx@qq.com,住址guandong").
                        params(Map.of("name", name, "age", age)))
                .call()
                // 格式化为StudentRecord对象的json格式输出
                .entity(StudentRecord.class);
    }
}
