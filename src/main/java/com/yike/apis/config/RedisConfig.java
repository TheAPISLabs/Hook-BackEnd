package com.yike.apis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author ：lp
 * @version :
 * @date ：2020/6/19 9:58
 * @description:
 * @modified By：
 */
@Configuration
public class RedisConfig {
    @Bean(name = "redisTemplate")
    public RedisTemplate<Object, Object> getRedisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setKeySerializer(new StringRedisSerializer()); // The serialization type of key
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(factory);

        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer); // The serialized type of value
        return redisTemplate;
    }
}
