package org.ttw.webserver.requestHandler;

import org.ttw.webserver.HttpRequest;
import org.ttw.webserver.HttpResponse;

public interface RequestHandler {

	void handle(HttpRequest request,HttpResponse response);

}
