package tplab1.presentation;

import tplab1.application.Dpto;
import tplab1.persistency.DAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DptoEditorPanel extends JPanel {

    private DAO<Dpto, Integer> dao;
    private DptoController dptoController;
    private Dpto dpto;
    private JPanel topPanel = new JPanel();
    private JPanel bottomPanel = new JPanel();
    private JLabel dptoLabel = new JLabel("Unidad");
    private JLabel nameLabel = new JLabel("Nombre");
    private JLabel surnameLabel = new JLabel("Apellido");
    private JTextField dptoField = new JTextField(10);
    private JTextField nameField = new JTextField(10);
    private JTextField surnameField = new JTextField(10);
    private JButton clearButton = new JButton("Limpiar");
    private JButton saveButton = new JButton("Guardar");
    private JButton cancelButton = new JButton("Cancel");

    public DptoEditorPanel(DAO<Dpto, Integer> dao, DptoController dptoController) {
        super();
        this.dao = dao;
        this.dptoController = dptoController;
    }

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
            Dpto dpto = new Dpto(Integer.parseInt(dptoField.getText()), nameField.getText(), surnameField.getText());
            dao.save(dpto);
            dptoController.showDptoTablePanel();
        };
    }

    public ActionListener execCancelButton() {
        return e -> {
        };
    }

    public void setFields() {
        if (dpto != null) {
            dptoField.setText(dpto.getId().toString());
            nameField.setText(dpto.getName());
            surnameField.setText(dpto.getSurname());
        } else {
            dptoField.setText("");
            nameField.setText("");
            surnameField.setText("");
        }
    }

    private void addComponents() {
        topPanel.setLayout(new FlowLayout());
        bottomPanel.setLayout(new FlowLayout());
        topPanel.add(dptoLabel);
        topPanel.add(dptoField);
        topPanel.add(nameLabel);
        topPanel.add(nameField);
        topPanel.add(surnameLabel);
        topPanel.add(surnameField);
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
