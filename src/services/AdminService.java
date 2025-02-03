package services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utils.FileManager;

public class AdminService {
    private static final String FILE_NAME = "data/admins.txt";

    public void createAdmin(String adminData) {
        FileManager.saveData(FILE_NAME, adminData);
    }

    public List<String> getAllAdmins() {
        List<String> admins = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                admins.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return admins;
    }

    public boolean updateAdmin(String oldData, String newData) {
        List<String> admins = getAllAdmins();
        boolean updated = false;

        try (FileWriter writer = new FileWriter(FILE_NAME, false)) {
            for (String admin : admins) {
                if (admin.equals(oldData)) {
                    writer.write(newData + "\n");
                    updated = true;
                } else {
                    writer.write(admin + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return updated;
    }

    public boolean deleteAdmin(String adminData) {
        List<String> admins = getAllAdmins();
        boolean removed = admins.remove(adminData);

        try (FileWriter writer = new FileWriter(FILE_NAME, false)) {
            for (String admin : admins) {
                writer.write(admin + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return removed;
    }

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
