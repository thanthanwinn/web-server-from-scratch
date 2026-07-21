package org.ttw.webserver.requestHandler;

import java.net.http.HttpRequest;
import java.util.HashMap;

public class HandlerFactory {
	
	 	public static final String GET_METHOD = "GET";
	    public static final String POST_METHOD = "POST";

	    HashMap<String, RequestHandler> handlers = new HashMap<>();
	    
	    RequestHandler defaultHandler = new GetHandler();
	    
	    
	    
	    public RequestHandler getHandler(String httpMethod) {
			RequestHandler handler = this.handlers.get(httpMethod);
			   if(handler == null) {
				 return  defaultHandler;
			   }
			   
			 return handler;
		}
	    
	    public void addHander(String method,RequestHandler handler) {
	    	this.handlers.put(method, handler);
	    }



}
