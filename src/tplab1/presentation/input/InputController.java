package tplab1.presentation.input;

import tplab1.application.service.InputService;
import tplab1.presentation.MainFrame;

public class InputController {

    private MainFrame frame;
    private InputService inputService;
    private InputTablePanel inputTablePanel;

    public InputController(InputService inputService) {
        this.inputService = inputService;
        this.inputTablePanel = new InputTablePanel(inputService, this);
    }

    public void build() {
        //dptoEditorPanel.buildPanel();
        showInputTablePanel();
    }

    public void showInputTablePanel() {
        inputTablePanel.buildPanel();
        frame.repaintWith(inputTablePanel);
    }

    public void showMainPanel() {
        frame.show();
    }

    public void setFrame(MainFrame frame) {
        this.frame = frame;
    }
}
