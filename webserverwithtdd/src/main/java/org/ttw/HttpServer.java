package org.ttw;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    int port;
    ServerSocket socket;

    public HttpServer(int port) throws IOException {

        this.port= port;
        this.init();
    }

    public void init() {
        try {
            this.socket = new ServerSocket(this.port);
            System.out.println("server listen at"+ this.port);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void start(){
        try {
            Socket clientSocket =  this.socket.accept();
            System.out.println("Client socket" + clientSocket.isBound());

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Start done");
    }


}
