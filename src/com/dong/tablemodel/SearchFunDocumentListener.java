/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dong.tablemodel;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 * this class implements {@link javax.swing.event.DocumentListener}.
 * this class is registed as the document listener of
 * {@link com.dong.tablemodel.XTablePanel#searchText}
 * when the contents of {@link com.dong.tablemodel.XTablePanel#searchText}
 * changed,this class will be called.
 * @author Dong Gang
 * @version 1.0 2011-06-12
 * @since 1.6
 *
 */
public class SearchFunDocumentListener implements DocumentListener {

    /**
     * internal table.
     */
    JTable table;
    /**
     * internal tablemodel.
     */
    DefaultTableModel tableModel;
    /**
     * table sorter.
     */
    TableRowSorter<TableModel> sorter;
    /**
     * search text.
     */
    JTextField searchText;

    /**
     * contructor method. initialize class member.
     */
    SearchFunDocumentListener(JTable table, DefaultTableModel tableModel, JTextField textField) {
        this.table = table;
        this.tableModel = tableModel;
        sorter = new TableRowSorter<TableModel>(tableModel);
        this.table.setRowSorter(sorter);
        this.searchText = textField;
    }

    /**
     * override of {@link javax.swing.event.DocumentListener#insertUpdate(javax.swing.event.DocumentEvent) },
     * when any contents of {@link #searchText } is inserted, make sort of row.
     */
    @Override
    public void insertUpdate(DocumentEvent e) {

        if (searchText.getText().length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter(searchText.getText()));
        }

    }

    /**
     * override of {@link javax.swing.event.DocumentListener#removeUpdate(javax.swing.event.DocumentEvent)},
     * when any contents of {@link #searchText } is removed, make sort of row.
     */
    @Override
    public void removeUpdate(DocumentEvent e) {
        if (searchText.getText().length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter(searchText.getText()));
        }
    }

    /**
     * override of {@link javax.swing.event.DocumentListener#changedUpdate(javax.swing.event.DocumentEvent) },
     * blank realization, do nothing.
     */
    @Override
    public void changedUpdate(DocumentEvent e) {
    }
}

