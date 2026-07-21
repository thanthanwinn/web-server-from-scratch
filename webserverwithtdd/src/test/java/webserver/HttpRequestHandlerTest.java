package webserver;

import org.junit.Assert;
import org.junit.Test;
import org.ttw.webserver.HttpRequest;
import org.ttw.webserver.HttpRequestProcessor;
import org.ttw.webserver.HttpResponse;
import org.ttw.webserver.requestHandler.GetHandler;
import org.ttw.webserver.requestHandler.HandlerFactory;
import org.ttw.webserver.requestHandler.PostHandler;

public class HttpRequestHandlerTest {

    @Test
    public void testHandleRequestDefaultRoot(){
        HttpRequest request = createHttpGetRequest();
        request.setUrl("/");
        HttpRequestProcessor httpRequestProcesser = getHttpRequestProcessor();
        HttpResponse response = httpRequestProcesser.handle(request);

        String body = response.getBody();

        Assert.assertEquals("200",response.getStatusCode());
        Assert.assertEquals("OK",response.getStatusCodeDescription());
        Assert.assertEquals("text/html",response.getHeader("Content-Type"));
        Assert.assertTrue(body.contains("Hello"));
    }

	private HttpRequestProcessor getHttpRequestProcessor() {
		HandlerFactory factory = new HandlerFactory();
		factory.addHander("GET", new GetHandler());
		factory.addHander("POST", new PostHandler());

        HttpRequestProcessor httpRequestProcesser = new HttpRequestProcessor(factory);
		return httpRequestProcesser;
	}

    @Test
    public void testHandleRequestIndexHtml(){
        HttpRequest request = createHttpGetRequest();
        request.setUrl("/index.html");

        HttpRequestProcessor httpRequestProcesser = getHttpRequestProcessor();
        HttpResponse response = httpRequestProcesser.handle(request);

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

        HttpRequestProcessor httpRequestProcesser = getHttpRequestProcessor();
        HttpResponse response = httpRequestProcesser.handle(request);

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

        HttpRequestProcessor httpRequestProcesser = getHttpRequestProcessor();
        HttpResponse response = httpRequestProcesser.handle(request);

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

        HttpRequestProcessor httpRequestProcesser = getHttpRequestProcessor();
        HttpResponse response = httpRequestProcesser.handle(request);

        String body = response.getBody();

        Assert.assertEquals("200",response.getStatusCode());
        Assert.assertEquals("OK",response.getStatusCodeDescription());
        Assert.assertEquals("text/html",response.getHeader("Content-Type"));
        Assert.assertTrue(body.contains("Hello"));
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
