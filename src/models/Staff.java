package model;

public class Staff {
    private String name;
    private String role;
    private String type;
    private String pcModel;
    private String macAddress;

    // Constructor
    public Staff(String name, String role, String type, String pcModel, String macAddress) {
        this.name = name;
        this.role = role;
        this.type = type;
        this.pcModel = pcModel;
        this.macAddress = macAddress;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
