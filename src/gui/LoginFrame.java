package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import services.AdminService;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginFrame extends JFrame {
    // DBU PC MANAGEMENT SYSTEM
    // This system manages the login process for admins and superadmins.
    // Code written by [Your Name], all rights reserved.

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private AdminService adminService;

    public LoginFrame() {
        adminService = new AdminService(); // Initialize the AdminService

        setTitle("Login - DBU PC Management System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create the header panel with title
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.BLUE);
        JLabel headerLabel = new JLabel("DBU PC MANAGEMENT SYSTEM", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);

        // Create the login form panel
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        
        // Username input
        JPanel usernamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);
        usernameField.setPreferredSize(new Dimension(250, 30));
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameField);
        loginPanel.add(usernamePanel);

        // Password input
        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);
        passwordField.setPreferredSize(new Dimension(250, 30));
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        loginPanel.add(passwordPanel);

        // Login button
        loginButton = new JButton("Login");
        loginButton.setBackground(new Color(70, 130, 180)); // Steel blue color
        loginButton.setForeground(Color.WHITE);
        loginButton.setPreferredSize(new Dimension(250, 40));
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginPanel.add(loginButton);

        // Action listener for the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Check if the user is the superadmin
                if (username.equals("superadmin") && validateSuperAdminPassword(password)) {
                    // Route to RegisterAdminFrame if superadmin is valid
                    new RegisterAdminFrame(); // Route to RegisterAdminFrame
                    dispose(); // Close the login frame
                } else if (adminService.getAllAdmins().contains(username + "," + password)) {
                    // If the user is an admin from the saved file
                    new AdminFrame(); // Route to AdminFrame
                    dispose(); // Close the login frame
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid credentials!");
                }
            }
        });

        // Add the panels to the main frame
        add(headerPanel, BorderLayout.NORTH);
        add(loginPanel, BorderLayout.CENTER);

        // Center the frame on the screen
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Method to validate super admin password by reading from a file
    private boolean validateSuperAdminPassword(String inputPassword) {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/superadmin.txt"))) {
            String line = reader.readLine();
            if (line != null && line.contains(",")) {
                String[] credentials = line.split(",");
                String superAdminPassword = credentials[1];
                return inputPassword.equals(superAdminPassword);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
