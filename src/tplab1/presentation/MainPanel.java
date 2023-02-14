package tplab1.presentation;

import tplab1.presentation.balance.BalanceController;
import tplab1.presentation.dpto.DptoController;
import tplab1.presentation.input.InputController;
import tplab1.presentation.output.OutputController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel {

    private DptoController dptoController;
    private InputController inputController;
    private OutputController outputController;
    private BalanceController balanceController;
    private JButton dptosButton = new JButton("Departamentos");
    private JButton inputButton = new JButton("Ingresos");
    private JButton outputButton = new JButton("Gastos");
    private JButton balanceButton = new JButton("Balances");


    public MainPanel(DptoController dptoController, InputController inputController, OutputController outputController, BalanceController balanceController) {
        super();
        this.dptoController = dptoController;
        this.inputController = inputController;
        this.outputController = outputController;
        this.balanceController = balanceController;
    }

    public void buildPanel() {
        addComponents();
        addListeners();
    }

    private void addComponents() {
        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(450, 70));
        this.add(dptosButton);
        this.add(inputButton);
        this.add(outputButton);
        this.add(balanceButton);
    }

    private void addListeners() {
        dptosButton.addActionListener(dptosButtonEffect());
        inputButton.addActionListener(inputsButtonEffect());
        outputButton.addActionListener(outputsButtonEffect());
        balanceButton.addActionListener(balanceButtonEffect());
    }

    public ActionListener dptosButtonEffect() {
        return e -> dptoController.build();
    }

    public ActionListener inputsButtonEffect() {
        return e -> inputController.build();
    }

    public ActionListener outputsButtonEffect() {
        return e -> outputController.build();
    }

    public ActionListener balanceButtonEffect() {
        return e -> balanceController.build();
    }
}
