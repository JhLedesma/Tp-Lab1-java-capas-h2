package tplab1.presentation;

import tplab1.application.Dpto;
import tplab1.persistency.DAO;

import javax.swing.*;
import java.awt.*;

public class MainFrame {

    private DAO<Dpto, Integer> dao;

    public MainFrame(DAO<Dpto, Integer> dao) {
        this.dao = dao;
    }

    public void show() {
        JFrame frame = new JFrame("TP");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new FlowLayout());

        frame.getContentPane().add(new DptoTablePanel(dao));

        frame.pack();
        frame.setVisible(true);
    }
}
