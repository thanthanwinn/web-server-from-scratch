package org.ttw.webserver;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HttpRequest {

    private String httpMethod;
    private String url;
    private String httpVersion;
}
