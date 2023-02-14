package tplab1.presentation.input;

import com.toedter.calendar.JDateChooser;
import tplab1.application.model.Input;
import tplab1.application.service.InputService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;

import static tplab1.presentation.PanelUtils.*;

public class InputEditorPanel extends JPanel {

    private InputService inputService;
    private InputController inputController;
    private Input input;
    private JLabel dptoLabel = new JLabel("Unidad");
    private JLabel descriptionLabel = new JLabel("Descripcion");
    private JLabel amountLabel = new JLabel("Valor");
    private JLabel dateLabel = new JLabel("Fecha");
    private JTextField descriptionField = new JTextField(13);
    private JTextField amountField = new JTextField(13);
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
        JPanel panel = createCalendarPanel(calendar, dateLabel);

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

    public void setInput(Input input) {
        this.input = input;
    }
}
