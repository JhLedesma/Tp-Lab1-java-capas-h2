package tplab1.presentation.dpto;

import tplab1.application.model.Dpto;
import tplab1.application.service.DptoService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DptoEditorPanel extends JPanel {

    private DptoService dptoService;
    private DptoController dptoController;
    private Dpto dpto;
    private JLabel dptoLabel = new JLabel("Unidad");
    private JLabel dniLabel = new JLabel("Dni");
    private JLabel nameLabel = new JLabel("Nombre");
    private JLabel surnameLabel = new JLabel("Apellido");
    private JTextField dptoField = new JTextField(10);
    private JTextField dniField = new JTextField(10);
    private JTextField nameField = new JTextField(10);
    private JTextField surnameField = new JTextField(10);
    private JComboBox dptoBox = new JComboBox();
    private JButton clearButton = new JButton("Limpiar");
    private JButton saveButton = new JButton("Guardar");
    private JButton cancelButton = new JButton("Cancel");

    public DptoEditorPanel(DptoService dptoService, DptoController dptoController) {
        super();
        this.dptoService = dptoService;
        this.dptoController = dptoController;
    }

// Pantalla principal
// Boton volver de dpto table
// validacion de campos vacios

    public void buildPanel() {
        BoxLayout verticalLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(verticalLayout);
        setFields();
        addComponents();
        addListeners();
    }

    public ActionListener execClearButton() {
        return e -> setFields();
    }

    public ActionListener execSaveButton() {
        return e -> {
            String id = dpto == null ? dptoBox.getSelectedItem().toString() : dptoField.getText();
            dptoService.save(id, dniField.getText(), nameField.getText(), surnameField.getText());
            dptoController.showDptoTablePanel();
        };
    }

    public ActionListener execCancelButton() {
        return e -> dptoController.showDptoTablePanel();
    }

    public void setFields() {
        if (dpto != null) {
            dptoBox.setVisible(false);
            dptoField.setVisible(true);
            dptoField.setEnabled(false);
            dptoField.setText(dpto.getId().toString());
            dniField.setText(dpto.getHabitant().getDni().toString());
            nameField.setText(dpto.getHabitant().getName());
            surnameField.setText(dpto.getHabitant().getSurname());
        } else {
            dptoBox.setVisible(true);
            dptoField.setVisible(false);
            dptoBox.setModel(new DefaultComboBoxModel(dptoService.getAvailablesIds()));
            dniField.setText("");
            nameField.setText("");
            surnameField.setText("");
        }
    }

    private void addComponents() {
        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        topPanel.setLayout(new GridLayout(2, 2));
        bottomPanel.setLayout(new FlowLayout());

        JPanel dptoPanel = new JPanel();
        dptoPanel.setLayout(new FlowLayout());
        dptoBox.setPreferredSize(new Dimension(150, dptoBox.getPreferredSize().height));
        dptoPanel.add(dptoLabel);
        dptoPanel.add(dptoBox);
        dptoPanel.add(dptoField);
        JPanel dniPanel = new JPanel();
        dniPanel.setLayout(new FlowLayout());
        dniPanel.add(dniLabel);
        dniPanel.add(dniField);
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout());
        namePanel.add(nameLabel);
        namePanel.add(nameField);
        JPanel surnamePanel = new JPanel();
        surnamePanel.setLayout(new FlowLayout());
        surnamePanel.add(surnameLabel);
        surnamePanel.add(surnameField);

        topPanel.add(dptoPanel);
        topPanel.add(dniPanel);
        topPanel.add(namePanel);
        topPanel.add(surnamePanel);

        bottomPanel.add(clearButton);
        bottomPanel.add(saveButton);
        bottomPanel.add(cancelButton);

        this.add(topPanel);
        this.add(bottomPanel);
    }

    private void addListeners() {
        clearButton.addActionListener(execClearButton());
        saveButton.addActionListener(execSaveButton());
        cancelButton.addActionListener(execCancelButton());
    }

    public void setDpto(Dpto dpto) {
        this.dpto = dpto;
    }
}
