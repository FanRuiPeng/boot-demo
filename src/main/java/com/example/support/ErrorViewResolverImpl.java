package com.example.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by BMF on 2018/8/25 0025.
 */
public class ErrorViewResolverImpl implements ErrorViewResolver {

    private final static Logger LOGGER = LoggerFactory.getLogger(ErrorViewResolverImpl.class);

    @Override
    public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {
        boolean xxClientError = status.is4xxClientError();
        if (xxClientError) {
            LOGGER.info("status is : {}", status.value());
        }
        return new ModelAndView(status.value() + "");
    }
}