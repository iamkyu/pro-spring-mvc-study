package com.chap04.controller.helper;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;

/**
 * @author Kj Nam
 * @since 2017-02-19
 */
public interface AfterRunService {
    String getContentAsString() throws UnsupportedEncodingException;

    WebApplicationContext getContext();

    <T> T getBean(Class<T> beanType);

    ModelAndView getModelAndView();

    AfterRunService assertViewName(String viewName);

    AfterRunService assertModel(String name, Object value);
}
