package com.example.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

/**
 * Created by BMF on 2018/8/14 0014.
 */
@Component
@Order(1)
public class ApplicationRunnerImpl implements ApplicationRunner {

    @Autowired
    private  ApplicationArguments applicationArguments;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("ApplicationRunnerImpl");
        args.getOptionNames().forEach(System.out::println);
        applicationArguments.getOptionNames().forEach(System.out::println);
        Stream.of(applicationArguments.getSourceArgs()).forEach(System.out::println);

    }
}