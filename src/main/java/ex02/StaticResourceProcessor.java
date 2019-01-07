package ex02;

import java.io.IOException;

/**
 * @author paranoidq
 * @since 1.0.0
 */
public class StaticResourceProcessor {

    public void process(Request request, Response response) {
        try {
            response.sendStaticResource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
