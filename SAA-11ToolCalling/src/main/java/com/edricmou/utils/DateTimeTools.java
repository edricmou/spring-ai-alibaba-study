package com.edricmou.utils;

import org.springframework.ai.tool.annotation.Tool;

import java.time.LocalDateTime;

public class DateTimeTools {


    @Tool(description = "获取当前时间",returnDirect = false) //不直接返回，由大模型进行返回
    public String getCurrentDateTime() {
        return LocalDateTime.now().toString();
    }
}
