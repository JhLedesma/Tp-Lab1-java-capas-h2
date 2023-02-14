package tplab1.presentation.input;

import com.toedter.calendar.JDateChooser;
import tplab1.application.model.Input;
import tplab1.application.service.InputService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class InputEditorPanel extends JPanel {

    private InputService inputService;
    private InputController inputController;
    private Input input;
    private JLabel dptoLabel = new JLabel("Unidad");
    private JLabel descriptionLabel = new JLabel("Descripcion");
    private JLabel amountLabel = new JLabel("Amount");
    private JLabel dateLabel = new JLabel("date");
    private JTextField descriptionField = new JTextField(15);
    private JTextField amountField = new JTextField(15);
    private JDateChooser calendar = new JDateChooser();
    private JComboBox dptoBox = new JComboBox();
    private JButton clearButton = new JButton("Limpiar");
    private JButton saveButton = new JButton("Guardar");
    private JButton cancelButton = new JButton("Cancel");

    public InputEditorPanel(InputService inputService, InputController inputController) {
        super();
        this.inputService = inputService;
        this.inputController = inputController;
    }

    public void buildPanel() {
        BoxLayout verticalLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(verticalLayout);
        setFields();
        addComponents();
        addListeners();
    }

    public ActionListener execClearButton() {
        return e -> setFields();
    }

    public ActionListener execSaveButton() {
        return e -> {
            String dptoId = dptoBox.getSelectedItem().toString();
            String id = input != null && input.getId() != null ? input.getId().toString() : null;
            inputService.save(id, amountField.getText(), descriptionField.getText(), calendar.getDate(), dptoId);
            inputController.showInputTablePanel();
        };
    }

    public ActionListener execCancelButton() {
        return e -> inputController.showInputTablePanel();
    }

    public void setFields() {
        dptoBox.setModel(new DefaultComboBoxModel(inputService.getDptosIds()));
        if (input != null) {
            descriptionField.setText(input.getDescription());
            amountField.setText(input.getAmount().toString());
        } else {
            descriptionField.setText("");
            amountField.setText("");
        }
    }

    private void addComponents() {
        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        topPanel.setLayout(new GridLayout(2, 2));
        bottomPanel.setLayout(new FlowLayout());

        JPanel dptoPanel = createComboBoxPanel(dptoLabel, dptoBox);
        JPanel descriptionPanel = createEditPanel(descriptionLabel, descriptionField);
        JPanel amountPanel = createEditPanel(amountLabel, amountField);
        JPanel panel = createCalendarPanel();

        addPanelToPanel(Arrays.asList(dptoPanel, descriptionPanel, amountPanel, panel), topPanel);
        addButtonToPanel(Arrays.asList(clearButton, saveButton, cancelButton), bottomPanel);

        this.add(topPanel);
        this.add(bottomPanel);
    }

    private void addListeners() {
        clearButton.addActionListener(execClearButton());
        saveButton.addActionListener(execSaveButton());
        cancelButton.addActionListener(execCancelButton());
    }

    private JPanel createEditPanel(JLabel label, JTextField field) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(label);
        panel.add(field);
        return panel;
    }

    private JPanel createComboBoxPanel(JLabel label, JComboBox comboBox) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(label);
        comboBox.setPreferredSize(new Dimension(170, comboBox.getPreferredSize().height));
        panel.add(comboBox);
        return panel;
    }

    private JPanel createCalendarPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        calendar.setDate(Date.from(Instant.now()));
        panel.add(dateLabel);
        panel.add(calendar);
        return panel;
    }

    private void addPanelToPanel(List<JPanel> childPanels, JPanel panel) {
        childPanels.forEach(panel::add);
    }

    private void addButtonToPanel(List<JButton> childButtons, JPanel panel) {
        childButtons.forEach(panel::add);
    }

    public void setInput(Input input) {
        this.input = input;
    }
}
