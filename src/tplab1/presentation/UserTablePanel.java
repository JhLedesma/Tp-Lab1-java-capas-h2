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
import java.util.Random;

public class UserTablePanel extends JPanel implements ActionListener {

    private DAO<User, String> dao;
    private UserTableModel userTableModel;
    private JTable userTable;
    private JScrollPane scrollPane;
    private JButton cleanButton;
    private JButton addButton;

    public UserTablePanel(DAO<User, String> dao) {
        super();
        this.dao = dao;
        buildPanel();
    }

    private void buildPanel() {
        this.setLayout(new FlowLayout());
        userTableModel = new UserTableModel();
        userTable = new JTable(userTableModel);
        scrollPane = new JScrollPane(userTable);
        this.add(scrollPane);

        cleanButton = new JButton("Limpiar");
        cleanButton.addActionListener(this);
        this.add(cleanButton);

        addButton = new JButton("Agregar");
        addButton.addActionListener(this);
        this.add(addButton);

        List<User> content;
        try {
            content = dao.getAll();
        } catch (NonExistentElement e) {
            content = Collections.emptyList();
        }

        userTableModel.setContent(content);
        userTableModel.fireTableDataChanged();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            Random random = new Random();
            User user = new User(random.nextInt() + "_name", random.nextInt() + "_surname");
            userTableModel.getContent().add(user);
            userTableModel.fireTableDataChanged();
        } else if (e.getSource() == cleanButton) {
            userTableModel.getContent().clear();
            userTableModel.fireTableDataChanged();
        }
    }
}
