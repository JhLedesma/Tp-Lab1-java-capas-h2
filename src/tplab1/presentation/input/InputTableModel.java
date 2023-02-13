package tplab1.presentation.input;

import tplab1.application.model.Input;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class InputTableModel extends AbstractTableModel {

    private static final int DPTO_COLUM = 0;
    private static final int DESCRIPTION_COLUM = 1;
    private static final int AMOUNT_COLUM = 2;
    private static final int DATE_COLUM = 3;
    private String[] columNames = {"Dpto", "Descripcion", "Valor", "Fecha"};
    private Class[] columTypes = {String.class, String.class, String.class, String.class};
    private List<Input> content;

    public InputTableModel() {
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
        Input input = content.get(rowIndex);
        Object result;
        switch (columnIndex) {
            case DPTO_COLUM:
                result = input.getDptoId();
                break;
            case DESCRIPTION_COLUM:
                result = input.getDescription();
                break;
            case AMOUNT_COLUM:
                result = input.getAmount();
                break;
            case DATE_COLUM:
                result = input.getDate();
                break;
            default:
                result = "";
        }
        return result;
    }

    public List<Input> getContent() {
        return content;
    }

    public void setContent(List<Input> content) {
        this.content = content;
    }
}
