package edricmou.config;

import com.alibaba.cloud.ai.memory.redis.RedisChatMemoryRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisMemoryConfig {

    @Value("${spring.data.redis.host}")
    private String host;

    @Value("${spring.data.redis.password}")
    private String password;

    @Bean
    public RedisChatMemoryRepository  redisChatMemoryRepository() {
        // 将redis连接参数配置到chatmemory仓库
        return RedisChatMemoryRepository.builder().host(host).password(password).build();
    }
}
