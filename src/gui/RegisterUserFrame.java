package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RegisterUserFrame extends JFrame {
    private JComboBox<String> userTypeComboBox;
    private JTextField nameField, idField, roleField, macAddressField;
    private JComboBox<String> departmentComboBox, pcModelComboBox, typeComboBox;
    private JButton registerBtn, backBtn;
    private JPanel studentPanel, staffPanel;

    public RegisterUserFrame() {
        setTitle("Register User");
        setSize(400, 300);
        setLayout(new BorderLayout());

        // User Type Selection
        userTypeComboBox = new JComboBox<>(new String[]{"Select User Type", "Student", "Staff"});
        userTypeComboBox.addActionListener(e -> togglePanels());

        // Student Panel
        studentPanel = new JPanel(new GridLayout(4, 2));
        nameField = new JTextField();
        idField = new JTextField("DBU");
        departmentComboBox = new JComboBox<>(new String[]{"Select Department", "CS", "EE", "ME"});
        pcModelComboBox = new JComboBox<>(new String[]{"Select PC Model", "Dell", "HP", "Lenovo"});
        macAddressField = new JTextField();

        studentPanel.add(new JLabel("Name:")); studentPanel.add(nameField);
        studentPanel.add(new JLabel("ID:")); studentPanel.add(idField);
        studentPanel.add(new JLabel("Department:")); studentPanel.add(departmentComboBox);
        studentPanel.add(new JLabel("PC Model:")); studentPanel.add(pcModelComboBox);
        studentPanel.add(new JLabel("MAC Address:")); studentPanel.add(macAddressField);
        studentPanel.setVisible(false);

        // Staff Panel
        staffPanel = new JPanel(new GridLayout(4, 2));
        roleField = new JTextField();
        typeComboBox = new JComboBox<>(new String[]{"Select Type", "Personal", "Organization"});
        staffPanel.add(new JLabel("Name:")); staffPanel.add(nameField);
        staffPanel.add(new JLabel("Role:")); staffPanel.add(roleField);
        staffPanel.add(new JLabel("Type:")); staffPanel.add(typeComboBox);
        staffPanel.add(new JLabel("PC Model:")); staffPanel.add(pcModelComboBox);
        staffPanel.add(new JLabel("MAC Address:")); staffPanel.add(macAddressField);
        staffPanel.setVisible(false);

        // Buttons
        registerBtn = new JButton("Register");
        registerBtn.addActionListener(this::handleRegister);
        backBtn = new JButton("Back");
        backBtn.addActionListener(e -> {
            new AdminFrame("Admin");
            dispose();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(registerBtn);
        buttonPanel.add(backBtn);

        // Add components
        add(userTypeComboBox, BorderLayout.NORTH);
        add(studentPanel, BorderLayout.CENTER);
        add(staffPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void togglePanels() {
        String selectedType = (String) userTypeComboBox.getSelectedItem();
        studentPanel.setVisible("Student".equals(selectedType));
        staffPanel.setVisible("Staff".equals(selectedType));
    }

    private void handleRegister(ActionEvent e) {
        JOptionPane.showMessageDialog(this, "User Registered Successfully!");
    }
}
