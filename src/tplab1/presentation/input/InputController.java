package tplab1.presentation.input;

import tplab1.application.model.Input;
import tplab1.application.service.InputService;
import tplab1.presentation.MainFrame;

public class InputController {

    private MainFrame frame;
    private InputService inputService;
    private InputTablePanel inputTablePanel;
    private InputEditorPanel inputEditorPanel;

    public InputController(InputService inputService) {
        this.inputService = inputService;
        this.inputTablePanel = new InputTablePanel(inputService, this);
        this.inputEditorPanel = new InputEditorPanel(inputService, this);
    }

    public void build() {
        inputEditorPanel.buildPanel();
        showInputTablePanel();
    }

    public void showInputEditorPanel(Input input) {
        inputEditorPanel.setInput(input);
        inputEditorPanel.setFields();
        frame.repaintWith(inputEditorPanel);
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
