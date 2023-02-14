package tplab1.presentation.balance;

import tplab1.application.model.MonthlyBalance;
import tplab1.application.service.MonthlyBalanceService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class BalanceViewPanel extends JPanel {

    private MonthlyBalanceService monthlyBalanceService;
    private BalanceController balanceController;
    private JLabel dptoLabel = new JLabel("Unidad");
    private JComboBox dptoBox = new JComboBox();
    private JLabel monthLabel = new JLabel("Mes");
    private JComboBox monthBox = new JComboBox();
    private JLabel inputLabel = new JLabel("Entradas:");
    private JLabel inputResultLabel = new JLabel("");
    private JLabel outputLabel = new JLabel("Gastos:");
    private JLabel outputResultLabel = new JLabel("");
    private JLabel balanceLabel = new JLabel("Balance:");
    private JLabel balanceResultLabel = new JLabel("");
    private JButton clearButton = new JButton("Limpiar");
    private JButton findButton = new JButton("Buscar");
    private JButton cancelButton = new JButton("Cancel");

    public BalanceViewPanel(MonthlyBalanceService monthlyBalanceService, BalanceController balanceController) {
        super();
        this.monthlyBalanceService = monthlyBalanceService;
        this.balanceController = balanceController;
    }

    public void buildPanel() {
        BoxLayout verticalLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(verticalLayout);
        setFields();
        addComponents();
        addListeners();
    }

    private void addComponents() {
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        topPanel.add(createComboBoxPanel(dptoLabel, dptoBox));
        topPanel.add(createComboBoxPanel(monthLabel, monthBox));

        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new GridLayout(1, 3));
        middlePanel.add(createLabelPanel(inputLabel, inputResultLabel));
        middlePanel.add(createLabelPanel(outputLabel, outputResultLabel));
        middlePanel.add(createLabelPanel(balanceLabel, balanceResultLabel));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        addButtonToPanel(Arrays.asList(clearButton, findButton, cancelButton), bottomPanel);

        this.add(topPanel);
        this.add(middlePanel);
        this.add(bottomPanel);
    }


    private void setFields() {
        dptoBox.setModel(new DefaultComboBoxModel(monthlyBalanceService.getDptosIds()));
        monthBox.setModel(new DefaultComboBoxModel(monthlyBalanceService.getMonths()));
        inputResultLabel.setText("-");
        outputResultLabel.setText("-");
        balanceResultLabel.setText("-");
    }

    private void addListeners() {
        clearButton.addActionListener(execClearButton());
        findButton.addActionListener(execFindButton());
        cancelButton.addActionListener(execCancelButton());
    }


    public ActionListener execFindButton() {
        return e -> {
            int monthId = Integer.parseInt(monthBox.getSelectedItem().toString());
            Integer dptoId = Integer.parseInt(dptoBox.getSelectedItem().toString());
            MonthlyBalance monthlyBalance = monthlyBalanceService.getBalance(monthId, dptoId);
            inputResultLabel.setText(monthlyBalance.getInputAmount().toString());
            outputResultLabel.setText(monthlyBalance.getOutputAmount().toString());
            balanceResultLabel.setText(monthlyBalance.getBalanceAmount().toString());
        };
    }

    public ActionListener execClearButton() {
        return e -> setFields();
    }

    public ActionListener execCancelButton() {
        return e -> balanceController.showMainPanel();
    }


    private JPanel createComboBoxPanel(JLabel label, JComboBox comboBox) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(label);
        comboBox.setPreferredSize(new Dimension(170, comboBox.getPreferredSize().height));
        panel.add(comboBox);
        return panel;
    }

    private JPanel createLabelPanel(JLabel label, JLabel labelInfo) {
        JPanel panel = new JPanel();
        BoxLayout verticalLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(verticalLayout);
        panel.add(label);
        panel.add(labelInfo);
        JPanel outerPanel = new JPanel();
        outerPanel.add(panel);
        return outerPanel;
    }

    private void addButtonToPanel(List<JButton> childButtons, JPanel panel) {
        childButtons.forEach(panel::add);
    }
}
