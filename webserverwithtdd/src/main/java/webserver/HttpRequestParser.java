package webserver;


import java.util.Arrays;

public class HttpRequestParser {

    public HttpRequest parse(String rawRequest){
        HttpRequest request = new HttpRequest();
        String[] lines = rawRequest.split("\r\n");
        String startLine = lines[0];
        String startLineData[] = startLine.split(" ");
        request.setHttpMethod(startLineData[0]);
        request.setUrl(startLineData[1]);
        request.setHttpVersion(startLineData[2]);
        return request;

    }
}

