package services;

import java.util.ArrayList;
import java.util.List;

import models.Student;
import utils.FileManager;


public class StudentService {
    private static final String FILE_NAME = "students.txt";

    public void registerStudent(String name, String id, String department, String pcModel, String macAddress , String registerdBy) {
        String studentData = name + "," + id + "," + department + "," + pcModel + "," + macAddress + "," + registerdBy;
        FileManager.writeToFile(FILE_NAME, studentData);
    }

    public boolean updateStudent(String id, String name, String department, String pcModel, String macAddress) {
        // Read all students' data from file
        List<String> students = FileManager.readFromFile(FILE_NAME);
        boolean updated = false;

        // Iterate through the students and find the one with the matching ID
        for (int i = 0; i < students.size(); i++) {
            String studentData = students.get(i);
            String[] studentDetails = studentData.split(",");

            if (studentDetails[1].equals(id)) { // Match by student ID
                // Replace the student's old data with the new data
                String newData = name + "," + id + "," + department + "," + pcModel + "," + macAddress;
                students.set(i, newData);
                updated = true;
                break;
            }
        }

        if (updated) {
            // Write updated list back to file
            return FileManager.writeToFile(FILE_NAME, students);
        }
        return false; // If no student found with matching ID
    }

    public boolean deleteStudent(String id) {
        // Read all students' data from file
        List<String> students = FileManager.readFromFile(FILE_NAME);
        boolean deleted = false;

        // Iterate through the students and find the one with the matching ID
        for (int i = 0; i < students.size(); i++) {
            String studentData = students.get(i);
            String[] studentDetails = studentData.split(",");

            if (studentDetails[1].equals(id)) { // Match by student ID
                students.remove(i);
                deleted = true;
                break;
            }
        }

        if (deleted) {
            // Write updated list back to file
            return FileManager.writeToFile(FILE_NAME, students);
        }
        return false; // If no student found with matching ID
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
    public Student findStudentById(String id) {
        List<String> students = FileManager.readFromFile(FILE_NAME);
        for (String studentData : students) {
            String[] studentDetails = studentData.split(",");
            if (studentDetails[1].equals(id)) { // Use index 1 for ID (second element)
                Student student = new Student(studentDetails[1], studentDetails[0], studentDetails[2], studentDetails[3], studentDetails[4], studentDetails[5]);
                return student;
            }
        }
        return null; // Return null if student not found
    }
    
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        List<String> studentDataList = FileManager.readFromFile(FILE_NAME);
    
        for (String data : studentDataList) {
            String[] studentDetails = data.split(",");
            Student student = new Student(studentDetails[0], studentDetails[1], studentDetails[2], studentDetails[3], studentDetails[4], studentDetails[5]);
            students.add(student);
        }
        return students;
    }
    
}
