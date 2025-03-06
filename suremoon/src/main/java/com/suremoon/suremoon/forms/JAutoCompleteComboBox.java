package com.suremoon.suremoon.forms;

import javax.swing.*;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.Vector;

/**
 * Aug 23, 2016
 *
 * @author HHL 下拉列表输入自动填充类
 *
 */
public class JAutoCompleteComboBox extends JComboBox {

    private AutoCompleter completer;

    public JAutoCompleteComboBox() {
        super();
        addCompleter();
    }

    public JAutoCompleteComboBox(ComboBoxModel cm) {
        super(cm);
        addCompleter();
    }

    public JAutoCompleteComboBox(ComboBoxModel cm,
                                 ItemListener itemListener) {
        super(cm);
        addCompleter(itemListener);
    }

    private void addCompleter(ItemListener itemListener) {
        setEditable(true);
        completer = new AutoCompleter(this, itemListener);

    }

    public JAutoCompleteComboBox(Object[] items) {
        super(items);
        addCompleter();
    }

    public JAutoCompleteComboBox(List v) {
        super((Vector) v);
        addCompleter();
    }

    private void addCompleter() {
        setEditable(true);
        completer = new AutoCompleter(this);
    }

    public void autoComplete(String str) {
        this.completer.autoComplete(str, str.length());
    }

    public String getText() {
        return ((JTextField) getEditor().getEditorComponent()).getText();
    }

    public void setText(String text) {
        ((JTextField) getEditor().getEditorComponent()).setText(text);
    }

    public boolean containsItem(String itemString) {
        for (int i = 0; i < this.getModel().getSize(); i++) {
            String _item = " " + this.getModel().getElementAt(i);
            if (_item.equals(itemString))
                return true;
        }
        return false;
    }

}