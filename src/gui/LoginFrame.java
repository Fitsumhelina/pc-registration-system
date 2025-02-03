package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import services.AdminService;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private AdminService adminService;

    public LoginFrame() {
        adminService = new AdminService(); // Initialize the AdminService

        setTitle("Login");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        loginButton = new JButton("Login");
        add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Check if the user is the superadmin (check if "superadmin" exists in the admin file)
                if (username.equals("superadmin") && password.equals("1234")) {
                   new RegisterAdminFrame(); // Route to RegisterAdminFrame
                   dispose();
               
                } else if (adminService.getAllAdmins().contains(username + "," + password)) {
                    // If the user is an admin from the saved file
                    new AdminFrame(); // Route to AdminFrame
                    dispose(); // Close the login frame
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid credentials!");
                }
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
