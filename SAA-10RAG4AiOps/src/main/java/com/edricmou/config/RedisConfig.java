package com.edricmou.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.ConnectionPoolConfig;
import redis.clients.jedis.JedisPooled;

import java.net.URISyntaxException;
import java.time.Duration;

@Configuration
public class RedisConfig {

    @Value("${spring.data.redis.host}")
    private String redisUri;

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactor) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

        redisTemplate.setConnectionFactory(redisConnectionFactor);
        //设置key序列化方式string
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //设置value的序列化方式json，使用GenericJackson2JsonRedisSerializer替换默认序列化
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }

//    @Bean
//    public JedisPooled jedisPooled() throws URISyntaxException {
//        ConnectionPoolConfig poolConfig = new ConnectionPoolConfig();
//        // 池中最大活跃连接数，默认 8
//        poolConfig.setMaxTotal(8);
//        // 池中最大空闲连接数，默认 8
//        poolConfig.setMaxIdle(8);
//        // 池中的最小空闲连接数，默认 0
//        poolConfig.setMinIdle(0);
//        // 启用等待连接变为可用
//        poolConfig.setBlockWhenExhausted(true);
//        // 等待连接变为可用的最大秒数，jdk17 以上支持，低版本可用 setMaxWaitMillis
//        poolConfig.setMaxWait(Duration.ofSeconds(1));
//        // 控制检查池中空闲连接的间隔时间，jdk17 以上支持，低版本可用 setTimeBetweenEvictionRunsMillis
//        poolConfig.setTimeBetweenEvictionRuns(Duration.ofSeconds(1));
//        return new JedisPooled(poolConfig, redisUri);
//    }
}
