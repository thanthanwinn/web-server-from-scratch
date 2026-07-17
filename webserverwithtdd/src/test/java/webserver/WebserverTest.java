package webserver;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.ttw.HttpServer;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WebserverTest {

    @BeforeAll
    public static void beforeAll() throws IOException {
        HttpServer webServer = new HttpServer(9000);

        CompletableFuture.runAsync(() -> {
            webServer.start();
            System.out.println("start in before all");
        });

    }
    @Test
    public void testStart() throws IOException {

        Socket clientSocket = new Socket("localhost", 9000);

        assertTrue(clientSocket.isConnected());

    }
}
