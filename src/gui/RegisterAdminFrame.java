package gui;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import services.AdminService;

public class RegisterAdminFrame extends JFrame {
    private JButton registerAdminBtn, updatePCBtn, deletePCBtn, viewAdminBtn, searchPCBtn;
    private AdminService adminService;

    public RegisterAdminFrame() {
        adminService = new AdminService();
        
        setTitle("Admin Dashboard");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        registerPCBtn = new JButton("Create admin");
        updatePCBtn = new JButton("Update admin");
        deletePCBtn = new JButton("Delete admin");
        searchPCBtn = new JButton("Search admin");
        viewAdminBtn = new JButton("View all admins");

        add(registerAdminBtn);
        add(updatePCBtn);
        add(deletePCBtn);
        add(viewAdminBtn);
        add(searchPCBtn);

        registerAdminBtn.addActionListener(e -> registerPC());
        updatePCBtn.addActionListener(e -> updatePC());
        deletePCBtn.addActionListener(e -> deletePC());
        viewAdminBtn.addActionListener(e -> viewAdmins());
        searchPCBtn.addActionListener(e -> searchPC());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void registerPC() {
        String adminData = JOptionPane.showInputDialog("Enter Admin Details:");
        if (adminData != null) {
            adminService.createAdmin(adminData);
            JOptionPane.showMessageDialog(this, "Admin Registered Successfully");
        }
    }

    private void updatePC() {
        String oldData = JOptionPane.showInputDialog("Enter Existing Admin Details:");
        String newData = JOptionPane.showInputDialog("Enter New Admin Details:");
        if (oldData != null && newData != null) {
            boolean success = adminService.updateAdmin(oldData, newData);
            JOptionPane.showMessageDialog(this, success ? "Admin Updated Successfully" : "Admin Not Found");
        }
    }

    private void deletePC() {
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

    private void searchPC() {
        String keyword = JOptionPane.showInputDialog("Enter Search Keyword:");
        if (keyword != null) {
            List<String> results = adminService.searchAdmin(keyword);
            JOptionPane.showMessageDialog(this, results.isEmpty() ? "No Matching Admins Found" : String.join("\n", results));
        }
    }
}
