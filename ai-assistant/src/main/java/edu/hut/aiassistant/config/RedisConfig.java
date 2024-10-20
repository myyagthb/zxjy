package edu.hut.aiassistant.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(connectionFactory);
//
//        // 设置键的序列化方式
//        template.setKeySerializer(new StringRedisSerializer());
//        // 设置值的序列化方式
//        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//
//        // 设置哈希键的序列化方式
//        template.setHashKeySerializer(new StringRedisSerializer());
//        // 设置哈希值的序列化方式
//        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
//
//        return template;
//    }

    // 通用的RedisTemplate初始化方法
    private <K, V> RedisTemplate<K, V> createRedisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<K, V> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        // 设置键的序列化方式
        template.setKeySerializer(new StringRedisSerializer());
        // 设置值的序列化方式
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        // 设置哈希键的序列化方式
        template.setHashKeySerializer(new StringRedisSerializer());
        // 设置哈希值的序列化方式
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

        return template;
    }

    @Bean(name = "userStatusRedisTemplate")
    public RedisTemplate<String, Boolean> userStatusRedisTemplate(RedisConnectionFactory connectionFactory) {
        return createRedisTemplate(connectionFactory);
    }

    @Bean(name = "codeRedisTemplate")
    public RedisTemplate<String, Integer> codeRedisTemplate(RedisConnectionFactory connectionFactory) {
        return createRedisTemplate(connectionFactory);
    }
}