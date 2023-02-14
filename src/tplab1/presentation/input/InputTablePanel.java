package tplab1.presentation.input;

import tplab1.application.model.Input;
import tplab1.application.service.InputService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class InputTablePanel extends JPanel {

    private InputService inputService;
    private InputController inputController;
    private InputTableModel inputTableModel = new InputTableModel();
    private JPanel topPanel = new JPanel();
    private JPanel bottomPanel = new JPanel();
    private JTable inputTable = new JTable(inputTableModel);
    private JScrollPane scrollPane = new JScrollPane(inputTable);
    private JButton deleteButton = new JButton("Eliminar");
    private JButton addButton = new JButton("Agregar");
    private JButton updateButton = new JButton("Modificar");
    private JButton backButton = new JButton("Volver");

    public InputTablePanel(InputService inputService, InputController inputController) {
        super();
        this.inputService = inputService;
        this.inputController = inputController;
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
        inputTableModel.setContent(inputService.getContentTable());
        inputTableModel.fireTableDataChanged();
    }

    public ActionListener addButtonEffect() {
        return e -> inputController.showInputEditorPanel(null);
    }

    public ActionListener updateButtonEffect() {
        return e -> {
            int selectedRow = inputTable.getSelectedRow();
            if (selectedRow >= 0) {
                Input input = inputTableModel.getContent().get(selectedRow);
                inputController.showInputEditorPanel(input);
            }
        };
    }

    public ActionListener deleteButtonEffect() {
        return e -> {
            int selectedRow = inputTable.getSelectedRow();
            if (selectedRow >= 0) {
                Integer id = inputTableModel.getContent().get(selectedRow).getId();
                inputService.delete(id);
                inputTableModel.getContent().remove(selectedRow);
                inputTableModel.fireTableDataChanged();
            }
        };
    }

    public ActionListener backButtonEffect() {
        return e -> inputController.showMainPanel();
    }
}
