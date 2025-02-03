package gui;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
        String adminData = JOptionPane.showInputDialog("Enter Admin Details:");
        if (adminData != null) {
            adminService.createAdmin(adminData);
            JOptionPane.showMessageDialog(this, "Admin Registered Successfully");
        }
    }

    private void updateAdmin() {
        String oldData = JOptionPane.showInputDialog("Enter Existing Admin Details:");
        String newData = JOptionPane.showInputDialog("Enter New Admin Details:");
        if (oldData != null && newData != null) {
            boolean success = adminService.updateAdmin(oldData, newData);
            JOptionPane.showMessageDialog(this, success ? "Admin Updated Successfully" : "Admin Not Found");
        }
    }

    private void deleteAdmin() {
        String adminData = JOptionPane.showInputDialog("Enter Admin Details to Delete:");
        if (adminData != null) {
            boolean success = adminService.deleteAdmin(adminData);
            JOptionPane.showMessageDialog(this, success ? "Admin Deleted Successfully" : "Admin Not Found");
        }
    }

    private void viewAdmins() {
        List<String> admins = adminService.getAllAdmins();
        JOptionPane.showMessageDialog(this, admins.isEmpty() ? "No Admins Found" : String.join("\n", admins));
    }

    private void searchAdmin() {
        String keyword = JOptionPane.showInputDialog("Enter Search Keyword:");
        if (keyword != null) {
            List<String> results = adminService.searchAdmin(keyword);
            JOptionPane.showMessageDialog(this, results.isEmpty() ? "No Matching Admins Found" : String.join("\n", results));
        }
    }
}
