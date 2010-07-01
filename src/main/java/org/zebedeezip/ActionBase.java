package org.zebedeezip;

import javax.swing.*;
import java.net.URL;

public abstract class ActionBase extends AbstractAction {
    protected ActionBase(String name, String iconResourcePath) {
        this(name, createImageIcon(iconResourcePath));
        super.putValue(Action.SHORT_DESCRIPTION, name);
    }

    protected ActionBase(String name) {
        super(name);
    }

    protected ActionBase(String name, Icon icon) {
        super(name, icon);
    }

    public static ImageIcon createImageIcon(String iconResourcePath) {
        URL url = Thread.currentThread().getContextClassLoader().getResource(iconResourcePath);
        if (url == null) {
            throw new InvalidResourceException(iconResourcePath, url);
        }

        return new ImageIcon(url);
    }
}
