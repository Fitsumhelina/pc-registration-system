package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

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

        // Automatically display students on startup or prompt user for selection
        userTypeComboBox.setSelectedIndex(0); // Default to "Select User Type"
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void viewUsers() {
        String selectedUserType = (String) userTypeComboBox.getSelectedItem();

        // Clear the table before populating
        tableModel.setRowCount(0);

        if ("Student".equals(selectedUserType)) {
            int response = JOptionPane.showConfirmDialog(this, "Do you want to view all students?", "Confirm View", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                displayStudents();
            }
        } else if ("Staff".equals(selectedUserType)) {
            int response = JOptionPane.showConfirmDialog(this, "Do you want to view all staff?", "Confirm View", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                displayStaff();
            }
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
