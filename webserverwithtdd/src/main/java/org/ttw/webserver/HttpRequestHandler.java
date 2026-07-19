package org.ttw.webserver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class HttpRequestHandler {

    private static final Path SERVER_ROOT = resolveServerRoot();

    HashMap<String,String> extContentType = new HashMap<>();
    public HttpRequestHandler(){
        this.extContentType.put("html", "text/html");
        this.extContentType.put("htm", "text/html");
        this.extContentType.put("css", "text/css");
        this.extContentType.put("", "text/plain");
    }

    public  HttpResponse handle(HttpRequest request){

       HttpResponse response = new HttpResponse(request);

       if(request.getHttpMethod().equals("GET")){
            handleGet(request,response);
       }
       return response;
    }

    private void handleGet(HttpRequest request,HttpResponse response) {
        String url = request.getUrl();

        Path path;
        if("/".equals(url)){
            System.out.println("handle Get /");
            path = SERVER_ROOT.resolve("index.html");
        } else {
            path = SERVER_ROOT.resolve(url.startsWith("/") ? url.substring(1) : url);
        }

        try {
            String extension = this.getExtension(path);
            String contentType = this.extContentType.get(extension);

            String content = Files.readString(path);
            response.setStatusCode("200");
            response.setBody(content);
            response.setHeader("Content-Type", contentType);
        } catch (IOException e){
            System.out.println(e.getMessage());
            response.setStatusCode("500");
        }
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
