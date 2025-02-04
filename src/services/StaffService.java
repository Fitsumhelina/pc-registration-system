package services;

import java.util.List;

import models.Staff;
import utils.FileManager;


public class StaffService {
    private static final String FILE_NAME = "staff.txt";

    public void registerStaff(String name, String role, String type, String pcModel, String macAddress) {
        String staffData = name + "," + role + "," + type + "," + pcModel + "," + macAddress;
        FileManager.writeToFile(FILE_NAME, staffData);
    }

    public boolean updateStaff(String name, String role, String type, String pcModel, String macAddress) {
        // Read all staff data from file
        List<String> staff = FileManager.readFromFile(FILE_NAME);
        boolean updated = false;

        // Iterate through the staff list and find the one with the matching name
        for (int i = 0; i < staff.size(); i++) {
            String staffData = staff.get(i);
            String[] staffDetails = staffData.split(",");

            if (staffDetails[0].equals(name)) { // Match by staff name
                // Replace the staff's old data with the new data
                String newData = name + "," + role + "," + type + "," + pcModel + "," + macAddress;
                staff.set(i, newData);
                updated = true;
                break;
            }
        }

        if (updated) {
            // Write the updated list back to the file
            return FileManager.writeToFile(FILE_NAME, staff);
        }
        return false; // If no staff found with the matching name
    }

    public boolean deleteStaff(String name) {
        // Read all staff data from file
        List<String> staff = FileManager.readFromFile(FILE_NAME);
        boolean deleted = false;

        // Iterate through the staff list and find the one with the matching name
        for (int i = 0; i < staff.size(); i++) {
            String staffData = staff.get(i);
            String[] staffDetails = staffData.split(",");

            if (staffDetails[0].equals(name)) { // Match by staff name
                staff.remove(i);
                deleted = true;
                break;
            }
        }

        if (deleted) {
            // Write the updated list back to the file
            return FileManager.writeToFile(FILE_NAME, staff);
        }
        return false; // If no staff found with the matching name
    }

    public String viewAllStaff() {
        List<String> staff = FileManager.readFromFile(FILE_NAME);
        return String.join("\n", staff);
    }

    public String searchStaff(String keyword) {
        List<String> staff = FileManager.readFromFile(FILE_NAME);
        StringBuilder result = new StringBuilder();

        for (String s : staff) {
            if (s.contains(keyword)) {
                result.append(s).append("\n");
            }
        }
        return result.length() > 0 ? result.toString() : "No staff found.";
    }
    public Staff findStaffByName(String name) {
        List<String> staff = FileManager.readFromFile(FILE_NAME);
        for (String staffData : staff) {
            String[] staffDetails = staffData.split(",");
            if (staffDetails[0].equals(name)) { // Assuming the first column is the name
                return new Staff(staffDetails[0], staffDetails[1], staffDetails[2], staffDetails[3], staffDetails[4]);
            }
        }
        return null; // Return null if staff not found
    }
    
}
