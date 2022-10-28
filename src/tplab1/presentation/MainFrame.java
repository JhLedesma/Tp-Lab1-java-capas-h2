package tplab1.presentation;

import tplab1.application.Dpto;
import tplab1.persistency.DAO;

import javax.swing.*;

public class MainFrame {

    private JFrame frame = new JFrame("TP");
    private DAO<Dpto, Integer> dao;
    private DptoController dptoController;

    public MainFrame(DAO<Dpto, Integer> dao) {
        this.dao = dao;
        this.dptoController = new DptoController(frame, dao);
    }

    public void show() {
        dptoController.build();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
