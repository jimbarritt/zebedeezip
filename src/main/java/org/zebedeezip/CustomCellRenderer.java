package org.zebedeezip;

import javax.swing.table.TableCellRenderer;
import javax.swing.*;
import java.awt.*;

public class CustomCellRenderer implements TableCellRenderer {


    public CustomCellRenderer() {
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        //System.out.println("getTableCellRendererComponent <" + (String)value + ">, <"+ isSelected + ">, <" + hasFocus + ">");
        //_label = new JLabel();
        _label.setOpaque(true);
        _label.setFont(table.getFont());
        _label.setBorder(BorderFactory.createEmptyBorder(1, 10, 1, 1));

        // Do stripey stripes ...
        if (_drawStripes) {
            if (row % 2 == 0) {
                _label.setBackground(table.getBackground());
            } else {
                _label.setBackground(Color.lightGray);
            }
        }


        if (hasFocus && _showCellFocus) {
            _label.setBorder(BorderFactory.createLineBorder(Color.blue, 1));

            _label.setBackground(_focusColor);
        }

        if (isSelected) {
            _label.setBackground(_selectedBackgroundColor);
            _label.setForeground(_selectedForegroundColor);
        }   else {
            _label.setBackground(_backgroundColor);
            _label.setForeground(_foreGroundColor);
        }

        _label.setText((String)value);


        return _label;
    }

    private JLabel _label = new JLabel();

    private Font _normalFont = _label.getFont();
    private Font _nullValueFont = new Font("Courier New", Font.PLAIN, 12);
    private boolean _drawStripes = false;
    private boolean _showCellFocus = false;
    private Color _selectedForegroundColor = Color.white;
    private Color _selectedBackgroundColor = Color.gray;
    private Color _backgroundColor = _label.getBackground();
    private Color _foreGroundColor = _label.getForeground();
    private Color _focusColor = Color.cyan;
}
