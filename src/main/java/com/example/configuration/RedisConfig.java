package com.example.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by BMF on 2018/8/28 0028.
 */
@Data
//@Configuration
//@PropertySource("classpath:config/${spring.profiles.active}/redis.yml")
@ConfigurationProperties("spring.redis")
public class RedisConfig {
    private String host;
    private Integer port;
    private String password;
    private Integer database;
    private Integer maxWaitMillis;
    private Integer minEvictableIdleTimeMillis;
    private Integer timeBetweenEvictionRunsMillis;
    private Integer timeout;
    private Boolean usePool;
    private Integer maxTotal;
    private Integer maxIdle;
    private Integer minIdle;
}