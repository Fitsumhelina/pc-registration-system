package services;

import java.util.List;

import utils.FileManager;

public class StudentService {
    private static final String FILE_NAME = "students.txt";

    public void registerStudent(String name, String id ,String department, String pcModel, String macAddress) {
        String studentData = name + "," + id + "," + department + "," + pcModel + "," + macAddress;
        FileManager.writeToFile(FILE_NAME, studentData);
    }

    public boolean updateStudent(String name, String department, String pcModel, String macAddress) {
        String oldData = name + ",";
        String newData = name + "," + department + "," + pcModel + "," + macAddress;
        return FileManager.updateFile(FILE_NAME, oldData, newData);
    }

    public boolean deleteStudent(String name) {
        return FileManager.deleteFromFile(FILE_NAME, name);
    }

    public String viewAllStudents() {
        List<String> students = FileManager.readFromFile(FILE_NAME);
        return String.join("\n", students);
    }

    public String searchStudent(String keyword) {
        List<String> students = FileManager.readFromFile(FILE_NAME);
        StringBuilder result = new StringBuilder();

        for (String student : students) {
            if (student.contains(keyword)) {
                result.append(student).append("\n");
            }
        }
        return result.length() > 0 ? result.toString() : "No students found.";
    }
}
