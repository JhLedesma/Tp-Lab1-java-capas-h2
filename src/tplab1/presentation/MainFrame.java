package tplab1.presentation;

import tplab1.application.DptoService;
import tplab1.presentation.dpto.DptoController;

import javax.swing.*;

public class MainFrame {

    private JFrame frame = new JFrame("TP");
    private DptoService dptoService;
    private DptoController dptoController;

    public MainFrame(DptoService dptoService) {
        this.dptoService = dptoService;
        this.dptoController = new DptoController(frame, dptoService);
    }

    public void show() {
        dptoController.build();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
