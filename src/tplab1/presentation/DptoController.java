package tplab1.presentation;

import tplab1.application.Dpto;
import tplab1.persistency.DAO;

import javax.swing.*;

public class DptoController {
    private JFrame frame;
    private DAO<Dpto, Integer> dao;
    private DptoTablePanel dptoTablePanel;
    private DptoEditorPanel dptoEditorPanel;


    public DptoController(JFrame frame, DAO<Dpto, Integer> dao) {
        this.frame = frame;
        this.dao = dao;
        this.dptoTablePanel = new DptoTablePanel(dao, this);
        this.dptoEditorPanel = new DptoEditorPanel(dao, this);
    }

    public void build() {
        dptoEditorPanel.buildPanel();
        showDptoTablePanel();
    }

    public void showDptoTablePanel() {
        dptoTablePanel.buildPanel();
        frame.getContentPane().removeAll();
        frame.getContentPane().add(dptoTablePanel);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
    }

    public void showDptoEditorPanel(Dpto dpto) {
        dptoEditorPanel.setDpto(dpto);
        dptoEditorPanel.setFields();
        frame.getContentPane().removeAll();
        frame.getContentPane().add(dptoEditorPanel);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
    }
}
