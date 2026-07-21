package org.ttw.webserver.requestHandler;

import org.ttw.webserver.HttpRequest;
import org.ttw.webserver.HttpResponse;
import org.ttw.webserver.action.HttpServletAction;

public class PostHandler implements RequestHandler {

	@Override
	public void handle(HttpRequest request, HttpResponse response) {
		// TODO Auto-generated method stub
		String action =  request.getUrl();
    	String prefix = "org.ttw.webserver.action";
    	
    	
   	 System.out.println(action + "class name");

    	 Class clazz = null;
		try {
			clazz = Class.forName(prefix+action.replace("/","." ));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
    	 HttpServletAction act = null;
		try {
			act = (HttpServletAction) clazz.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 act.handle(request,response);
		
	}

}
