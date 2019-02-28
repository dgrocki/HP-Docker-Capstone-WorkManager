package com.hp.pwp.capstone
import org.eclipse.jetty.server.Server
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Jetty extends Server{
	
    private static final Gson gson = new GsonBuilder().
    setDateFormat("yyyy-MM-dd HH:mm:ss").create();
 
    public Jetty(int port) {
        super(port);
 
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler
        .NO_SESSIONS);
        context.setContextPath("/test");
        context.addServlet(new ServletHolder(new GetName(gson)), "/GetName/*");
        context.addServlet(new ServletHolder(new SaveName()), "/SaveName/*");
        this.setHandler(context);
        this.setStopAtShutdown(true);
    }
 
}
