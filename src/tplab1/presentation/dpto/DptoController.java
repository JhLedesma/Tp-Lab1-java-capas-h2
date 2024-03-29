package tplab1.presentation.dpto;

import tplab1.application.model.Dpto;
import tplab1.application.service.DptoService;
import tplab1.presentation.MainFrame;

public class DptoController {
    private MainFrame frame;
    private DptoService dptoService;
    private DptoTablePanel dptoTablePanel;
    private DptoEditorPanel dptoEditorPanel;


    public DptoController(DptoService dptoService) {
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
        frame.repaintWith(dptoTablePanel);
    }

    public void showDptoEditorPanel(Dpto dpto) {
        dptoEditorPanel.setDpto(dpto);
        dptoEditorPanel.setFields();
        frame.repaintWith(dptoEditorPanel);
    }

    public void showMainPanel() {
        frame.show();
    }

    public void setFrame(MainFrame frame) {
        this.frame = frame;
    }
}
