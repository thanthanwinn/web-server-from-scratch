package org.ttw.webserver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class HttpRequestHandler {

    private static final Path SERVER_ROOT = resolveServerRoot();
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String CONTENT_LENGTH = "Content-Length";
    public static final String OK_200 = "200";
    public static final String INTERNAL_SERVER_500 = "500";
    public static final String GET_METHOD = "GET";
    public static final String POST_METHOD = "POST";

    HashMap<String,String> extContentType = new HashMap<>();
    public HttpRequestHandler(){
        this.extContentType.put("html", "text/html");
        this.extContentType.put("htm", "text/html");
        this.extContentType.put("css", "text/css");
        this.extContentType.put("", "text/plain");
    }

    public  HttpResponse handle(HttpRequest request){

       HttpResponse response = new HttpResponse(request);

       if(request.getHttpMethod().equals(GET_METHOD)){
            handleGet(request,response);
       }else if(request.getHttpMethod().equals(POST_METHOD)){
           this.handlePost(request,response);
       }
       return response;
    }

    private void handleGet(HttpRequest request,HttpResponse response) {

        Path path = resolvePath(request.getUrl());

        try {
            String contentType = resolveContentType(path);

            resolvedContent(response, path, contentType);

        } catch (IOException e){
            System.out.println(e.getMessage());
            response.setStatusCode(INTERNAL_SERVER_500);
        }
    }
    private void handlePost(HttpRequest request,HttpResponse response) {

        response.setStatusCode(OK_200);
        response.setHeader(CONTENT_TYPE,"text/html");
        response.setBody("Hello World");
    }

    private String resolveContentType(Path path) {
        String extension = this.getExtension(path);
        return this.extContentType.get(extension);
    }

    private void resolvedContent(HttpResponse response, Path path, String contentType) throws IOException {
        String content = Files.readString(path);
        response.setStatusCode(OK_200);
        response.setBody(content);
        response.setHeader(CONTENT_TYPE, contentType);
        response.setHeader(CONTENT_LENGTH, content.length()+"");
    }

    private Path resolvePath(String url) {
        Path path;
        if("/".equals(url)){
            System.out.println("handle Get /");
            path = SERVER_ROOT.resolve("index.html");
        } else {
            path = SERVER_ROOT.resolve(url.startsWith("/") ? url.substring(1) : url);
        }
        return path;
    }

    private  String getExtension(Path path){
        if (path == null){
            return "";
        }

        String fileName = path.getFileName().toString();
        int dotIndex = fileName.lastIndexOf('.');

        if(dotIndex > 0){
            return fileName.substring(dotIndex + 1);
        }
        return "";
   }

   private static Path resolveServerRoot() {
        Path[] candidates = {
            Path.of("src", "main", "resources").toAbsolutePath().normalize(),
            Path.of("bin", "src", "main", "resources").toAbsolutePath().normalize(),
            Path.of("target", "classes").toAbsolutePath().normalize()
        };

        for (Path candidate : candidates) {
            if (Files.isDirectory(candidate)) {
                return candidate;
            }
        }

        return Path.of("src", "main", "resources").toAbsolutePath().normalize();
   }

}
