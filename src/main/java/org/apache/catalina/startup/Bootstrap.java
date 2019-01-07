package org.apache.catalina.startup;

import org.apache.catalina.connector.HttpConnector;

/**
 * @author paranoidq
 * @since 1.0.0
 */
public class Bootstrap {
    public static void main(String[] args) {
        HttpConnector connector = new HttpConnector();
        connector.start();
    }
}
