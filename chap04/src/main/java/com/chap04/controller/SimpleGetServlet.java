package com.chap04.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Kj Nam
 * @since 2017-02-18
 */
public class SimpleGetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");

        resp.getWriter().print("<HTML><BODY>");
        resp.getWriter().print("Hello " + name);
        resp.getWriter().print("</BODY></HTML>");
    }
}
