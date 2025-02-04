package models;

public class Student {
    private String id;
    private String name;
    private String department;
    private String pcModel;
    private String macAddress;

    // Constructor
    public Student(String id, String name, String department, String pcModel, String macAddress) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.pcModel = pcModel;
        this.macAddress = macAddress;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPcModel() {
        return pcModel;
    }

    public void setPcModel(String pcModel) {
        this.pcModel = pcModel;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }
}
