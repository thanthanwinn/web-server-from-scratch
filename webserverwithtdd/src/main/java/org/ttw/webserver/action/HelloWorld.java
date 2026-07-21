package org.ttw.webserver.action;


import org.ttw.webserver.HttpRequest;
import org.ttw.webserver.HttpResponse;

public class HelloWorld extends HttpServletAction {

	public void handle(HttpRequest request, HttpResponse response) {
		// TODO Auto-generated method stub
		response.setBody("Hello World");
		response.setHeader("Content-Type","text/html");
		response.setHttpMethod("POST");
		response.setHttpVersion("HTTP/1.1");
		response.setStatusCode("200");
		
	}

}
