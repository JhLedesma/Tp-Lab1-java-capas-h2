package tplab1.presentation.output;

import tplab1.application.model.Output;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class OutputTableModel extends AbstractTableModel {

    private static final int DESCRIPTION_COLUM = 0;
    private static final int AMOUNT_COLUM = 1;
    private static final int DATE_COLUM = 2;
    private String[] columNames = {"Descripcion", "Valor", "Fecha"};
    private Class[] columTypes = {String.class, String.class, String.class};
    private List<Output> content;

    public OutputTableModel() {
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
        Output output = content.get(rowIndex);
        Object result;
        switch (columnIndex) {
            case DESCRIPTION_COLUM:
                result = output.getDescription();
                break;
            case AMOUNT_COLUM:
                result = output.getAmount();
                break;
            case DATE_COLUM:
                result = output.getDate();
                break;
            default:
                result = "";
        }
        return result;
    }

    public List<Output> getContent() {
        return content;
    }

    public void setContent(List<Output> content) {
        this.content = content;
    }
}
