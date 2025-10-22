package edricmou.controller;

import com.alibaba.cloud.ai.dashscope.audio.DashScopeSpeechSynthesisOptions;
import com.alibaba.cloud.ai.dashscope.audio.synthesis.SpeechSynthesisModel;
import com.alibaba.cloud.ai.dashscope.audio.synthesis.SpeechSynthesisPrompt;
import com.alibaba.cloud.ai.dashscope.audio.synthesis.SpeechSynthesisResponse;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.util.UUID;

@RestController
public class ChatController {

    private final String downloadPath = "/Users/edricmou/Downloads/";

    @Resource
    private SpeechSynthesisModel speechSynthesisModel;

    @GetMapping("text2Voice")
    public String text2Image(@RequestParam(value = "question",defaultValue = "你好，支付宝到账1百万元，请注意查收") String question) {
        //1 语音参数设置
        DashScopeSpeechSynthesisOptions options = DashScopeSpeechSynthesisOptions.builder()
                // 模型id
                .model("cosyvoice-v2")
                // 音色
                .voice("longyingcui")
                .build();

        // 2 调用大模型语音生成对象
        SpeechSynthesisResponse response = speechSynthesisModel.call(new SpeechSynthesisPrompt(question, options));

        // 3 字节流语音转换
        ByteBuffer byteBuffer = response.getResult().getOutput().getAudio();

        try {
            String filePath = downloadPath + UUID.randomUUID() + ".mp3";
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            fileOutputStream.write(byteBuffer.array());
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return "success";
    }
}
