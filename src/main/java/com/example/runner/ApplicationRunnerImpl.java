package com.example.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by BMF on 2018/8/14 0014.
 */
@Component
@Order(1)
public class ApplicationRunnerImpl implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("ApplicationRunnerImpl");
        args.getOptionNames().forEach(System.out::println);
    }
}