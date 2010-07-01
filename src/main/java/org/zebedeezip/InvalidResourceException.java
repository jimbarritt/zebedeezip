package org.zebedeezip;

import java.net.URL;


public class InvalidResourceException extends RuntimeException {
    public InvalidResourceException(String iconResourcePath, URL url) {
        super("InvalidResource: " + iconResourcePath + " : " + url);
    }
}
