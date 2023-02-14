package tplab1.presentation.dpto;

import tplab1.application.model.Dpto;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class DptoTableModel extends AbstractTableModel {

    private static final int ID_COLUM = 0;
    private static final int DNI_COLUM = 1;
    private static final int FLOOR_COLUM = 2;
    private static final int SIZE_COLUM = 3;
    private static final int NAME_COLUM = 4;
    private static final int SURNAME_COLUM = 5;
    private String[] columNames = {"Dpto", "Dni", "Floor", "Size", "Nombre", "Apellido"};
    private Class[] columTypes = {String.class, String.class, String.class, String.class, String.class, String.class};
    private List<Dpto> content;

    public DptoTableModel() {
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
        Dpto dpto = content.get(rowIndex);
        Object result;
        switch (columnIndex) {
            case ID_COLUM:
                result = dpto.getId();
                break;
            case FLOOR_COLUM:
                result = dpto.getFloor();
                break;
            case SIZE_COLUM:
                result = dpto.getSize();
                break;
            case DNI_COLUM:
                result = dpto.getHabitant().getDni();
                break;
            case NAME_COLUM:
                result = dpto.getHabitant().getName();
                break;
            case SURNAME_COLUM:
                result = dpto.getHabitant().getSurname();
                break;
            default:
                result = "";
        }
        return result;
    }

    public List<Dpto> getContent() {
        return content;
    }

    public void setContent(List<Dpto> content) {
        this.content = content;
    }
}
