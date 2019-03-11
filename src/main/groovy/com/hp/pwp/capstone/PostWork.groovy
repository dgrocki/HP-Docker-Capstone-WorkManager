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
        StringBuffer jb = new StringBuffer();
        String line = null;

        try {
            BufferedReader reader = request.getReader();

            while((line = reader.readLine()) != null) {
                jb.append(line);
            }
            

            println( jb )            

            final long timeStamp = System.currentTimeMillis();
            
            servBeanStalk.sendWork(jb.toString());
            
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception ex) {
            println( ex )
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } finally {
            response.getWriter().close();
        }
    }
}
