package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by BMF on 2018/8/13 0013.
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @Value("${name}")
    private String name;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @RequestMapping("/")
    @ResponseBody
    public String index() {
        String execute = redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.ping();
            }
        });
        System.out.println(execute);
        return name;
    }
}