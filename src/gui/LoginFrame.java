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
        setLayout(new GridBagLayout());

        // GridBagConstraints for responsive layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Create the header panel with title
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.BLUE);
        JLabel headerLabel = new JLabel("DBU PC MANAGEMENT SYSTEM", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);
        gbc.gridwidth = 2;
        add(headerPanel, gbc);

        // Username input
        JPanel usernamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);
        usernameField.setPreferredSize(new Dimension(250, 30));
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameField);
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(usernamePanel, gbc);

        // Password input
        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);
        passwordField.setPreferredSize(new Dimension(250, 30));
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        gbc.gridy = 2;
        add(passwordPanel, gbc);

        // Login button
        loginButton = new JButton("Login");
        loginButton.setBackground(new Color(70, 130, 180)); // Steel blue color
        loginButton.setForeground(Color.WHITE);
        loginButton.setPreferredSize(new Dimension(250, 40));
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(loginButton, gbc);

        // Action listener for the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Check if the user is the superadmin
                if (username.equals("superadmin") && validateSuperAdminPassword(password)) {
                    // Route to RegisterAdminFrame if superadmin is valid
                    new SuperAdminFrame(); // Route to RegisterAdminFrame
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

        // Center the frame on the screen initially
        setLocationRelativeTo(null);

        // Make the window resizable
        setResizable(true);

        // Adjust components dynamically when window size changes
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent componentEvent) {
                adjustComponentSizes();
            }
        });

        setVisible(true);
    }

    // Adjust component sizes dynamically based on window resizing
    private void adjustComponentSizes() {
        // Make the fields and button responsive to window resizing
        usernameField.setMinimumSize(new Dimension(getWidth() / 2, 30));
        passwordField.setMinimumSize(new Dimension(getWidth() / 2, 30));
        loginButton.setMinimumSize(new Dimension(getWidth() / 2, 40));

        // Adjust text fields and buttons based on window size
        usernameField.setPreferredSize(new Dimension(getWidth() / 2, 30));
        passwordField.setPreferredSize(new Dimension(getWidth() / 2, 30));
        loginButton.setPreferredSize(new Dimension(getWidth() / 2, 40));
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

    public static void main(String[] args) {
        new LoginFrame();
    }
}
