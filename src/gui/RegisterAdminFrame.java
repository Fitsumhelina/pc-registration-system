package gui;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import services.AdminService;

public class RegisterAdminFrame extends JFrame {
    private JButton registerAdminBtn, updateAdminBtn, deleteAdminBtn, viewAdminBtn, searchAdminBtn;
    private AdminService adminService;

    public RegisterAdminFrame() {
        adminService = new AdminService();
        
        setTitle("SuperAdmin Dashboard");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        registerAdminBtn = new JButton("Create admin");
        updateAdminBtn = new JButton("Update admin");
        deleteAdminBtn = new JButton("Delete admin");
        searchAdminBtn = new JButton("Search admin");
        viewAdminBtn = new JButton("View all admins");

        add(registerAdminBtn);
        add(updateAdminBtn);
        add(deleteAdminBtn);
        add(viewAdminBtn);
        add(searchAdminBtn); 

        registerAdminBtn.addActionListener(e -> registerAdmin());
        updateAdminBtn.addActionListener(e -> updateAdmin());
        deleteAdminBtn.addActionListener(e -> deleteAdmin());
        searchAdminBtn.addActionListener(e -> searchAdmin());
        viewAdminBtn.addActionListener(e -> viewAdmins());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void registerAdmin() {
        JTextField nameField = new JTextField();
        JTextField passwordField = new JPasswordField();
        Object[] fields = {"Name:", nameField, "Password:", passwordField};
        int option = JOptionPane.showConfirmDialog(this, fields, "Enter Admin Details", JOptionPane.OK_CANCEL_OPTION);
        
        if (option == JOptionPane.OK_OPTION) {
            String adminData = nameField.getText() + "," + passwordField.getText();
            adminService.createAdmin(adminData);
            JOptionPane.showMessageDialog(this, "Admin Registered Successfully");
        }
    }

    private void updateAdmin() {
        String oldData = JOptionPane.showInputDialog("Enter Existing Admin Details (Name,Password):");
        String newData = JOptionPane.showInputDialog("Enter New Admin Details (Name,Password):");
        if (oldData != null && newData != null) {
            boolean success = adminService.updateAdmin(oldData, newData);
            JOptionPane.showMessageDialog(this, success ? "Admin Updated Successfully" : "Admin Not Found");
        }
    }

    private void deleteAdmin() {
        String adminData = JOptionPane.showInputDialog("Enter Admin Details to Delete (Name,Password):");
        if (adminData != null) {
            boolean success = adminService.deleteAdmin(adminData);
            JOptionPane.showMessageDialog(this, success ? "Admin Deleted Successfully" : "Admin Not Found");
        }
    }

    private void viewAdmins() {
        List<String> admins = adminService.getAllAdmins();
        
        if (admins.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No Admins Found");
            return;
        }
        
        String[] columnNames = {"Name", "Password"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        for (String admin : admins) {
            String[] adminDetails = admin.split(",");
            model.addRow(adminDetails);
        }
        
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        JOptionPane.showMessageDialog(this, scrollPane, "All Admins", JOptionPane.PLAIN_MESSAGE);
    }

    private void searchAdmin() {
        String keyword = JOptionPane.showInputDialog("Enter Search Keyword:");
        if (keyword != null) {
            List<String> results = adminService.searchAdmin(keyword);
            if (results.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No Matching Admins Found");
                return;
            }
            
            String[] columnNames = {"Name", "Password"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);
            for (String admin : results) {
                String[] adminDetails = admin.split(",");
                model.addRow(adminDetails);
            }
            
            JTable table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);
            JOptionPane.showMessageDialog(this, scrollPane, "Search Results", JOptionPane.PLAIN_MESSAGE);
        }
    }
}
