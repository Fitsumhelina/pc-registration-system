package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AdminFrame extends JFrame {
    private JButton registerBtn, updateBtn, deleteBtn, searchBtn, viewBtn, logoutBtn;
    private String adminName;

    public AdminFrame(String adminName) {
        this.adminName = adminName;

        setTitle("Welcome " + adminName);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180));
        JLabel headerLabel = new JLabel("Welcome " + adminName);
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Button Panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 5, 5));
        registerBtn = new JButton("Register User");
        updateBtn = new JButton("Update User");
        deleteBtn = new JButton("Delete User");
        searchBtn = new JButton("Search User");
        viewBtn = new JButton("View All Users");
        logoutBtn = new JButton("Log Out");

        buttonPanel.add(registerBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(searchBtn);
        buttonPanel.add(viewBtn);
        buttonPanel.add(logoutBtn);

        add(buttonPanel, BorderLayout.CENTER);

        // Event Listeners
        registerBtn.addActionListener(e -> openRegisterFrame());
        updateBtn.addActionListener(e -> openUpdateFrame());
        deleteBtn.addActionListener(e -> openDeleteFrame());
        searchBtn.addActionListener(e -> openSearchFrame());
        viewBtn.addActionListener(e -> openViewFrame());
        logoutBtn.addActionListener(e -> logout());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void openRegisterFrame() {
        new RegisterUserFrame(adminName);
        dispose();
    }

    private void openUpdateFrame() {
        new UpdateUserFrame();
        dispose();
    }

    private void openDeleteFrame() {
        new DeleteUserFrame();
        dispose();
    }

    private void openSearchFrame() {
        new SearchUserFrame();
        dispose();
    }

    private void openViewFrame() {
        new ViewAllUsersFrame();
        dispose();
    }

    private void logout() {
        new LoginFrame();
        dispose();
    }
}
