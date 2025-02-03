package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import services.AdminService;

public class SuperAdminFrame extends JFrame {
    private JButton registerAdminBtn, updateAdminBtn, deleteAdminBtn, viewAdminBtn, searchAdminBtn, existBtn;
    private AdminService adminService;

    public SuperAdminFrame() {
        adminService = new AdminService();
        
        // Set basic window properties
        setTitle("SuperAdmin Dashboard");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Set a sleek background color or gradient
        setContentPane(new JLabel(new ImageIcon("path_to_your_image.jpg")));
        getContentPane().setBackground(new Color(255, 255, 255));  // White background

        // Set layout
        setLayout(new BorderLayout());

        // Header panel with a gradient color
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180));
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        JLabel headerLabel = new JLabel("SuperAdmin Dashboard");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 26));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Main content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(new Color(240, 240, 240));  // Light gray background
        
        // Initialize buttons with a desktop look
        registerAdminBtn = createStyledButton("Create Admin");
        updateAdminBtn = createStyledButton("Update Admin");
        deleteAdminBtn = createStyledButton("Delete Admin");
        searchAdminBtn = createStyledButton("Search Admin");
        viewAdminBtn = createStyledButton("View All Admins");
        existBtn = createStyledButton("Log Out");

        registerAdminBtn.addActionListener(e -> registerAdmin());
        updateAdminBtn.addActionListener(e -> updateAdmin());
        deleteAdminBtn.addActionListener(e -> deleteAdmin());
        searchAdminBtn.addActionListener(e -> searchAdmin());
        viewAdminBtn.addActionListener(e -> viewAdmins());
        existBtn.addActionListener(e -> {
            new LoginFrame();
            dispose();
        });


        contentPanel.add(createButtonPanel(registerAdminBtn));
        contentPanel.add(createButtonPanel(updateAdminBtn));
        contentPanel.add(createButtonPanel(deleteAdminBtn));
        contentPanel.add(createButtonPanel(viewAdminBtn));
        contentPanel.add(createButtonPanel(searchAdminBtn));
        contentPanel.add(createButtonPanel(existBtn));

        add(contentPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel createButtonPanel(JButton button) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.setOpaque(false); // Make background transparent for panels
        
        panel.add(button);
        return panel;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setBackground(new Color(70, 130, 180)); // Steel blue color
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2));
        button.setPreferredSize(new Dimension(250, 45));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(100, 150, 200)); // Hover effect
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(70, 130, 180)); // Original color
            }
        });
        
        button.setToolTipText(text);
        return button;
    }

    private void registerAdmin() {
        JTextField nameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        Object[] fields = {"Name:", nameField, "Password:", passwordField};
        int option = JOptionPane.showConfirmDialog(this, fields, "Enter Admin Details", JOptionPane.OK_CANCEL_OPTION);
        
        if (option == JOptionPane.OK_OPTION) {
            String adminData = nameField.getText() + "," + new String(passwordField.getPassword());
            adminService.createAdmin(adminData);
            JOptionPane.showMessageDialog(this, "Admin Registered Successfully");
        }
    }

    private void updateAdmin() {
        JTextField oldNameField = new JTextField();
        JPasswordField oldPasswordField = new JPasswordField();
        JTextField newNameField = new JTextField();
        JPasswordField newPasswordField = new JPasswordField();
        
        Object[] fields = {
            "Old Name:", oldNameField, "Old Password:", oldPasswordField, 
            "New Name:", newNameField, "New Password:", newPasswordField
        };

        int option = JOptionPane.showConfirmDialog(this, fields, "Update Admin Details", JOptionPane.OK_CANCEL_OPTION);
        
        if (option == JOptionPane.OK_OPTION) {
            String oldData = oldNameField.getText() + "," + new String(oldPasswordField.getPassword());
            String newData = newNameField.getText() + "," + new String(newPasswordField.getPassword());
            boolean success = adminService.updateAdmin(oldData, newData);
            JOptionPane.showMessageDialog(this, success ? "Admin Updated Successfully" : "Admin Not Found");
        }
    }

    private void deleteAdmin() {
        JTextField nameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        
        Object[] fields = {"Name:", nameField, "Password:", passwordField};
        int option = JOptionPane.showConfirmDialog(this, fields, "Enter Admin Details to Delete", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String adminData = nameField.getText() + "," + new String(passwordField.getPassword());
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
