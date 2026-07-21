package org.ttw.webserver;

import java.net.http.HttpRequest;

public interface HttpServletAction {

    void handle(HttpRequest request, HttpResponse response);
}
