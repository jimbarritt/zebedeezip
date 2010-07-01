package org.zebedeezip;

import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.*;

public class CustomTable extends JTable {


    protected boolean processKeyBinding(KeyStroke ks, KeyEvent e, int condition, boolean pressed) {
        boolean retVal = true;
        boolean forwards = (!e.isShiftDown());


        if (e.getID() == KeyEvent.KEY_PRESSED && e.getKeyCode() == 9) { // TAB
            moveToNextComponent(forwards);
//            _isTabbing = true;
//            moveToNextCell(getSelectedRow(), getSelectedColumn(), forwards);
//            editCellAt(getSelectedRow(), getSelectedColumn(), null);
        }else if (e.getID() == KeyEvent.KEY_PRESSED && e.getKeyCode() == 9) { // SHIFT_TAB
            moveToNextComponent(forwards);
//            _isTabbing = true;
//            moveToNextCell(getSelectedRow(), getSelectedColumn(), forwards);
//            editCellAt(getSelectedRow(), getSelectedColumn(), null);
        }else if (e.getID() == KeyEvent.KEY_PRESSED && e.getKeyCode() == 10) { // ENTER
//            moveToNextCell(getSelectedRow(), getSelectedColumn(), forwards);
//            editCellAt(getSelectedRow(), getSelectedColumn(), null);

            fireActionPerformed();
        } else {
            retVal = super.processKeyBinding(ks, e, condition, pressed);
        }

        return retVal;
    }



    private void fireActionPerformed() {
        ActionEvent evt = new ActionEvent(this, CustomTable.EVENT_ENTER_PRESSED, "enterPressed");
        if (_enterAction != null) {
            _enterAction.actionPerformed(evt);
        }

        for (Iterator itr = _actionListeners.iterator(); itr.hasNext();) {
            ActionListener l = (ActionListener)itr.next();
            l.actionPerformed(evt);

        }
    }

    private void moveToNextComponent(boolean forwards) {

        Container cycleRoot = super.getFocusCycleRootAncestor();
        FocusTraversalPolicy policy = super.getFocusTraversalPolicy();
        if (policy == null && cycleRoot != null) {
            policy = cycleRoot.getFocusTraversalPolicy();
        }

        if (policy != null) {

            Component target;
            if (forwards) {
                target  = policy.getComponentAfter(cycleRoot, this);
            } else {
                target = policy.getComponentBefore(cycleRoot, this);
            }

            if (target != null) target.requestFocusInWindow();
        }
    }



    public void addActionListener(ActionListener l) {
        _actionListeners.add(l);
    }

    public void removeActionListener(ActionListener l) {
        _actionListeners.remove(l);
    }


    private static final Logger log = Logger.getLogger(CustomTable.class);
    private Action _enterAction;
    private static final int EVENT_ENTER_PRESSED = 99;
    private java.util.List _actionListeners = new ArrayList();
}
