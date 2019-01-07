package org.apache.catalina.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author paranoidq
 * @since 1.0.0
 */
public class StringManager {

    private static final Map<String, StringManager> map
        = new HashMap<>();

    public static StringManager getManager (String key) {
        return map.get(key);
    }

    public String getString(String errorKey) {
        return null;
    }
}
