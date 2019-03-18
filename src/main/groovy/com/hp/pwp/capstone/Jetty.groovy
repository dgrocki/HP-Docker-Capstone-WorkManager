package com.hp.pwp.capstone
import org.eclipse.jetty.server.Server
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Jetty extends Server
{
    private static final Gson gson = new GsonBuilder().
        setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    private BeanstalkClient beanstalk;
    private ServletContextHandler context;
    //private ServletContextHandler workerManager;
    //private HandlerCollection context;
    public Jetty(int port, BeanstalkClient bs)
    {
        super(port);

        beanstalk = bs;
        context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        //workManager = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);


        context.setContextPath("/workManager");
        context.addServlet(new ServletHolder(new GetWork(beanstalk)), "/getWorkA/*");
        context.addServlet(new ServletHolder(new PostWork(beanstalk)), "/postWork/*");

        ServletHolder holderEvents = new ServletHolder("ws-events", WorkServlet.class);
        context.addServlet(holderEvents, "/socket/*");
        
        //workManager.setContextPath("/workManager")
        //context.setHandlers(new Handler[] { workerA, workManager });
        
        this.setHandler(context);
        this.setStopAtShutdown(true);
    }
}
