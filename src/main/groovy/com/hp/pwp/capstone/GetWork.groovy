package com.hp.pwp.capstone

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import groovy.json.JsonSlurper

@SuppressWarnings("serial")
public class GetWork extends HttpServlet 
{
    private BeanstalkClient servBeanStalk;
    private String output;

    public GetWork(BeanstalkClient beanstalk) 
    {
        servBeanStalk = beanstalk;
        output = "";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException 
    {
        def parser = new JsonSlurper()

        output = servBeanStalk.recieve_new_work();

        def json = parser.parseText(output)

        println( json )

        try {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println( output )
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } finally {
            //out.close();
        }
    }
}
