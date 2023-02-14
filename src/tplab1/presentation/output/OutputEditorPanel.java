package tplab1.presentation.output;

import com.toedter.calendar.JDateChooser;
import tplab1.application.model.Output;
import tplab1.application.service.OutputService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class OutputEditorPanel extends JPanel {

    private OutputService outputService;
    private OutputController outputController;
    private Output output;
    private JLabel descriptionLabel = new JLabel("Descripcion");
    private JLabel amountLabel = new JLabel("Valor");
    private JLabel dateLabel = new JLabel("Fecha");
    private JTextField descriptionField = new JTextField(13);
    private JTextField amountField = new JTextField(13);
    private JDateChooser calendar = new JDateChooser();
    private JButton clearButton = new JButton("Limpiar");
    private JButton saveButton = new JButton("Guardar");
    private JButton cancelButton = new JButton("Cancel");

    public OutputEditorPanel(OutputService outputService, OutputController outputController) {
        super();
        this.outputService = outputService;
        this.outputController = outputController;
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
            String id = output != null && output.getId() != null ? output.getId().toString() : null;
            outputService.save(id, amountField.getText(), descriptionField.getText(), calendar.getDate());
            outputController.showOutputTablePanel();
        };
    }

    public ActionListener execCancelButton() {
        return e -> outputController.showOutputTablePanel();
    }

    public void setFields() {
        if (output != null) {
            descriptionField.setText(output.getDescription());
            amountField.setText(output.getAmount().toString());
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

        JPanel descriptionPanel = createEditPanel(descriptionLabel, descriptionField);
        JPanel amountPanel = createEditPanel(amountLabel, amountField);
        JPanel panel = createCalendarPanel();

        addPanelToPanel(Arrays.asList(descriptionPanel, amountPanel, panel), topPanel);
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

    private JPanel createCalendarPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        calendar.setPreferredSize(new Dimension(200, calendar.getPreferredSize().height));
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

    public void setOutput(Output output) {
        this.output = output;
    }
}
