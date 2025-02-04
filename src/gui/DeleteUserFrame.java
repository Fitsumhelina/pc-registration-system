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

import models.Staff;
import models.Student;
import services.StaffService;
import services.StudentService;

public class DeleteUserFrame extends JFrame {
    private JComboBox<String> userTypeComboBox;
    private JTextField studentIdField, studentNameField, studentMacAddressField, staffNameField, staffRoleField, staffMacAddressField;
    private JComboBox<String> studentDepartmentComboBox, studentPcModelComboBox, staffPcModelComboBox, staffTypeComboBox;
    private JButton searchBtn, deleteBtn, backBtn;
    private StudentService studentService;
    private StaffService staffService;
    private JPanel studentPanel, staffPanel;

    public DeleteUserFrame() {
        studentService = new StudentService();
        staffService = new StaffService();

        setTitle("Delete User");
        setSize(500, 500);
        setLayout(new BorderLayout());

        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180));
        JLabel headerLabel = new JLabel("Delete Existing User");
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

        // Student Panel
        studentPanel = new JPanel(new GridBagLayout());
        studentIdField = new JTextField(15);
        studentNameField = new JTextField(15);
        studentMacAddressField = new JTextField(15);
        studentPcModelComboBox = new JComboBox<>(new String[]{"Select PC Model", "Dell", "HP", "Lenovo"});
        studentDepartmentComboBox = new JComboBox<>(new String[]{"Select Department", "CS", "EE", "ME"});

        GridBagConstraints studentGbc = new GridBagConstraints();
        studentGbc.insets = new Insets(5, 5, 5, 5);
        studentGbc.fill = GridBagConstraints.HORIZONTAL;

        studentGbc.gridx = 0;
        studentGbc.gridy = 0;
        studentPanel.add(new JLabel("Enter ID:"), studentGbc);
        studentGbc.gridx = 1;
        studentPanel.add(studentIdField, studentGbc);

        studentGbc.gridx = 0;
        studentGbc.gridy = 1;
        studentPanel.add(new JLabel("Name:"), studentGbc);
        studentGbc.gridx = 1;
        studentPanel.add(studentNameField, studentGbc);

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

        // Staff Panel
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
        staffPanel.add(new JLabel("Enter Name:"), staffGbc);
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
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 5, 5));
        searchBtn = new JButton("Search");
        deleteBtn = new JButton("Delete");
        backBtn = new JButton("Back");

        buttonPanel.add(searchBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(backBtn);

        gbc.gridy = 3;
        gbc.gridwidth = 2;
        formPanel.add(buttonPanel, gbc);

        add(formPanel, BorderLayout.CENTER);

        // Event Listeners
        userTypeComboBox.addActionListener(e -> togglePanels());
        searchBtn.addActionListener(this::handleSearch);
        deleteBtn.addActionListener(this::handleDelete);
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

    private void handleSearch(ActionEvent e) {
        String userType = (String) userTypeComboBox.getSelectedItem();
        if ("Student".equals(userType)) {
            if (!studentIdField.getText().isEmpty()) {
                // Search Student by ID
                Student student = studentService.findStudentById(studentIdField.getText());
                if (student != null) {
                    studentNameField.setText(student.getName());
                    studentDepartmentComboBox.setSelectedItem(student.getDepartment());
                    studentPcModelComboBox.setSelectedItem(student.getPcModel());
                    studentMacAddressField.setText(student.getMacAddress());
                } else {
                    JOptionPane.showMessageDialog(this, "Student not found.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please enter the Student ID.");
            }
        } else if ("Staff".equals(userType)) {
            if (!staffNameField.getText().isEmpty()) {
                // Search Staff by Name
                Staff staff = staffService.findStaffByName(staffNameField.getText());
                if (staff != null) {
                    staffRoleField.setText(staff.getRole());
                    staffTypeComboBox.setSelectedItem(staff.getType());
                    staffPcModelComboBox.setSelectedItem(staff.getPcModel());
                    staffMacAddressField.setText(staff.getMacAddress());
                } else {
                    JOptionPane.showMessageDialog(this, "Staff not found.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please enter the Staff Name.");
            }
        }
    }

    private void handleDelete(ActionEvent e) {
        String userType = (String) userTypeComboBox.getSelectedItem();
        int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this user?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        
        if (confirmation == JOptionPane.YES_OPTION) {
            if ("Student".equals(userType)) {
                if (!studentIdField.getText().isEmpty()) {
                    boolean deleted = studentService.deleteStudent(studentIdField.getText());
                    if (deleted) {
                        JOptionPane.showMessageDialog(this, "Student Deleted Successfully");
                    } else {
                        JOptionPane.showMessageDialog(this, "Student not found.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Please enter the Student ID.");
                }
            } else if ("Staff".equals(userType)) {
                if (!staffNameField.getText().isEmpty()) {
                    boolean deleted = staffService.deleteStaff(staffNameField.getText());
                    if (deleted) {
                        JOptionPane.showMessageDialog(this, "Staff Deleted Successfully");
                    } else {
                        JOptionPane.showMessageDialog(this, "Staff not found.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Please enter the Staff Name.");
                }
            }
        }
    }
}
