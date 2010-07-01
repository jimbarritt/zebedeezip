package org.zebedeezip;

import javax.swing.table.DefaultTableModel;
import javax.swing.event.TableModelEvent;
import java.util.List;
import java.util.ArrayList;

public class ZipEntryTableModel extends DefaultTableModel {

    public ZipEntryTableModel(boolean includeDirectory) {
        super();

        super.addColumn(COL_NAME);
        super.addColumn(COL_TYPE);

        if (includeDirectory) {
            super.addColumn(COL_DIRECTORY);
        }
    }

    public void setEntries(List entries) {
        _entries = entries;
        fireTableChanged(new TableModelEvent(this));
    }

    public Object getValueAt(int row, int column) {
        ZipContentEntry file = (ZipContentEntry)_entries.get(row);

        Object value = null;
        if (column == 0) {
            value = file.getName();
        } else if (column == 1){
            value = file.getType();
        } else if (column == 2) {
            value = file.getDirectory();
        }
        return value;
    }

    public int getRowCount() {
        if (_entries != null) {
            return _entries.size();
        } else {
            return 0;
        }
    }

    public static final String COL_NAME = "Name";
    public static final String COL_TYPE = "Type";
    public static final String COL_DIRECTORY = "Directory";

    private List _entries = new ArrayList();

}
