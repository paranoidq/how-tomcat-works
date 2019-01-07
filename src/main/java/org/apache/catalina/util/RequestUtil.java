package org.apache.catalina.util;

import javax.servlet.http.Cookie;
import java.util.ArrayList;

/**
 * @author paranoidq
 * @since 1.0.0
 */
public class RequestUtil {

    public static Cookie[] parseCookieHeader(String header) {
        if (header == null || header.length() < 1) {
            return new Cookie[0];
        }
        ArrayList<Cookie> cookies = new ArrayList<>();
        while (header.length() > 0) {
            int semiColon = header.indexOf(";");
            if (semiColon < 0) {
                semiColon = header.length();
            }
            if (semiColon == 0) {
                break;
            }
            String token = header.substring(0, semiColon);
            if (semiColon < header.length()) {
                header = header.substring(semiColon + 1);
            } else {
                header = "";
            }
            try {
                int equals = token.indexOf("=");
                if (equals > 0) {
                    String name = token.substring(0, equals).trim();
                    String value = token.substring(equals + 1).trim();
                    cookies.add(new Cookie(name, value));
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        return cookies.toArray(new Cookie[cookies.size()]);
    }
}
