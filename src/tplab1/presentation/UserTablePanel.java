package tplab1.presentation;

import tplab1.application.User;
import tplab1.persistency.DAO;
import tplab1.persistency.exception.NonExistentElement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

public class UserTablePanel extends JPanel implements ActionListener {

    private DAO<User, String> dao;
    private UserTableModel userTableModel = new UserTableModel();
    private JTable userTable = new JTable(userTableModel);
    private JScrollPane scrollPane = new JScrollPane(userTable);
    private JButton deleteButton = new JButton("Eliminar");
    private JButton addButton = new JButton("Agregar");
    private JButton updateButton = new JButton("Modificar");
    private JButton backButton = new JButton("Volver");

    public UserTablePanel(DAO<User, String> dao) {
        super();
        this.dao = dao;
        buildPanel();
    }

    private void buildPanel() {
        this.setLayout(new FlowLayout());
        addComponents();
        addListeners();
        showUsersList();
    }

    private void addComponents() {
        this.add(scrollPane);
        this.add(deleteButton);
        this.add(addButton);
        this.add(updateButton);
        this.add(backButton);
    }

    private void addListeners() {
        deleteButton.addActionListener(deleteButtonEffect());
        addButton.addActionListener(addButtonEffect());
        updateButton.addActionListener(this);
        backButton.addActionListener(this);
    }

    private void showUsersList() {
        List<User> content;
        try {
            content = dao.getAll();
        } catch (NonExistentElement e) {
            content = Collections.emptyList();
        }
        userTableModel.setContent(content);
        userTableModel.fireTableDataChanged();
//        userTable.setRowSelectionAllowed(true);
//        userTable.setRowSelectionInterval(0, 0);
    }

    public ActionListener addButtonEffect() {
        return e -> {
            User user = new User("Hola", "Chau");
            dao.save(user);
            userTableModel.getContent().add(user);
            userTableModel.fireTableDataChanged();
        };
    }

    public ActionListener deleteButtonEffect() {
        return e -> {
            int selectedRow = userTable.getSelectedRow();
            String id = userTableModel.getContent().get(selectedRow).getName();
            dao.delete(id);
            userTableModel.getContent().remove(selectedRow);
            userTableModel.fireTableDataChanged();
        };
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
