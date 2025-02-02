package models;

public class StudentPC {
    private String studentID;
    private String pcModel;
    private String macAddress;

    public StudentPC(String studentID, String pcModel, String macAddress) {
        this.studentID = studentID;
        this.pcModel = pcModel;
        this.macAddress = macAddress;
    }

    public String getStudentID() { return studentID; }
    public String getPcModel() { return pcModel; }
    public String getMacAddress() { return macAddress; }
}
