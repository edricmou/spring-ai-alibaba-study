package edricmou.controller;

import com.alibaba.cloud.ai.dashscope.image.DashScopeImageOptions;
import jakarta.annotation.Resource;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {


    @Resource
    private ImageModel imageModel;

    @GetMapping("text2Image")
    public String text2Image(@RequestParam("question") String question) {
        // 文生图调用
        return imageModel
                .call(new ImagePrompt(question, DashScopeImageOptions.builder().withModel("wanx2.1-t2i-turbo").build()))
                .getResult()
                .getOutput()
                .getUrl();
    }
}
