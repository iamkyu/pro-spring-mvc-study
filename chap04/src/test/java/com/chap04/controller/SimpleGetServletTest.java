package com.chap04.controller;

import com.chap04.controller.helper.AbstractDispatcherServletTest;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Kj Nam
 * @since 2017-02-18
 */
public class SimpleGetServletTest extends AbstractDispatcherServletTest {

    @Test
    public void simpleServletTest() throws ServletException, IOException {
        MockHttpServletRequest req = new MockHttpServletRequest("GET", "/hello");
        req.addParameter("name", "Spring");
        MockHttpServletResponse resp = new MockHttpServletResponse();

        SimpleGetServlet servlet = new SimpleGetServlet();
        servlet.service(req, resp);

        assertTrue(resp.getContentAsString().contains("Hello Spring"));
    }

    @Test
    public void customDispatcherServletTest() throws ServletException, IOException {
        ConfigurableDispatcherServlet servlet = new ConfigurableDispatcherServlet();
        servlet.setRelativeLocations(getClass(), "spring-servlet.xml");
        servlet.setClasses(HelloSpring.class);
        servlet.init(new MockServletConfig("spring"));


        MockHttpServletRequest req = new MockHttpServletRequest("GET", "/hello");
        req.addParameter("name", "spring");
        MockHttpServletResponse resp = new MockHttpServletResponse();

        servlet.service(req, resp);

        ModelAndView mav = servlet.getModelAndView();
        assertThat(mav .getViewName(), is( "/WEB-INF/view/hello.jsp"));
        assertThat(mav.getModel().get("message"), is("Hello spring"));
    }

    @Test
    public void customDispatcherServletTestWithHelper() throws ServletException, IOException {
        ModelAndView mav = setRelativeLocations("spring-servlet.xml")
                .setClasses(HelloSpring.class)
                .initRequest("/hello", RequestMethod.GET)
                .addParameter("name", "Spring")
                .runService()
                .getModelAndView();

        assertThat(mav.getViewName(), is("/WEB-INF/view/hello.jsp"));
        assertThat(mav.getModel().get("message"), is("Hello Spring"));
    }
}