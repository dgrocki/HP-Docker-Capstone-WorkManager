package com.hp.pwp.capstone

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class PostWork extends HttpServlet 
{
    private BeanstalkClient servBeanStalk;

    public PostWork(BeanstalkClient beanstalk) 
    {
        servBeanStalk = beanstalk;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException 
    {
        try {
            final long timeStamp = System.currentTimeMillis();
            final String work = request.getParameter("work");
            servBeanStalk.sendWork(work);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } finally {
            response.getWriter().close();
        }
    }
}
