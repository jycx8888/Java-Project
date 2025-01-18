/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OOP;

import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Justin Yong
 */
public class CustomTableModel extends DefaultTableModel {
    private List<String> passwords;

    public CustomTableModel(Object[] columnNames, int rowCount, List<String> passwords) {
        super(columnNames, rowCount);
        this.passwords = passwords;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false; // Make cells non-editable
    }

    @Override
    public Object getValueAt(int row, int column) {
        if (column == 4) { // Mask the password column
            return "********";
        }
        return super.getValueAt(row, column);
    }
}
