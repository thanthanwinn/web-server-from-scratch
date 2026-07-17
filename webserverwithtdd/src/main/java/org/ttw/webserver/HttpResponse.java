package org.ttw.webserver;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class HttpResponse extends HttpMessage{
    HashMap<String ,String> statusCodeMapping = new HashMap<>();

    String statusCode;
    String statusCodeDescription;

    HttpResponse(HttpRequest request){
        statusCodeMapping.put("200","OK");
        statusCodeMapping.put("201","Created");
        statusCodeMapping.put("400","Bad Request");
        statusCodeMapping.put("500","Internal Server Error");
    }

    public void setStatusCode(String statusCode){
        this.statusCode = statusCode;
        this.statusCodeDescription = this.statusCodeMapping.get(statusCode);
    }

}
