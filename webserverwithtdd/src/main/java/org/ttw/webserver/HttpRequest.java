package org.ttw.webserver;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class HttpRequest {

    private String httpMethod;
    private String url;
    private String httpVersion;
    private String body;


    HashMap<String , String> headers = new HashMap<>();

    public void setHeader(String name, String value){
        this.headers.put(name,value);
    }

    public String getHeader(String name){
        return this.headers.get(name);
    }

    public String getBody(String body){
        return this.body;
    }


}
