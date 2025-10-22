package edricmou.controller;

import com.alibaba.cloud.ai.memory.redis.RedisChatMemoryRepository;
import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

@RestController
public class ChatController {

    @Resource
    private ChatClient deepseekClient;

    @Resource
    private RedisChatMemoryRepository redisChatMemoryRepository;

    @GetMapping("deepseek")
    public Flux<String> deepseekChat(@RequestParam("question") String question) {
        return deepseekClient.prompt()
                .user(question)
                .advisors(advisorSpec -> advisorSpec.param(CONVERSATION_ID, "1"))
                .stream()
                .content();
    }
}
