package ex02;

import ex01.HttpServer;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.*;
import java.util.Locale;

/**
 * @author paranoidq
 * @since 1.0.0
 */
public class Response implements ServletResponse {
    private Request request;
    private OutputStream outputStream;
    private PrintWriter printWriter;

    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void sendStaticResource() throws IOException {
        FileInputStream fis = null;
        try {
            File file = new File(HttpServer.WEB_ROOT, request.getUri());
            if (file.exists()) {
                fis = new FileInputStream(file);
//                int ch = fis.read(bytes, 0, BUFFER_SIZE);
//                while (ch != -1) {
//                    outputStream.write(bytes, 0, ch);
//                    ch = fis.read(bytes, 0, BUFFER_SIZE);
//                }
                byte[] data = IOUtils.toByteArray(fis);
                // 对于浏览器而言，正常的报文也必须要满足HTTP协议的规范
                String header = "HTTP/1.1 200 OK\r\n" +
                    "Content-Type: text/html\r\n" +
                    "\r\n";
                byte[] msg = ArrayUtils.addAll(header.getBytes(), data);
                IOUtils.write(msg, outputStream);
            } else {
                String errorMsg = "" +
                    "HTTP/1.1 404 File Not Found\r\n" +
                    "Content-Type: text/html\r\n" +
                    "\r\n" +
                    "<h1>File Not Found</h1>";
                outputStream.write(errorMsg.getBytes());
            }
            outputStream.flush();
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
    }

    public PrintWriter getPrintWriter() {
        this.printWriter = new PrintWriter(outputStream, true);
        return printWriter;
    }


    public String getCharacterEncoding() {
        return null;
    }

    public String getContentType() {
        return null;
    }

    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }

    public PrintWriter getWriter() throws IOException {
        return null;
    }

    public void setCharacterEncoding(String charset) {

    }

    public void setContentLength(int len) {

    }

    public void setContentLengthLong(long len) {

    }

    public void setContentType(String type) {

    }

    public void setBufferSize(int size) {

    }

    public int getBufferSize() {
        return 0;
    }

    public void flushBuffer() throws IOException {

    }

    public void resetBuffer() {

    }

    public boolean isCommitted() {
        return false;
    }

    public void reset() {

    }

    public void setLocale(Locale loc) {

    }

    public Locale getLocale() {
        return null;
    }
}
