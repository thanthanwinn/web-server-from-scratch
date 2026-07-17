package webserver;

import org.junit.Assert;
import org.junit.Test;
import org.ttw.webserver.HttpRequest;
import org.ttw.webserver.HttpRequestParser;

public class HttpRequestParserTest {
    @Test
     public void testStartLine(){
        HttpRequestParser parser = new HttpRequestParser();
        String httpRequest = "GET /hello HTTP/1.1\r\n";
        HttpRequest request = parser.parse(httpRequest);
        Assert.assertEquals("GET", request.getHttpMethod());
        Assert.assertEquals("/hello", request.getUrl());
        Assert.assertEquals("HTTP/1.1",request.getHttpVersion());

    }

    @Test
    public void testStartLinePost(){
        HttpRequestParser parser = new HttpRequestParser();
        String httpRequest = "POST /hello HTTP/1.1\r\n";
        HttpRequest request = parser.parse(httpRequest);
        Assert.assertEquals("POST", request.getHttpMethod());
        Assert.assertEquals("/hello", request.getUrl());
        Assert.assertEquals("HTTP/1.1",request.getHttpVersion());

    }
    @Test
    public void testStartLinePostWithHeader(){
        HttpRequestParser parser = new HttpRequestParser();

        String httpRequest = "POST /hello HTTP/1.1\r\n"
                + "Content-Length: 354\r\n"
                + "Content-Type: application/json\r\n";

        HttpRequest request = parser.parse(httpRequest);

        Assert.assertEquals("POST", request.getHttpMethod());
        Assert.assertEquals("/hello", request.getUrl());
        Assert.assertEquals("HTTP/1.1",request.getHttpVersion());
        Assert.assertEquals("application/json",request.getHeader("Content-Type"));
        Assert.assertEquals("354",request.getHeader("Content-Length"));

    }

    @Test
    public void testStartLinePostWithBody(){
        HttpRequestParser parser = new HttpRequestParser();
        String body = "{data:123}";
        String httpRequest = "POST /hello HTTP/1.1\r\n"
                + "Content-Length: 354\r\n"
                + "Content-Type: application/json\r\n"
                + "\r\n"
                +body+ "\r\n"
                ;

        HttpRequest request = parser.parse(httpRequest);

        Assert.assertEquals("POST", request.getHttpMethod());
        Assert.assertEquals("/hello", request.getUrl());
        Assert.assertEquals("HTTP/1.1",request.getHttpVersion());
        Assert.assertEquals("application/json",request.getHeader("Content-Type"));
        Assert.assertEquals("354",request.getHeader("Content-Length"));
        Assert.assertEquals(body,request.getBody());





    }
}
