package tplab1.presentation.output;

import tplab1.application.model.Output;
import tplab1.application.service.OutputService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class OutputTablePanel extends JPanel {

    private OutputService outputService;
    private OutputController outputController;
    private OutputTableModel outputTablePanel = new OutputTableModel();
    private JPanel topPanel = new JPanel();
    private JPanel bottomPanel = new JPanel();
    private JTable inputTable = new JTable(outputTablePanel);
    private JScrollPane scrollPane = new JScrollPane(inputTable);
    private JButton deleteButton = new JButton("Eliminar");
    private JButton addButton = new JButton("Agregar");
    private JButton updateButton = new JButton("Modificar");
    private JButton backButton = new JButton("Volver");

    public OutputTablePanel(OutputService outputService, OutputController outputController) {
        super();
        this.outputService = outputService;
        this.outputController = outputController;
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
        outputTablePanel.setContent(outputService.getContentTable());
        outputTablePanel.fireTableDataChanged();
    }

    public ActionListener addButtonEffect() {
        return e -> outputController.showOutputEditorPanel(null);
    }

    public ActionListener updateButtonEffect() {
        return e -> {
            int selectedRow = inputTable.getSelectedRow();
            if (selectedRow >= 0) {
                Output output = outputTablePanel.getContent().get(selectedRow);
                outputController.showOutputEditorPanel(output);
            }
        };
    }

    public ActionListener deleteButtonEffect() {
        return e -> {
            int selectedRow = inputTable.getSelectedRow();
            if (selectedRow >= 0) {
                Integer id = outputTablePanel.getContent().get(selectedRow).getId();
                outputService.delete(id);
                outputTablePanel.getContent().remove(selectedRow);
                outputTablePanel.fireTableDataChanged();
            }
        };
    }

    public ActionListener backButtonEffect() {
        return e -> outputController.showMainPanel();
    }
}
