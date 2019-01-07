package org.apache.catalina.connector;

import java.io.InputStream;

/**
 * @author paranoidq
 * @since 1.0.0
 */
public class HttpRequest {
    private String uri;
    private InputStream inputStream;

    public HttpRequest(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getRequestURI() {
        return uri;
    }
}
