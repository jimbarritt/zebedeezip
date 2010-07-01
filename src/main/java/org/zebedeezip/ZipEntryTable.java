package org.zebedeezip;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.util.*;

public class ZipEntryTable extends JPanel {
    public ZipEntryTable(boolean includeDirectory) {
        super(new BorderLayout());
        initUI(includeDirectory);
    }



    private void initUI(boolean includeDirectory) {


        _table = new CustomTable();

//        _table.setFocusCycleRoot(false);
//        _table.setFocusTraversalKeysEnabled(true);

        _table.setShowHorizontalLines(false);
        _table.setShowVerticalLines(true);
        _table.setCellSelectionEnabled(false);
        _table.setRowSelectionAllowed(true);
        _table.setColumnSelectionAllowed(false);
        _table.setGridColor(Color.lightGray);


        _table.setRowHeight(20);
         

        _table.setDefaultRenderer(Object.class, new CustomCellRenderer());
        _table.setDefaultEditor(Object.class,  new TableCellEditor(){
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            public Object getCellEditorValue() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            public boolean isCellEditable(EventObject anEvent) {
                return false;  //To change body of implemented methods use File | Settings | File Templates.
            }

            public boolean shouldSelectCell(EventObject anEvent) {
                return false;  //To change body of implemented methods use File | Settings | File Templates.
            }

            public boolean stopCellEditing() {
                return false;  //To change body of implemented methods use File | Settings | File Templates.
            }

            public void cancelCellEditing() {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            public void addCellEditorListener(CellEditorListener l) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            public void removeCellEditorListener(CellEditorListener l) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });


        _tableModel = new ZipEntryTableModel(includeDirectory);

        _table.setModel(_tableModel);

        JScrollPane sp = new JScrollPane(_table);


        _table.getColumn(ZipEntryTableModel.COL_NAME).setMinWidth(300);
        _table.getColumn(ZipEntryTableModel.COL_NAME).setMaxWidth(10000);
        _table.getColumn(ZipEntryTableModel.COL_NAME).setPreferredWidth(300);
        _table.getColumn(ZipEntryTableModel.COL_NAME).setWidth(300);
        _table.getColumn(ZipEntryTableModel.COL_TYPE).setMinWidth(60);
//        _table.getColumn(ZipEntryTableModel.COL_TYPE).setWidth(40);
        _table.getColumn(ZipEntryTableModel.COL_TYPE).setPreferredWidth(60);


        if (includeDirectory) {
            _table.getColumn(ZipEntryTableModel.COL_DIRECTORY).setMinWidth(600);
            _table.getColumn(ZipEntryTableModel.COL_DIRECTORY).setMaxWidth(20000);
        }

        super.add(sp, BorderLayout.CENTER);
    }


    public void updateEditors() {
        _tableModel.setEntries(_entries);


    }

    public void setEntries(java.util.List entries) {
        _entries = entries;
        updateEditors();
    }

    public CustomTable getTable() {
        return _table;
    }


    public java.util.List getEntries() {
        return _entries;
    }

    public ZipContentEntry getSelectedPlan() {
        return (ZipContentEntry)_entries.get(_table.getSelectedRow());
    }


    private java.util.List _entries;
    private CustomTable _table;
    private ZipEntryTableModel _tableModel;
}
