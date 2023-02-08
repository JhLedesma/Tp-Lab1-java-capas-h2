package tplab1.presentation.dpto;

import tplab1.application.Dpto;
import tplab1.application.DptoService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DptoTablePanel extends JPanel {

    private DptoService dptoService;
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

    public DptoTablePanel(DptoService dptoService, DptoController dptoController) {
        super();
        this.dptoService = dptoService;
        this.dptoController = dptoController;
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
        updateButton.addActionListener(updateButtonEffect());
        backButton.addActionListener(backButtonEffect());
    }

    private void showUsersList() {
        dptoTableModel.setContent(dptoService.getContentTable());
        dptoTableModel.fireTableDataChanged();
    }

    public ActionListener addButtonEffect() {
        return e -> dptoController.showDptoEditorPanel(null);
    }

    public ActionListener updateButtonEffect() {
        return e -> {
            int selectedRow = dptoTable.getSelectedRow();
            if (selectedRow >= 0) {
                Dpto dpto = dptoTableModel.getContent().get(selectedRow);
                dptoController.showDptoEditorPanel(dpto);
            }
        };
    }

    public ActionListener deleteButtonEffect() {
        return e -> {
            int selectedRow = dptoTable.getSelectedRow();
            if (selectedRow >= 0) {
                Integer id = dptoTableModel.getContent().get(selectedRow).getId();
                dptoService.delete(id);
                dptoTableModel.getContent().remove(selectedRow);
                dptoTableModel.fireTableDataChanged();
            }
        };
    }

    public ActionListener backButtonEffect() {
        return e -> dptoController.showMainPanel();
    }
}
