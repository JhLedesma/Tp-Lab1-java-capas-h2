package tplab1.presentation.dpto;

import tplab1.application.Dpto;
import tplab1.application.DptoService;

import javax.swing.*;

public class DptoController {
    private JFrame frame;
    private DptoService dptoService;
    private DptoTablePanel dptoTablePanel;
    private DptoEditorPanel dptoEditorPanel;


    public DptoController(JFrame frame, DptoService dptoService) {
        this.frame = frame;
        this.dptoService = dptoService;
        this.dptoTablePanel = new DptoTablePanel(dptoService, this);
        this.dptoEditorPanel = new DptoEditorPanel(dptoService, this);
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
