package tplab1.presentation;

import tplab1.application.Dpto;
import tplab1.persistency.DAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class UserEditorPanel extends JPanel {

    private DAO<Dpto, String> dao;
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

    public UserEditorPanel(DAO<Dpto, String> dao) {
        super();
        this.dao = dao;
    }

    public UserEditorPanel(DAO<Dpto, String> dao, Dpto dpto) {
        super();
        this.dao = dao;
        this.dpto = dpto;
    }

    private void buildPanel() {
        BoxLayout verticalLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(verticalLayout);
        buildFields();
        addComponents();
        addListeners();
    }

    private void addComponents() {
        topPanel.setLayout(new FlowLayout());
        bottomPanel.setLayout(new FlowLayout());
        topPanel.add(dptoLabel);
        topPanel.add(dptoLabel);
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

    private void buildFields() {
        if (dpto != null) {
            dptoField.setText("dpto info");
            dptoField.setEditable(true);
            nameField.setText(dpto.getName());
            surnameField.setText(dpto.getSurname());
        }
    }

    private void addListeners() {
        clearButton.addActionListener(execClearButton());
        saveButton.addActionListener(execSaveButton());
        cancelButton.addActionListener(execCancelButton());
    }

    public ActionListener execClearButton() {
        return e -> {
            dptoField.setText("");
            nameField.setText("");
            surnameField.setText("");
        };
    }

    public ActionListener execSaveButton() {
        return e -> {
            Dpto dpto = new Dpto(nameField.getText(), surnameField.getText());
            dao.save(dpto);
        };
    }

    public ActionListener execCancelButton() {
        return e -> {
        };
    }
}
