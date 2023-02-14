package tplab1.presentation.output;

import tplab1.application.service.OutputService;
import tplab1.presentation.MainFrame;

public class OutputController {

    private MainFrame frame;
    private OutputService outputService;




    public void showMainPanel() {
        frame.show();
    }

    public void setFrame(MainFrame frame) {
        this.frame = frame;
    }
}
