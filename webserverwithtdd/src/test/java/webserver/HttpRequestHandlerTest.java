package webserver;

import org.junit.Assert;
import org.junit.Test;
import org.ttw.webserver.HttpRequest;
import org.ttw.webserver.HttpRequestHandler;
import org.ttw.webserver.HttpResponse;

public class HttpRequestHandlerTest {

    @Test
    public void testHandleRequestDefaultRoot(){
        HttpRequest request = new HttpRequest();
        request.setHttpMethod("GET");
        request.setUrl("/");
        request.setHttpVersion("HTTP/1.1");

        HttpRequestHandler httpRequestHandler = new HttpRequestHandler();
        HttpResponse response = httpRequestHandler.handle(request);

        String body = response.getBody();

        Assert.assertEquals("200",response.getStatusCode());
        Assert.assertEquals("OK",response.getStatusCodeDescription());
        Assert.assertEquals("text/html",response.getHeader("Content-Type"));
        Assert.assertTrue(body.contains("Hello"));
    }

    @Test
    public void testHandleRequestIndexHtml(){
        HttpRequest request = new HttpRequest();
        request.setHttpMethod("GET");
        request.setUrl("/index.html");
        request.setHttpVersion("HTTP/1.1");

        HttpRequestHandler httpRequestHandler = new HttpRequestHandler();
        HttpResponse response = httpRequestHandler.handle(request);

        String body = response.getBody();

        Assert.assertEquals("200",response.getStatusCode());
        Assert.assertEquals("OK",response.getStatusCodeDescription());
        Assert.assertEquals("text/html",response.getHeader("Content-Type"));
        Assert.assertTrue(body.contains("Hello"));
    }

    @Test
    public void testHandleCSS(){
        HttpRequest request = new HttpRequest();
        request.setHttpMethod("GET");
        request.setUrl("/style.css");
        request.setHttpVersion("HTTP/1.1");

        HttpRequestHandler httpRequestHandler = new HttpRequestHandler();
        HttpResponse response = httpRequestHandler.handle(request);

        String body = response.getBody();

        Assert.assertEquals("200",response.getStatusCode());
        Assert.assertEquals("OK",response.getStatusCodeDescription());
        Assert.assertEquals("text/css",response.getHeader("Content-Type"));
        Assert.assertTrue(body.contains("green"));
    }
}
