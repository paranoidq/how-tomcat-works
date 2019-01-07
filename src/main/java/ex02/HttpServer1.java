package ex02;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author paranoidq
 * @since 1.0.0
 */
public class HttpServer1 {
    public static final String WEB_ROOT = "/Users/paranoidq/how-tomcat-works/webroot";
    private static final String SHUTDOWN_COMMAND = "/shutdown";

    private volatile boolean shutdown = false;

    public static void main(String[] args) {
        HttpServer1 httpServer = new HttpServer1();
        httpServer.listen();
    }

    public void listen() {
        ServerSocket serverSocket = null;
        int port = 8089;
        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        while (!shutdown) {
            Socket socket = null;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                socket = serverSocket.accept();
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();

                Request request = new Request(inputStream);
                request.parse();

                System.out.println(request.getUri());

                Response response = new Response(outputStream);
                response.setRequest(request);

                if (request.getUri().startsWith("/servlet/")) {
                    ServletProcessor1 processor = new ServletProcessor1();
                    processor.process(request, response);
                } else {
                    StaticResourceProcessor processor = new StaticResourceProcessor();
                    processor.process(request, response);
                }

                response.sendStaticResource();


                socket.close();
                shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
    }
}
