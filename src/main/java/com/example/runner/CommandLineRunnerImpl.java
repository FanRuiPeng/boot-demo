package com.example.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by BMF on 2018/8/14 0014.
 */
@Component
@Order(2)
public class CommandLineRunnerImpl implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("CommandLineRunnerImpl");
        for (String str : args) {
            System.out.println(args);
        }
    }
}