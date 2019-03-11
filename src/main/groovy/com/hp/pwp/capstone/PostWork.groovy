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
        def parser = new JsonSlurper()
        StringBuffer jb = new StringBuffer();
        String line = null;

        try {
            BufferedReader reader = request.getReader();

            while((line = reader.readLine()) != null) {
                jb.append(line);
            }
            
            def json = parser.parseText(jb.toString())

            println("==POST[WorkA]: " + json )

            if(json.path && json.WID && json.JID && json.startPage && json.endPage) {
                println("==POST[WorkA]: JSON has everything I need :*)")
            } else {
                throw new IOException("The submitted JSON object does not have all the required fields :*(")
            }

            final long timeStamp = System.currentTimeMillis();
            
            servBeanStalk.sendWork(jb.toString());
            
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception ex) {
            println( "**POST[WorkA] ERR: " + ex )
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } finally {
            response.getWriter().close();
        }
    }
}
