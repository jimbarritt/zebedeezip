package org.zebedeezip;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;

public class ZipFileContents {

    private static final Logger log = Logger.getLogger(ZipFileContents.class);

    private final File sourceFile;
        
    private ZipFile zipfile;
    private List entries = new ArrayList();

    public ZipFileContents(File sourceFile) throws IOException {
        this.sourceFile = sourceFile;        
    }

    public void open() throws IOException {
        if (log.isInfoEnabled()) {
            log.info("Opening file: " + sourceFile.getAbsolutePath());
        }
        initEntries();
    }

    public List getEntries() {
        return entries;
    }

    public void close() throws IOException {
        if (log.isInfoEnabled()) {
            log.info("Closing File: " + sourceFile.getAbsolutePath());
        }
        if (zipfile != null) {
            zipfile.close();
        }
    }

    private void initEntries() throws IOException {
        entries = new ArrayList();

        zipfile = new ZipFile(sourceFile);

        Enumeration entries = zipfile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            if (!entry.isDirectory()) {
                String fullName = entry.getName();
                int index = fullName.lastIndexOf("/");

                String name = (index == -1) ? fullName : fullName.substring(index);
                String directory = (index == -1) ? "/" : "/" + fullName.substring(0, index);
                int indexDot = name.lastIndexOf(".");
                String type = name.substring(indexDot + 1);
                int startIndex = (index == -1) ? 0 : 1;
                name = name.substring(startIndex, name.length());
                ZipContentEntry contentEntry = new ZipContentEntry(name, type, directory);
                this.entries.add(contentEntry);
            }
        }
    }


}
