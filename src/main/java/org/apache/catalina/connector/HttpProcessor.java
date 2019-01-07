package org.apache.catalina.connector;

import org.apache.catalina.core.ServletProcessor;
import org.apache.catalina.core.StaticResourceProcessor;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author paranoidq
 * @since 1.0.0
 */
public class HttpProcessor {

    private HttpConnector connector;
    private HttpRequest request;
    private HttpResponse response;

    private HttpRequestLine requestLine = new HttpRequestLine();


    public HttpProcessor(HttpConnector connector) {
        this.connector = connector;
    }

    public void process(Socket socket) {
        SocketInputStream inputStream;
        OutputStream outputStream;
        try {
            inputStream = new SocketInputStream(
                socket.getInputStream(), 2048);
            outputStream = socket.getOutputStream();
            request = new HttpRequest(inputStream);
            response = new HttpResponse(outputStream);
            response.setRequest(request);
            response.setHeader("Server", "Prymont Servlet Container");

            parseRequest(inputStream, outputStream);
            parseHeader(outputStream);

            if (request.getRequestURI().startsWith("/servlet/")) {
                ServletProcessor processor = new ServletProcessor();
                processor.process(request, response);
            } else {
                StaticResourceProcessor processor = new StaticResourceProcessor();
                processor.process(request, response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void parseRequest(SocketInputStream inputStream, OutputStream outputStream) {
        // 让socketInputStream填入一个requestLine的实例
        inputStream.readRequestLine(requestLine);
        String method = new String(requestLine.method);
    }

    private void parseHeader(OutputStream outputStream) {

    }
}
