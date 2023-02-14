package tplab1.presentation;

import tplab1.presentation.dpto.DptoController;
import tplab1.presentation.input.InputController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel {

    private DptoController dptoController;
    private InputController inputController;
    private JButton dptosButton = new JButton("Departamentos");
    private JButton inputButton = new JButton("Ingresos");
    private JButton outputButton = new JButton("Gastos");
    private JButton monthlyBalanceButton = new JButton("Balance Mensual");


    public MainPanel(DptoController dptoController, InputController inputController) {
        super();
        this.dptoController = dptoController;
        this.inputController = inputController;
    }

    public void buildPanel() {
        BoxLayout verticalLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(verticalLayout);
        addComponents();
        addListeners();
    }

    private void addComponents() {
        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(450, 200));
        this.add(dptosButton);
        this.add(inputButton);
        this.add(outputButton);
        this.add(monthlyBalanceButton);
    }

    private void addListeners() {
        dptosButton.addActionListener(dptosButtonEffect());
        inputButton.addActionListener(inputsButtonEffect());
    }

    public ActionListener dptosButtonEffect() {
        return e -> dptoController.build();
    }

    public ActionListener inputsButtonEffect() {
        return e -> inputController.build();
    }
}
