package services;

import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

// import utils.FileManager;

public class AdminService extends JFrame {
    private final JTextField adminNameField, adminIDField;
    private final JButton createBtn, updateBtn, deleteBtn, searchBtn, displayBtn;
    private static final String FILE_PATH = "data/admins.txt";

    public AdminService() {
        setTitle("Admin Management");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel("Admin Name:"));
        adminNameField = new JTextField();
        add(adminNameField);

        add(new JLabel("Admin ID:"));
        adminIDField = new JTextField();
        add(adminIDField);

        createBtn = new JButton("Create Admin");
        updateBtn = new JButton("Update Admin");
        deleteBtn = new JButton("Delete Admin");
        searchBtn = new JButton("Search Admin");
        displayBtn = new JButton("Display All Admins");

        add(createBtn);
        add(updateBtn);
        add(deleteBtn);
        add(searchBtn);
        add(displayBtn);

        createBtn.addActionListener(e -> createAdmin());
        updateBtn.addActionListener(e -> updateAdmin());
        deleteBtn.addActionListener(e -> deleteAdmin());
        searchBtn.addActionListener(e -> searchAdmin());
        displayBtn.addActionListener(e -> displayAdmins());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createAdmin() {
        String name = adminNameField.getText().trim();
        String id = adminIDField.getText().trim();
        if (name.isEmpty() || id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if admin already exists
        if (adminExists(id)) {
            JOptionPane.showMessageDialog(this, "Admin with this ID already exists!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        FileManager.saveData("admins.txt", name + "," + id);
        JOptionPane.showMessageDialog(this, "Admin Created Successfully!");
    }

    private void updateAdmin() {
        String id = adminIDField.getText().trim();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter Admin ID to update!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<String> admins = readAdmins();
        boolean found = false;

        for (int i = 0; i < admins.size(); i++) {
            String[] parts = admins.get(i).split(",");
            if (parts.length > 1 && parts[1].equals(id)) {
                String newName = JOptionPane.showInputDialog(this, "Enter new admin name:", parts[0]);
                if (newName != null && !newName.trim().isEmpty()) {
                    admins.set(i, newName + "," + id);
                    writeAdmins(admins);
                    JOptionPane.showMessageDialog(this, "Admin Updated Successfully!");
                }
                found = true;
                break;
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(this, "Admin Not Found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteAdmin() {
        String id = adminIDField.getText().trim();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter Admin ID to delete!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<String> admins = readAdmins();
        List<String> updatedList = admins.stream()
                .filter(line -> !line.split(",")[1].equals(id))
                .collect(Collectors.toList());

        if (admins.size() == updatedList.size()) {
            JOptionPane.showMessageDialog(this, "Admin Not Found!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            writeAdmins(updatedList);
            JOptionPane.showMessageDialog(this, "Admin Deleted Successfully!");
        }
    }

    private void searchAdmin() {
        String id = adminIDField.getText().trim();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter Admin ID to search!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<String> admins = readAdmins();
        for (String admin : admins) {
            String[] parts = admin.split(",");
            if (parts.length > 1 && parts[1].equals(id)) {
                JOptionPane.showMessageDialog(this, "Admin Found: " + parts[0] + " (ID: " + parts[1] + ")");
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Admin Not Found!", "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void displayAdmins() {
        File file = new File(FILE_PATH);
        if (!file.exists() || file.length() == 0) {
            JOptionPane.showMessageDialog(this, "No Admins Found!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        StringBuilder adminList = new StringBuilder("Registered Admins:\n");
        List<String> admins = readAdmins();
        for (String admin : admins) {
            adminList.append(admin).append("\n");
        }
        JOptionPane.showMessageDialog(this, adminList.toString(), "All Admins", JOptionPane.INFORMATION_MESSAGE);
    }

    private boolean adminExists(String id) {
        List<String> admins = readAdmins();
        for (String admin : admins) {
            String[] parts = admin.split(",");
            if (parts.length > 1 && parts[1].equals(id)) {
                return true;
            }
        }
        return false;
    }

    private List<String> readAdmins() {
        List<String> admins = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) return admins;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                admins.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading data: " + e.getMessage());
        }
        return admins;
    }

    private void writeAdmins(List<String> admins) {
        try (FileWriter writer = new FileWriter(FILE_PATH, false)) { // Overwrites file
            for (String admin : admins) {
                writer.write(admin + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing data: " + e.getMessage());
        }
    }
}
