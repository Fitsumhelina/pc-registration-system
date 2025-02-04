package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import services.StaffService;
import services.StudentService;

public class AdminFrame extends JFrame {
    private JComboBox<String> userTypeComboBox;
    private JTextField nameField, idField, roleField, macAddressField;
    private JComboBox<String> departmentComboBox, pcModelComboBox, typeComboBox;
    private JButton registerBtn, updateBtn, deleteBtn, searchBtn, viewBtn;

    private StudentService studentService;
    private StaffService staffService;
    private JPanel studentPanel, staffPanel;

    public AdminFrame() {
        studentService = new StudentService();
        staffService = new StaffService();

        setTitle("Admin Dashboard");
        setSize(550, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180));
        JLabel headerLabel = new JLabel("Admin Dashboard");
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // User Type Selection
        userTypeComboBox = new JComboBox<>(new String[]{"Select User Type", "Student", "Staff"});
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        formPanel.add(userTypeComboBox, gbc);

        // Common Fields
        nameField = new JTextField();
        macAddressField = new JTextField();
        pcModelComboBox = new JComboBox<>(new String[]{"Select PC Model", "Dell", "HP", "Lenovo"});

        // Student Fields
        studentPanel = new JPanel(new GridLayout(5, 2));
        idField = new JTextField();
        departmentComboBox = new JComboBox<>(new String[]{"Select Department", "CS", "EE", "ME"});
        studentPanel.add(new JLabel("Name:")); studentPanel.add(nameField);
        studentPanel.add(new JLabel("ID:")); studentPanel.add(idField);
        studentPanel.add(new JLabel("Department:")); studentPanel.add(departmentComboBox);
        studentPanel.add(new JLabel("PC Model:")); studentPanel.add(pcModelComboBox);
        studentPanel.add(new JLabel("MAC Address:")); studentPanel.add(macAddressField);
        studentPanel.setVisible(false);
        gbc.gridy = 1; gbc.gridwidth = 2;
        formPanel.add(studentPanel, gbc);

        // Staff Fields
        staffPanel = new JPanel(new GridLayout(5, 2));
        roleField = new JTextField();
        typeComboBox = new JComboBox<>(new String[]{"Select Type", "Personal", "Organization"});
        staffPanel.add(new JLabel("Name:")); staffPanel.add(nameField);
        staffPanel.add(new JLabel("Role:")); staffPanel.add(roleField);
        staffPanel.add(new JLabel("Type:")); staffPanel.add(typeComboBox);
        staffPanel.add(new JLabel("PC Model:")); staffPanel.add(pcModelComboBox);
        staffPanel.add(new JLabel("MAC Address:")); staffPanel.add(macAddressField);
        staffPanel.setVisible(false);
        gbc.gridy = 2;
        formPanel.add(staffPanel, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 5, 5));
        registerBtn = new JButton("Register");
        updateBtn = new JButton("Update");
        deleteBtn = new JButton("Delete");
        searchBtn = new JButton("Search");
        viewBtn = new JButton("View All");

        buttonPanel.add(registerBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(searchBtn);
        buttonPanel.add(viewBtn);
        
        gbc.gridy = 3; gbc.gridwidth = 2;
        formPanel.add(buttonPanel, gbc);

        add(formPanel, BorderLayout.CENTER);

        // User Type Selection Logic
        userTypeComboBox.addActionListener(e -> togglePanels());

        // Button Listeners
        registerBtn.addActionListener(this::handleRegister);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void togglePanels() {
        String selectedType = (String) userTypeComboBox.getSelectedItem();
        studentPanel.setVisible("Student".equals(selectedType));
        staffPanel.setVisible("Staff".equals(selectedType));
    }

    private void handleRegister(ActionEvent e) {
        String userType = (String) userTypeComboBox.getSelectedItem();
        if (userType.equals("Student")) {
            if (isStudentFormValid()) {
                studentService.registerStudent(
                        nameField.getText(),
                        idField.getText(),
                        (String) departmentComboBox.getSelectedItem(),
                        (String) pcModelComboBox.getSelectedItem(),
                        macAddressField.getText()
                );
                JOptionPane.showMessageDialog(this, "Student Registered Successfully");
            } else {
                JOptionPane.showMessageDialog(this, "All fields must be filled correctly!");
            }
        } else if (userType.equals("Staff")) {
            if (isStaffFormValid()) {
                staffService.registerStaff(
                        nameField.getText(),
                        roleField.getText(),
                        (String) typeComboBox.getSelectedItem(),
                        (String) pcModelComboBox.getSelectedItem(),
                        macAddressField.getText()
                );
                JOptionPane.showMessageDialog(this, "Staff Registered Successfully");
            } else {
                JOptionPane.showMessageDialog(this, "All fields must be filled correctly!");
            }
        }
    }

    private boolean isStudentFormValid() {
        return !nameField.getText().isEmpty() && !idField.getText().isEmpty()
                && departmentComboBox.getSelectedIndex() > 0
                && pcModelComboBox.getSelectedIndex() > 0
                && !macAddressField.getText().isEmpty();
    }

    private boolean isStaffFormValid() {
        return !nameField.getText().isEmpty() && !roleField.getText().isEmpty()
                && typeComboBox.getSelectedIndex() > 0
                && pcModelComboBox.getSelectedIndex() > 0
                && !macAddressField.getText().isEmpty();
    }
}