package webserver;

import org.junit.Assert;
import org.junit.Test;
import org.ttw.webserver.HttpRequest;
import org.ttw.webserver.HttpRequestHandler;
import org.ttw.webserver.HttpResponse;

public class HttpRequestHandlerTest {

    @Test
    public void testHandleRequestDefaultRoot(){
        HttpRequest request = createHttpGetRequest();
        request.setUrl("/");

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
        HttpRequest request = createHttpGetRequest();
        request.setUrl("/index.html");

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
        HttpRequest request = createHttpGetRequest();
        request.setUrl("/style.css");

        HttpRequestHandler httpRequestHandler = new HttpRequestHandler();
        HttpResponse response = httpRequestHandler.handle(request);

        String body = response.getBody();

        Assert.assertEquals("200",response.getStatusCode());
        Assert.assertEquals("OK",response.getStatusCodeDescription());
        Assert.assertEquals("text/css",response.getHeader("Content-Type"));
        Assert.assertTrue(body.contains("green"));
    }

    @Test
    public void testHandlePost(){
        HttpRequest request = createHttpPostRequest();
        request.setUrl("/HelloWorld");

        HttpRequestHandler httpRequestHandler = new HttpRequestHandler();
        HttpResponse response = httpRequestHandler.handle(request);

        String body = response.getBody();

        Assert.assertEquals("200",response.getStatusCode());
        Assert.assertEquals("OK",response.getStatusCodeDescription());
        Assert.assertEquals("text/html",response.getHeader("Content-Type"));
        Assert.assertTrue(body.contains("Hello"));
    }
    @Test
    public void testHandlePostAnother(){
        HttpRequest request = createHttpPostRequest();
        request.setUrl("/Another");

        HttpRequestHandler httpRequestHandler = new HttpRequestHandler();
        HttpResponse response = httpRequestHandler.handle(request);

        String body = response.getBody();

        Assert.assertEquals("200",response.getStatusCode());
        Assert.assertEquals("OK",response.getStatusCodeDescription());
        Assert.assertEquals("text/html",response.getHeader("Content-Type"));
        Assert.assertTrue(body.contains("Another"));
    }

    private HttpRequest createHttpGetRequest() {
        HttpRequest request = new HttpRequest();
        request.setHttpMethod("GET");
        request.setHttpVersion("HTTP/1.1");
        return request;
    }

    private HttpRequest createHttpPostRequest() {
        HttpRequest request = new HttpRequest();
        request.setHttpMethod("POST");
        request.setHttpVersion("HTTP/1.1");
        return request;
    }
}
