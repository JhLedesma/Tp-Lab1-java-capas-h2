package tplab1.presentation;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.time.Instant;
import java.util.Date;
import java.util.List;

public class PanelUtils {

    public static JPanel createComboBoxPanel(JLabel label, JComboBox comboBox) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(label);
        comboBox.setPreferredSize(new Dimension(170, comboBox.getPreferredSize().height));
        panel.add(comboBox);
        return panel;
    }

    public static JPanel createVerticalLabelPanel(JLabel label, JLabel labelInfo) {
        JPanel panel = new JPanel();
        BoxLayout verticalLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(verticalLayout);
        panel.add(label);
        panel.add(labelInfo);
        JPanel outerPanel = new JPanel();
        outerPanel.add(panel);
        return outerPanel;
    }

    public static JPanel createCalendarPanel(JDateChooser calendar, JLabel dateLabel) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        calendar.setPreferredSize(new Dimension(200, calendar.getPreferredSize().height));
        calendar.setDate(Date.from(Instant.now()));
        panel.add(dateLabel);
        panel.add(calendar);
        return panel;
    }

    public static JPanel createEditPanel(JLabel label, JTextField field) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(label);
        panel.add(field);
        return panel;
    }

    public static void addPanelToPanel(List<JPanel> childPanels, JPanel panel) {
        childPanels.forEach(panel::add);
    }

    public static void addButtonToPanel(List<JButton> childButtons, JPanel panel) {
        childButtons.forEach(panel::add);
    }
}
