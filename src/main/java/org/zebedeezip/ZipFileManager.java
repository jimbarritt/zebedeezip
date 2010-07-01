package org.zebedeezip;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;

public class ZipFileManager {


    public ZipFileManager(File zipFile) throws IOException{
        _zipFile = zipFile;

    }

    private void initEntries() throws IOException{
        _entries = new ArrayList();

        _zip = new ZipFile(_zipFile);

        Enumeration entries = _zip.entries();
        while (entries.hasMoreElements()) {
            ZipEntry entry = (ZipEntry)entries.nextElement();
            if (!entry.isDirectory()) {
                String fullName = entry.getName();
                int index = fullName.lastIndexOf("/");

                String name = (index==-1) ? fullName : fullName.substring(index);
                String directory = (index==-1) ? "/" : "/" + fullName.substring(0, index);
                int indexDot = name.lastIndexOf(".");
                String type = name.substring(indexDot+1);
                int startIndex = (index==-1) ? 0 : 1;
                name = name.substring(startIndex, name.length());
                ZipContentEntry contentEntry = new ZipContentEntry(name, type, directory);
                _entries.add(contentEntry);
            }
        }
    }

    public void open() throws IOException {
         if (log.isInfoEnabled()) {
             log.info("Opening file: " + _zipFile.getAbsolutePath());
         }
        initEntries();
    }

    public void close() throws IOException {
        if (log.isInfoEnabled()) {
            log.info("Closing File: "+ _zipFile.getAbsolutePath());
        }
        if (_zip != null) {
            _zip.close();
        }

    }

    public List getEntries() {
        return _entries;
    }

    private static final Logger log = Logger.getLogger(ZipFileManager.class);
    private File _zipFile;
    private List _entries = new ArrayList();
    private ZipFile _zip;
}
