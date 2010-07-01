package org.zebedeezip;

import javax.swing.*;

public class ZebedeeToolbar extends JToolBar {

    public ZebedeeToolbar(ZebedeeFrame parent) {
        super("Main");
        super.setFloatable(false);
        super.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        initActions(parent);

    }

    private void initActions(ZebedeeFrame parent) {
       JButton btn = super.add(new OpenZipFileAction(parent));
//        btn.setBorder(BorderFactory.createEmptyBorder());

    }
}
