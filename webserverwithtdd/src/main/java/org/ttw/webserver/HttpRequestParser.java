package org.ttw.webserver;


public class HttpRequestParser {

    public HttpRequest parse(String rawRequest){
        HttpRequest request = new HttpRequest();

        String[] lines = rawRequest.split("\r\n");
        String startLine = lines[0];
        String startLineData[] = startLine.split(" ");

        String httpMethod = startLineData[0];
        String httpUrl = startLineData[1];
        String httpVersion = startLineData[2];

        request.setHttpMethod(httpMethod);
        request.setUrl(httpUrl);
        request.setHttpVersion(httpVersion);

        return request;

    }
}

