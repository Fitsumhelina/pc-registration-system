package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import services.StudentService;
import services.StaffService;
import models.Student;
import models.Staff;

public class ViewAllUsersFrame extends JFrame {
    private JComboBox<String> userTypeComboBox;
    private JTable userTable;
    private DefaultTableModel tableModel;
    private StudentService studentService;
    private StaffService staffService;

    public ViewAllUsersFrame() {
        studentService = new StudentService();
        staffService = new StaffService();

        setTitle("View All Users");
        setSize(600, 400);
        setLayout(new BorderLayout());

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180));
        JLabel headerLabel = new JLabel("View All Registered Users");
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        // User Type ComboBox
        JPanel topPanel = new JPanel();
        userTypeComboBox = new JComboBox<>(new String[]{"Select User Type", "Student", "Staff"});
        topPanel.add(userTypeComboBox);
        JButton viewButton = new JButton("View Users");
        topPanel.add(viewButton);
        add(topPanel, BorderLayout.CENTER);

        // Table Setup
        tableModel = new DefaultTableModel();
        userTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(userTable);
        add(scrollPane, BorderLayout.SOUTH);

        // Event Listener
        viewButton.addActionListener(e -> viewUsers());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void viewUsers() {
        String selectedUserType = (String) userTypeComboBox.getSelectedItem();

        // Clear the table before populating
        tableModel.setRowCount(0);

        if ("Student".equals(selectedUserType)) {
            displayStudents();
        } else if ("Staff".equals(selectedUserType)) {
            displayStaff();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a user type.");
        }
    }

    private void displayStudents() {
        String[] columnNames = {"Student ID", "Name", "Department", "PC Model", "MAC Address"};
        tableModel.setColumnIdentifiers(columnNames);

        // Fetch students from the service
        java.util.List<Student> students = studentService.getAllStudents();

        for (Student student : students) {
            tableModel.addRow(new Object[]{
                student.getId(),
                student.getName(),
                student.getDepartment(),
                student.getPcModel(),
                student.getMacAddress()
            });
        }
    }

    private void displayStaff() {
        String[] columnNames = {"Staff Name", "Role", "Type", "PC Model", "MAC Address"};
        tableModel.setColumnIdentifiers(columnNames);

        // Fetch staff from the service
        java.util.List<Staff> staffList = staffService.getAllStaff();

        for (Staff staff : staffList) {
            tableModel.addRow(new Object[]{
                staff.getName(),
                staff.getRole(),
                staff.getType(),
                staff.getPcModel(),
                staff.getMacAddress()
            });
        }
    }
}
