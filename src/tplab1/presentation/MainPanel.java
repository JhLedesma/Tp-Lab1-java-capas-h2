package tplab1.presentation;

import tplab1.presentation.dpto.DptoController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel {

    private DptoController dptoController;
    private JButton dptosButton = new JButton("Departamentos");
    private JButton inputButton = new JButton("Ingresos");
    private JButton outputButton = new JButton("Gastos");


    public MainPanel(DptoController dptoController) {
        super();
        this.dptoController = dptoController;
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
    }

    private void addListeners() {
        dptosButton.addActionListener(dptosButtonEffect());
    }

    public ActionListener dptosButtonEffect() {
        return e -> dptoController.build();
    }
}
