package org.apache.catalina.connector;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author paranoidq
 * @since 1.0.0
 */
public class SocketInputStream extends InputStream{
    private InputStream inputStream;
    private int backLog;

    public SocketInputStream(InputStream inputStream, int backLog) {
        this.inputStream = inputStream;
        this.backLog = backLog;
    }

    @Override
    public int read() throws IOException {
        return 0;
    }

    public void readRequestLine(HttpRequestLine requestLine) {

    }

    public void readHeader(HttpHeader header) {

    }
}
