package org.zebedeezip;

public class ZipContentEntry {
    public static final String P_NAME = "name";
    public static final String P_TYPE = "type";
    public static final String P_DIRECTORY = "directory";

    public ZipContentEntry(String name, String type, String directory) {
        _name = name;
        _type = type;
        _directory = directory;
    }

    public String getName() {
        return _name;
    }

    public String getType() {
        return _type;
    }

    public String getDirectory() {
        return _directory;
    }

    private String _name;
    private String _type;
    private String _directory;
}
