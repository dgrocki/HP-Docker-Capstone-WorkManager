package com.hp.pwp.capstone

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@SuppressWarnings("serial")
public class GetName extends HttpServlet {
 
    private Gson gson;
 
    public GetName(Gson g) {
        this.gson = g;
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
                                            throws ServletException, IOException {
        String output = "";
        try {
            JsonObject retVal = new JsonObject();
            
	//	response.setContentType("text/html");
            response.setContentType("text/html;charset=UTF-8");
//            retVal.add("name", "jetty");
  //          retVal.add("surname", "is awasome");
            //output = retVal.toString();
	   	output = "hello"; 
            //response.setStatus(HttpServletResponse.SC_OK);
	    PrintWriter out = response.getWriter();
	    out.println( output )
           // response.setContentType("text/html;charset=UTF-8");
            //out.println(output);
 
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
 
        } finally {
            //response.getWriter().close();
        }
    }
 
}
