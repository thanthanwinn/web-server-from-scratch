package webserver;

import org.junit.Assert;
import org.junit.Test;

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
}
