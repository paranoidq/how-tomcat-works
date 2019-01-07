package org.apache.catalina.connector;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author paranoidq
 * @since 1.0.0
 */
public class HttpConnector implements Runnable {
    private boolean stopped;
    private String scheme = "http";

    public String getScheme() {
        return scheme;
    }

    public void run() {
        ServerSocket serverSocket = null;
        int port = 8089;
        try {
            serverSocket = new ServerSocket(
                port, 1, InetAddress.getByName("127.0.0.1")
            );
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        while (!stopped) {
            Socket socket;
            try {
                socket = serverSocket.accept();
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            HttpProcessor processor = new HttpProcessor(this);
            processor.process(socket);
        }
    }

    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }
}
