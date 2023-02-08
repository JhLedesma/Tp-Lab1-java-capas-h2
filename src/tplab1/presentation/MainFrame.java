package tplab1.presentation;

import tplab1.application.DptoService;
import tplab1.presentation.dpto.DptoController;

import javax.swing.*;

public class MainFrame {

    private JFrame frame = new JFrame("Administracion de consorcio");
    private DptoService dptoService;
    private DptoController dptoController;
    private MainPanel mainPanel;

    public MainFrame(DptoService dptoService) {
        this.dptoService = dptoService;
        this.dptoController = new DptoController(this, dptoService);
        this.mainPanel = new MainPanel(dptoController);
    }

    public void show() {
        mainPanel.buildPanel();
        repaintWith(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void repaintWith(JPanel panel) {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
        frame.pack();
    }
}
