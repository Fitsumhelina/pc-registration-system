package services;

import java.util.ArrayList;
import java.util.List;

import utils.FileManager;

public class AdminService {
    private static final String FILE_NAME = "admins.txt";

    // Create new admin
    public void createAdmin(String adminData) {
        FileManager.writeToFile(FILE_NAME, adminData);
    }

    // Get all admins
    public List<String> getAllAdmins() {
        return FileManager.readFromFile(FILE_NAME);
    }

    // Update an admin's data
    public boolean updateAdmin(String oldData, String newData) {
        return FileManager.updateFile(FILE_NAME, oldData, newData);
    }

    // Delete an admin
    public boolean deleteAdmin(String adminData) {
        return FileManager.deleteFromFile(FILE_NAME, adminData);
    }

    // Search for admins by keyword
    public List<String> searchAdmin(String keyword) {
        List<String> results = new ArrayList<>();
        for (String admin : getAllAdmins()) {
            if (admin.contains(keyword)) {
                results.add(admin);
            }
        }
        return results;
    }
}
