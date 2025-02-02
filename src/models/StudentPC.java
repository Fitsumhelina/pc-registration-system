package models;

public class StudentPC {
    private String studentName;
    private String studentID;
    private String studentDepartment;
    private String pcModel;
    private String macAddress;

    public StudentPC(String studentName, String studentID, String studentDepartment,  String pcModel, String macAddress) {
        this.studentName = studentName;
        this.studentID = studentID;
        this.studentDepartment = studentDepartment; 
        this.pcModel = pcModel;
        this.macAddress = macAddress;
    }

    public String getStudentName() { return studentName; }
    public String getStudentID() { return studentID; }
    public String getStudentDepartment() { return studentDepartment; }
    public String getPcModel() { return pcModel; }
    public String getMacAddress() { return macAddress; }
}
