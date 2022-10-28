package tplab1.presentation;

import tplab1.application.Dpto;
import tplab1.persistency.DAO;
import tplab1.persistency.exception.NonExistentElement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

public class DptoTablePanel extends JPanel implements ActionListener {

    private DAO<Dpto, Integer> dao;
    private DptoController dptoController;
    private DptoTableModel dptoTableModel = new DptoTableModel();
    private JPanel topPanel = new JPanel();
    private JPanel bottomPanel = new JPanel();
    private JTable dptoTable = new JTable(dptoTableModel);
    private JScrollPane scrollPane = new JScrollPane(dptoTable);
    private JButton deleteButton = new JButton("Eliminar");
    private JButton addButton = new JButton("Agregar");
    private JButton updateButton = new JButton("Modificar");
    private JButton backButton = new JButton("Volver");

    public DptoTablePanel(DAO<Dpto, Integer> dao, DptoController dptoController) {
        super();
        this.dao = dao;
        this.dptoController = dptoController;
        //buildPanel();
    }

    public void buildPanel() {
        BoxLayout verticalLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(verticalLayout);
        addComponents();
        addListeners();
        showUsersList();
    }

    private void addComponents() {
        topPanel.setLayout(new FlowLayout());
        bottomPanel.setLayout(new FlowLayout());
        topPanel.add(scrollPane);
        bottomPanel.add(deleteButton);
        bottomPanel.add(addButton);
        bottomPanel.add(updateButton);
        bottomPanel.add(backButton);
        this.add(topPanel);
        this.add(bottomPanel);
    }

    private void addListeners() {
        deleteButton.addActionListener(deleteButtonEffect());
        addButton.addActionListener(addButtonEffect());
        updateButton.addActionListener(this);
        backButton.addActionListener(this);
    }

    private void showUsersList() {
        dptoTableModel.setContent(getContent());
        dptoTableModel.fireTableDataChanged();
    }

    public ActionListener addButtonEffect() {
        return e -> dptoController.showDptoEditorPanel(null);
    }

    public ActionListener deleteButtonEffect() {
        return e -> {
            int selectedRow = dptoTable.getSelectedRow();
            if (selectedRow >= 0) {
                Integer id = dptoTableModel.getContent().get(selectedRow).getId();
                dao.delete(id);
                dptoTableModel.getContent().remove(selectedRow);
                dptoTableModel.fireTableDataChanged();
            }
        };
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    //TODO pasar a metodo de servicio

    private List<Dpto> getContent() {
        List<Dpto> content;
        try {
            content = dao.getAll();
        } catch (NonExistentElement e) {
            content = Collections.emptyList();
        }
        return content;
    }
}
