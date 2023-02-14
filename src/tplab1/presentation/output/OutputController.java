package tplab1.presentation.output;

import tplab1.application.model.Output;
import tplab1.application.service.OutputService;
import tplab1.presentation.MainFrame;

public class OutputController {

    private MainFrame frame;
    private OutputService outputService;
    private OutputTablePanel outputTablePanel;
    private OutputEditorPanel outputEditorPanel;

    public OutputController(OutputService outputService) {
        this.outputService = outputService;
        this.outputTablePanel = new OutputTablePanel(outputService, this);
        this.outputEditorPanel = new OutputEditorPanel(outputService, this);
    }

    public void build() {
        outputEditorPanel.buildPanel();
        showOutputTablePanel();
    }

    public void showOutputEditorPanel(Output output) {
        outputEditorPanel.setOutput(output);
        outputEditorPanel.setFields();
        frame.repaintWith(outputEditorPanel);
    }

    public void showOutputTablePanel() {
        outputTablePanel.buildPanel();
        frame.repaintWith(outputTablePanel);
    }

    public void showMainPanel() {
        frame.show();
    }

    public void setFrame(MainFrame frame) {
        this.frame = frame;
    }
}
