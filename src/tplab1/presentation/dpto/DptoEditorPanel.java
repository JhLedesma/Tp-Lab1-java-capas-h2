package tplab1.presentation.dpto;

import tplab1.application.model.Dpto;
import tplab1.application.service.DptoService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class DptoEditorPanel extends JPanel {

    private DptoService dptoService;
    private DptoController dptoController;
    private Dpto dpto;
    private JLabel dptoLabel = new JLabel("Unidad");
    private JLabel floorLabel = new JLabel("Floor");
    private JLabel sizeLabel = new JLabel("Size");
    private JLabel dniLabel = new JLabel("Dni");
    private JLabel nameLabel = new JLabel("Nombre");
    private JLabel surnameLabel = new JLabel("Apellido");
    private JTextField dptoField = new JTextField(10);
    private JTextField floorField = new JTextField(1);
    private JTextField sizeField = new JTextField(3);
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
            dptoService.save(id, floorField.getText(), sizeField.getText(), dniField.getText(), nameField.getText(), surnameField.getText());
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
            floorField.setText(dpto.getFloor());
            sizeField.setText(dpto.getSize().toString());
            dniField.setText(dpto.getHabitant().getDni().toString());
            nameField.setText(dpto.getHabitant().getName());
            surnameField.setText(dpto.getHabitant().getSurname());
        } else {
            dptoBox.setVisible(true);
            dptoField.setVisible(false);
            dptoBox.setModel(new DefaultComboBoxModel(dptoService.getAvailablesIds()));
            floorField.setText("");
            sizeField.setText("");
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

        JPanel dptoPanel = createEditPanel(dptoLabel, dptoField);
        dptoBox.setPreferredSize(new Dimension(150, dptoBox.getPreferredSize().height));
        dptoPanel.add(dptoBox);
        JPanel floorPanel = createEditPanel(floorLabel, floorField);
        JPanel sizePanel = createEditPanel(sizeLabel, sizeField);
        JPanel dniPanel = createEditPanel(dniLabel, dniField);
        JPanel namePanel = createEditPanel(nameLabel, nameField);
        JPanel surnamePanel = createEditPanel(surnameLabel, surnameField);

        addPanelToPanel(Arrays.asList(dptoPanel, floorPanel, sizePanel, dniPanel, namePanel, surnamePanel), topPanel);
        addButtonToPanel(Arrays.asList(clearButton, saveButton, cancelButton), bottomPanel);

        this.add(topPanel);
        this.add(bottomPanel);
    }

    private void addListeners() {
        clearButton.addActionListener(execClearButton());
        saveButton.addActionListener(execSaveButton());
        cancelButton.addActionListener(execCancelButton());
    }

    private JPanel createEditPanel(JLabel label, JTextField field) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(label);
        panel.add(field);
        return panel;
    }

    private void addPanelToPanel(List<JPanel> childPanels, JPanel panel) {
        childPanels.forEach(panel::add);
    }

    private void addButtonToPanel(List<JButton> childButtons, JPanel panel) {
        childButtons.forEach(panel::add);
    }

    public void setDpto(Dpto dpto) {
        this.dpto = dpto;
    }
}
