package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import models.Staff;
import models.Student;
import services.StaffService;
import services.StudentService;

public class ViewAllUsersFrame extends JFrame {
    private JComboBox<String> userTypeComboBox;
    private JTable userTable;
    private DefaultTableModel tableModel;
    private JButton  backBtn;
    private StudentService studentService;
    private StaffService staffService;

    public ViewAllUsersFrame() {
        studentService = new StudentService();
        staffService = new StaffService();

        setTitle("Search User");
        setSize(800, 600); // Increased size for better view
        setLayout(new BorderLayout());

        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180));
        JLabel headerLabel = new JLabel("Search All Users");
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

        // Table Setup
        tableModel = new DefaultTableModel();
        userTable = new JTable(tableModel);
        userTable.setRowHeight(30); // Make rows taller for better visibility
        userTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); // Allow auto-resizing of columns
        JScrollPane scrollPane = new JScrollPane(userTable);
        scrollPane.setPreferredSize(new Dimension(750, 400)); // Set the scrollPane size for the table
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        formPanel.add(scrollPane, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        backBtn = new JButton("Back");

        buttonPanel.add(backBtn);

        gbc.gridy = 2;
        gbc.gridwidth = 2;
        formPanel.add(buttonPanel, gbc);

        add(formPanel, BorderLayout.CENTER);

        // Event Listeners
        userTypeComboBox.addActionListener(e -> toggleTable());
        backBtn.addActionListener(e -> {
            new AdminFrame("Admin"); // Adjust this based on your actual back screen.
            dispose();
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void toggleTable() {
        String selectedType = (String) userTypeComboBox.getSelectedItem();
        // Clear the table when the user type is changed
        tableModel.setRowCount(0);

        if ("Student".equals(selectedType)) {
            displayStudentTable();
        } else if ("Staff".equals(selectedType)) {
            displayStaffTable();
        }
    }

    private void displayStudentTable() {
        // Setting column names for the student table
        String[] columnNames = {"Student ID", "Name", "Department", "PC Model", "MAC Address", "Registered By"};
        tableModel.setColumnIdentifiers(columnNames);

        // Fetch students from the service and add them to the table
        java.util.List<Student> students = studentService.getAllStudents();
        for (Student student : students) {
            tableModel.addRow(new Object[]{
                student.getId(),
                student.getName(),
                student.getDepartment(),
                student.getPcModel(),
                student.getMacAddress(),
                student.getRegisteredBy()
            });
        }
    }

    private void displayStaffTable() {
        // Setting column names for the staff table
        String[] columnNames = {"Staff Name", "Role", "Type", "PC Model", "MAC Address", "Registered By"};
        tableModel.setColumnIdentifiers(columnNames);

        // Fetch staff from the service and add them to the table
        java.util.List<Staff> staffList = staffService.getAllStaff();
        for (Staff staff : staffList) {
            tableModel.addRow(new Object[]{
                staff.getName(),
                staff.getRole(),
                staff.getType(),
                staff.getPcModel(),
                staff.getMacAddress(),
                staff.getRegisteredBy()
            });
        }
    }

    private void handleSearch(ActionEvent e) {
        String userType = (String) userTypeComboBox.getSelectedItem();

        if ("Student".equals(userType)) {
            displayStudentTable();
        } else if ("Staff".equals(userType)) {
            displayStaffTable();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a user type.");
        }
    }
}
