package webserver;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.CompletableFuture;

import org.junit.BeforeClass;
import org.junit.Test;
import org.ttw.HttpServer;

public class WebserverTest {

    @BeforeClass
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
