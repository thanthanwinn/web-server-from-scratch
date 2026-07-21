package org.ttw.webserver;

import org.ttw.webserver.requestHandler.HandlerFactory;
import org.ttw.webserver.requestHandler.RequestHandler;

public class HttpRequestProcessor {
	
	HandlerFactory factory;
	
	public HttpRequestProcessor(HandlerFactory factory) {
		this.factory = factory;
	}

	public  HttpResponse handle(HttpRequest request){

       HttpResponse response = new HttpResponse(request);
       RequestHandler handler =  factory.getHandler(request.getHttpMethod());
       handler.handle(request, response);
       
       return response;
    }



	

}
