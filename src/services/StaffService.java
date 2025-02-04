package services;

import java.util.List;
import utils.FileManager;

public class StaffService {
    private static final String FILE_NAME = "staff.txt";

    public void registerStaff(String name, String role, String type, String pcModel, String macAddress) {
        String staffData = name + "," + role + "," + type + "," + pcModel + "," + macAddress;
        FileManager.writeToFile(FILE_NAME, staffData);
    }

    public boolean updateStaff(String name, String role, String type, String pcModel, String macAddress) {
        String oldData = name + ",";
        String newData = name + "," + role + "," + type + "," + pcModel + "," + macAddress;
        return FileManager.updateFile(FILE_NAME, oldData, newData);
    }

    public boolean deleteStaff(String name) {
        return FileManager.deleteFromFile(FILE_NAME, name);
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
}
