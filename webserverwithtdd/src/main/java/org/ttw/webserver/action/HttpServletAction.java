package org.ttw.webserver.action;

import java.net.http.HttpRequest;

import org.ttw.webserver.HttpResponse;

public abstract class HttpServletAction {

   public abstract  void handle(org.ttw.webserver.HttpRequest request, HttpResponse response);

}
