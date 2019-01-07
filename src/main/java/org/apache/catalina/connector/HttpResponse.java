package org.apache.catalina.connector;

import java.io.OutputStream;

/**
 * @author paranoidq
 * @since 1.0.0
 */
public class HttpResponse {
    private OutputStream outputStream;

    public HttpResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void setRequest(HttpRequest request) {

    }

    public void setHeader(String key, String value) {

    }
}
