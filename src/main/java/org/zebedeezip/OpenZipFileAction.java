package org.zebedeezip;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;


public class OpenZipFileAction extends ActionBase {

    public static final String NAME = "openZipFile";

    public OpenZipFileAction(ZebedeeFrame parent) {
        super(NAME, "icon/open.gif");
        _parent = parent;
    }

    public void actionPerformed(ActionEvent e) {
        try {
            JFileChooser fileChooser = new JFileChooser(_parent.getStartLocation());

            fileChooser.setFileFilter(FILE_FILTER);
            fileChooser.showOpenDialog(_parent);

            if (fileChooser.getSelectedFile() != null) {
                File file = fileChooser.getSelectedFile();
                _parent.openZip(file);
            }
        } catch (IOException e1) {
            throw new RuntimeException(e1);
        }


    }

    private static final FileFilter FILE_FILTER = new FileFilter() {
        public boolean accept(File f) {
            return f.getName().endsWith(".zip");
        }

        public String getDescription() {
            return "Zip files (*.zip)";
        }
    } ;

    private ZebedeeFrame _parent;
}
