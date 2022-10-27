package tplab1.presentation;

import tplab1.application.User;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class UserTableModel extends AbstractTableModel {

    private static final int NAME_COLUM = 0;
    private static final int SURNAME_COLUM = 1;
    private String[] columNames = {"Name", "Surname"};
    private Class[] columTypes = {String.class, String.class};
    private List<User> content;

    public UserTableModel() {
        this.content = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return content.size();
    }

    @Override
    public int getColumnCount() {
        return columNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columTypes[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User user = content.get(rowIndex);
        Object result = null;
        switch (columnIndex) {
            case NAME_COLUM:
                result = user.getName();
                break;
            case SURNAME_COLUM:
                result = user.getSurname();
                break;
            default:
                result = "";
        }
        return result;
    }

    public List<User> getContent() {
        return content;
    }

    public void setContent(List<User> content) {
        this.content = content;
    }
}
