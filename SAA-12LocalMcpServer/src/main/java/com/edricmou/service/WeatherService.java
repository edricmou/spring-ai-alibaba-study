package com.edricmou.service;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class WeatherService {


    /**
     * MCP Server服务
     * @param city
     * @return {@link String }
     */
    @Tool(description = "根据城市名称获取天气预报")
    public String getWeatherByCity(String city) {
        System.out.println("使用了tool");
        Map<String, String> map = Map.of(
                "北京", "11111降雨频繁，其中今天和后天雨势较强，部分地区有暴雨并伴强对流天气，需注意",
                "上海", "22222多云,15℃~27℃,南风3级，当前温度27℃。",
                "深圳", "333333多云40天，阴16天，雨30天，晴3天"
        );
        return map.getOrDefault(city, "抱歉：未查询到对应城市！");
    }
}
