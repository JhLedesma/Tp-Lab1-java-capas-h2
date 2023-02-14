package tplab1.presentation.balance;

import tplab1.application.service.MonthlyBalanceService;
import tplab1.presentation.MainFrame;

public class BalanceController {

    private MainFrame frame;
    private MonthlyBalanceService monthlyBalanceService;
    private BalanceViewPanel balanceViewPanel;

    public BalanceController(MonthlyBalanceService monthlyBalanceService) {
        this.monthlyBalanceService = monthlyBalanceService;
        this.balanceViewPanel = new BalanceViewPanel(monthlyBalanceService, this);
    }

    public void build() {
        balanceViewPanel.buildPanel();
        frame.repaintWith(balanceViewPanel);
    }

    public void showMainPanel() {
        frame.show();
    }

    public void setFrame(MainFrame frame) {
        this.frame = frame;
    }
}
