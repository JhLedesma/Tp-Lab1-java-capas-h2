package tplab1.presentation;

import tplab1.application.User;
import tplab1.persistency.DAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class UserEditorPanel extends JPanel {

    private DAO<User, String> dao;
    private User user;
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

    public UserEditorPanel(DAO<User, String> dao) {
        super();
        this.dao = dao;
    }

    public UserEditorPanel(DAO<User, String> dao, User user) {
        super();
        this.dao = dao;
        this.user = user;
    }

    private void buildPanel() {
        BoxLayout verticalLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(verticalLayout);
        buildFields();
        addComponents();
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
        if (user != null) {
            dptoField.setText("dpto info");
            nameField.setText(user.getName());
            surnameField.setText(user.getSurname());
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
            User user = new User(nameField.getText(), surnameField.getText());
            dao.save(user);
        };
    }

    public ActionListener execCancelButton() {
        return e -> {
        };
    }
}
