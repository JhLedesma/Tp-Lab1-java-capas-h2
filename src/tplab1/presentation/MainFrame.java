package tplab1.presentation;

import tplab1.application.User;
import tplab1.persistency.DAO;

import javax.swing.*;
import java.awt.*;

public class MainFrame {

    private DAO<User, String> dao;

    public MainFrame(DAO<User, String> dao) {
        this.dao = dao;
    }

    public void show() {
        JFrame frame = new JFrame("TP");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new FlowLayout());

        frame.getContentPane().add(new UserTablePanel(dao));

        frame.pack();
        frame.setVisible(true);
    }
}
