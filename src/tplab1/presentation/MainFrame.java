package tplab1.presentation;

import tplab1.presentation.balance.BalanceController;
import tplab1.presentation.dpto.DptoController;
import tplab1.presentation.input.InputController;
import tplab1.presentation.output.OutputController;

import javax.swing.*;

public class MainFrame {

    private JFrame frame = new JFrame("Administracion de consorcio");
    private MainPanel mainPanel;

    public MainFrame(DptoController dptoController, InputController inputController, OutputController outputController, BalanceController balanceController) {
        dptoController.setFrame(this);
        inputController.setFrame(this);
        outputController.setFrame(this);
        balanceController.setFrame(this);
        this.mainPanel = new MainPanel(dptoController, inputController, outputController, balanceController);
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
