package org.ttw.webserver;


import java.util.Objects;

public class HttpRequestParser {

    public static final String HEADER_KEY_VALUE_SEPARATOR = ": ";

    public HttpRequest parse(String rawRequest){
        HttpRequest request = new HttpRequest();

        String[] lines = rawRequest.split("\r\n");
        String startLine = lines[0];

        parseStatusLine(request,startLine);

        parseHeadersAndBody(request,lines);


        return request;

    }

    private void parseHeadersAndBody(HttpRequest request, String[] lines){
        String line;
        int i ;
        for( i = 1; i< lines.length; i++){
            line = lines[i];

            if(line.isEmpty()){
                    i = i + 1;
                    break;
            }

            String headerData[] = line.split(HEADER_KEY_VALUE_SEPARATOR);
            String key = headerData[0];
            String value = headerData[1];

            request.setHeader(key,value);
        }
        if (i < lines.length){
            String body = lines[i];
            request.setBody(body);
        }

    }

    private void parseStatusLine(HttpRequest request,String startLine){
        String startLineData[] = startLine.split(" ");

        String httpMethod = startLineData[0];
        String httpUrl = startLineData[1];
        String httpVersion = startLineData[2];

        request.setHttpMethod(httpMethod);
        request.setUrl(httpUrl);
        request.setHttpVersion(httpVersion);
    }
}

