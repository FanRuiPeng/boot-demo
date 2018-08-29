package com.example.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionCommands;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by BMF on 2018/8/28 0028.
 */
@Configuration
@EnableConfigurationProperties(RedisConfig.class)
public class RedisConfiguration {

    private final static Logger LOGGER = LoggerFactory.getLogger(RedisConfiguration.class);

    @Autowired
    private RedisConfig redisConfig;

    @Bean(name = "jedisPoolConfig")
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(redisConfig.getMaxTotal());
        jedisPoolConfig.setMaxIdle(redisConfig.getMaxIdle());
        jedisPoolConfig.setMinIdle(redisConfig.getMinIdle());
        jedisPoolConfig.setMaxWaitMillis(redisConfig.getMaxWaitMillis());
        jedisPoolConfig.setMinEvictableIdleTimeMillis(redisConfig.getMinEvictableIdleTimeMillis());
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(redisConfig.getTimeBetweenEvictionRunsMillis());
        return jedisPoolConfig;
    }

    @Bean(name = "jedisConnectionFactory", destroyMethod = "destroy")
    public JedisConnectionFactory jedisConnectionFactory(@Qualifier("jedisPoolConfig") JedisPoolConfig jedisPoolConfig) {
        JedisConnectionFactory factory = new JedisConnectionFactory(jedisPoolConfig);
        factory.setHostName(redisConfig.getHost());
        factory.setPort(redisConfig.getPort());
        factory.setPassword(redisConfig.getPassword());
        factory.setTimeout(redisConfig.getTimeout());
        factory.setUsePool(redisConfig.getUsePool());
        factory.setDatabase(redisConfig.getDatabase());
        return factory;
    }

    @Bean(name = "redisTemplate")
    public RedisTemplate<String, String> redisTemplate(@Qualifier("jedisConnectionFactory") JedisConnectionFactory jedisConnectionFactory,
                                                       @Qualifier("stringRedisSerializer") RedisSerializer stringRedisSerializer) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(stringRedisSerializer);
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        return redisTemplate;
    }

    @Bean("stringRedisSerializer")
    public RedisSerializer<String> stringRedisSerializer() {
        return new StringRedisSerializer();
    }

    public <T> RedisSerializer<T> jackson2JsonRedisSerializer(Class<T> clazz) {
        return new Jackson2JsonRedisSerializer<>(clazz);
    }
}