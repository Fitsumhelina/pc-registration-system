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

public class RegisterUserFrame extends JFrame {
    // private String registeredby;
    private String AdminName;
    private JComboBox<String> userTypeComboBox;
    private JTextField studentNameField, studentIdField, studentMacAddressField, staffNameField, staffRoleField, staffMacAddressField;
    private JComboBox<String> studentDepartmentComboBox, studentPcModelComboBox, staffPcModelComboBox, staffTypeComboBox;
    private JButton registerBtn, backBtn;
    private StudentService studentService;
    private StaffService staffService;
    private JPanel studentPanel, staffPanel;

    public RegisterUserFrame(String AdminName) {
        this.AdminName = AdminName;
        studentService = new StudentService();
        staffService = new StaffService();

        setTitle("Register User");
        setSize(500, 450);
        setLayout(new BorderLayout());

        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180));
        JLabel headerLabel = new JLabel("Register New User");
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        // User Type Selection
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        userTypeComboBox = new JComboBox<>(new String[]{"Select User Type", "Student", "Staff"});
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel.add(userTypeComboBox, gbc);

        // Student Panel (Fixed Issues)
        studentPanel = new JPanel(new GridBagLayout());
        studentNameField = new JTextField(15);
        studentIdField = new JTextField(15);
        studentMacAddressField = new JTextField(15);
        studentPcModelComboBox = new JComboBox<>(new String[]{"Select PC Model", "Dell", "HP", "Lenovo"});
        studentDepartmentComboBox = new JComboBox<>(new String[]{"Select Department", "CS", "EE", "ME"});

        GridBagConstraints studentGbc = new GridBagConstraints();
        studentGbc.insets = new Insets(5, 5, 5, 5);
        studentGbc.fill = GridBagConstraints.HORIZONTAL;

        studentGbc.gridx = 0;
        studentGbc.gridy = 0;
        studentPanel.add(new JLabel("Name:"), studentGbc);
        studentGbc.gridx = 1;
        studentPanel.add(studentNameField, studentGbc);

        studentGbc.gridx = 0;
        studentGbc.gridy = 1;
        studentPanel.add(new JLabel("ID:"), studentGbc);
        studentGbc.gridx = 1;
        studentPanel.add(studentIdField, studentGbc);

        studentGbc.gridx = 0;
        studentGbc.gridy = 2;
        studentPanel.add(new JLabel("PC Model:"), studentGbc);
        studentGbc.gridx = 1;
        studentPanel.add(studentPcModelComboBox, studentGbc);

        studentGbc.gridx = 0;
        studentGbc.gridy = 3;
        studentPanel.add(new JLabel("Department:"), studentGbc);
        studentGbc.gridx = 1;
        studentPanel.add(studentDepartmentComboBox, studentGbc);

        studentGbc.gridx = 0;
        studentGbc.gridy = 4;
        studentPanel.add(new JLabel("MAC Address:"), studentGbc);
        studentGbc.gridx = 1;
        studentPanel.add(studentMacAddressField, studentGbc);

        studentPanel.setVisible(false);
        gbc.gridy = 1;
        formPanel.add(studentPanel, gbc);

        // Staff Panel (Separate Fields)
        staffPanel = new JPanel(new GridBagLayout());
        staffNameField = new JTextField(15);
        staffRoleField = new JTextField(15);
        staffMacAddressField = new JTextField(15);
        staffPcModelComboBox = new JComboBox<>(new String[]{"Select PC Model", "Dell", "HP", "Lenovo"});
        staffTypeComboBox = new JComboBox<>(new String[]{"Select Type", "Personal", "Organization"});

        GridBagConstraints staffGbc = new GridBagConstraints();
        staffGbc.insets = new Insets(5, 5, 5, 5);
        staffGbc.fill = GridBagConstraints.HORIZONTAL;

        staffGbc.gridx = 0;
        staffGbc.gridy = 0;
        staffPanel.add(new JLabel("Name:"), staffGbc);
        staffGbc.gridx = 1;
        staffPanel.add(staffNameField, staffGbc);

        staffGbc.gridx = 0;
        staffGbc.gridy = 1;
        staffPanel.add(new JLabel("Role:"), staffGbc);
        staffGbc.gridx = 1;
        staffPanel.add(staffRoleField, staffGbc);

        staffGbc.gridx = 0;
        staffGbc.gridy = 2;
        staffPanel.add(new JLabel("Type:"), staffGbc);
        staffGbc.gridx = 1;
        staffPanel.add(staffTypeComboBox, staffGbc);

        staffGbc.gridx = 0;
        staffGbc.gridy = 3;
        staffPanel.add(new JLabel("PC Model:"), staffGbc);
        staffGbc.gridx = 1;
        staffPanel.add(staffPcModelComboBox, staffGbc);

        staffGbc.gridx = 0;
        staffGbc.gridy = 4;
        staffPanel.add(new JLabel("MAC Address:"), staffGbc);
        staffGbc.gridx = 1;
        staffPanel.add(staffMacAddressField, staffGbc);

        staffPanel.setVisible(false);
        gbc.gridy = 2;
        formPanel.add(staffPanel, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        registerBtn = new JButton("Register");
        backBtn = new JButton("Back");

        buttonPanel.add(registerBtn);
        buttonPanel.add(backBtn);

        gbc.gridy = 3;
        gbc.gridwidth = 2;
        formPanel.add(buttonPanel, gbc);

        add(formPanel, BorderLayout.CENTER);

        // Event Listeners
        userTypeComboBox.addActionListener(e -> togglePanels());
        registerBtn.addActionListener(this::handleRegister);
        backBtn.addActionListener(e -> {
            new AdminFrame("Admin");
            dispose();
        });

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
        String currentAdmin = AdminName;
        if ("Student".equals(userType)) {
            if (isStudentFormValid()) {
                studentService.registerStudent(
                        studentNameField.getText(),
                        studentIdField.getText(),
                        (String) studentDepartmentComboBox.getSelectedItem(),
                        (String) studentPcModelComboBox.getSelectedItem(),
                        studentMacAddressField.getText(),
                        currentAdmin
                );
                JOptionPane.showMessageDialog(this, "Student Registered Successfully");
            } else {
                JOptionPane.showMessageDialog(this, "All fields must be filled correctly!");
            }
        } else if ("Staff".equals(userType)) {
            if (isStaffFormValid()) {
                staffService.registerStaff(
                        staffNameField.getText(),
                        staffRoleField.getText(),
                        (String) staffTypeComboBox.getSelectedItem(),
                        (String) staffPcModelComboBox.getSelectedItem(),
                        staffMacAddressField.getText(),
                        currentAdmin
                );
                JOptionPane.showMessageDialog(this, "Staff Registered Successfully");
            } else {
                JOptionPane.showMessageDialog(this, "All fields must be filled correctly!");
            }
        }
    }

    private boolean isStudentFormValid() {
        return !studentNameField.getText().isEmpty() && !studentIdField.getText().isEmpty()
                && studentDepartmentComboBox.getSelectedIndex() > 0
                && studentPcModelComboBox.getSelectedIndex() > 0
                && !studentMacAddressField.getText().isEmpty();
    }

    private boolean isStaffFormValid() {
        return !staffNameField.getText().isEmpty() && !staffRoleField.getText().isEmpty()
                && staffTypeComboBox.getSelectedIndex() > 0
                && staffPcModelComboBox.getSelectedIndex() > 0
                && !staffMacAddressField.getText().isEmpty();
    }
}
