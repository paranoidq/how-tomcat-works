package ex01;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author paranoidq
 * @since 1.0.0
 *
 * HTTP Response =
 * status + line
 * CRLF
 * message-body
 */
public class Response {
    Request request;
    OutputStream outputStream;

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
}
