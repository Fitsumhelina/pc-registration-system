import java.util.*;

class Student {
    String full_name;
    String student_id;
    String gender;
    String phone_number;
    String department;
    int year;
    String pc_name;
    String pc_serial;
    String pc_model;

    public Student(String full_name, String student_id, String gender, String phone_number, String department, int year, String pc_name, String pc_serial, String pc_model) {
        this.full_name = full_name;
        this.student_id = student_id;
        this.gender = gender;
        this.phone_number = phone_number;
        this.department = department;
        this.year = year;
        this.pc_name = pc_name;
        this.pc_serial = pc_serial;
        this.pc_model = pc_model;
    }

    public void displayDetails() {
        System.out.println("----------------------");
        System.out.println("Student Name: " + full_name);
        System.out.println("Student ID: " + student_id);
        System.out.println("Student Gender: " + gender);
        System.out.println("Student Phone Number: " + phone_number);
        System.out.println("Student Department: " + department);
        System.out.println("Student Year: " + year);
        System.out.println("Student's PC Name: " + pc_name);
        System.out.println("Student's PC Serial: " + pc_serial);
        System.out.println("Student's PC Model: " + pc_model);
        System.out.println("----------------------");
    }
}

class Staff extends Student{
    String staff_id;
    String job;

    public Staff(String full_name, String staff_id, String gender, String phone_number, String department, String job, int year, String pc_name, String pc_serial, String pc_model) {
        super(full_name, staff_id, gender, phone_number, department, year, pc_name, pc_serial, pc_model); 
        this.staff_id = staff_id;
        this.job = job;
    }

    public void displayDetails() {
        System.out.println("----------------------");
        System.out.println("Staff's Name: " + full_name);
        System.out.println("Staff's ID: " + staff_id);
        System.out.println("Staff's Gender: " + gender);
        System.out.println("Staff's Phone Number: " + phone_number);
        System.out.println("Staff's Department: " + department);
        System.out.println("Staff's Job: " + job);
        System.out.println("Staff's Year: " + year);
        System.out.println("Staff's PC Name: " + pc_name);
        System.out.println("Staff's PC Serial: " + pc_serial);
        System.out.println("Staff's PC Model: " + pc_model);
        System.out.println("----------------------");
    }
}

class Nonstaff extends Student{
    String nonstaff_id;

    public Nonstaff(String full_name, String nonstaff_id, String gender, String phone_number, String pc_name, String pc_serial, String pc_model) {
        super(full_name, nonstaff_id, gender, phone_number, null, 0, pc_name, pc_serial, pc_model);
        this.nonstaff_id = nonstaff_id;
    }

    public void displayDetails() {
        System.out.println("----------------------");
        System.out.println("NonStaff's Name: " + full_name);
        System.out.println("NonStaff's ID: " + nonstaff_id);
        System.out.println("NonStaff's Gender: " + gender);
        System.out.println("NonStaff's Phone Number: " + phone_number);
        System.out.println("NonStaff's PC Name: " + pc_name);
        System.out.println("NonStaff's PC Serial: " + pc_serial);
        System.out.println("NonStaff's PC Model: " + pc_model);
        System.out.println("----------------------");
    }
}

class Admin {
    private String username;
    private String password;
    private int rollNumber; 

    public Admin(String username, String password, int rollnumber) {
        this.username = username;
        this.password = password;
        this.rollNumber = rollNumber;
    }

    public void displayDetails() {
        System.out.println("--------------------");
        System.out.println("Admin's RollNumber: " + rollNumber);
        System.out.println("Admin's Username: " + username);
        System.out.println("Admin's Password: " + password);
    }

    public String getUserName() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getRollNumber() { 
        return rollNumber; 
    }


    public void setUserName(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

class SuperAdmin {
    private String user_name;
    private String pass_word;

    public SuperAdmin(String user_name, String pass_word) {
        this.user_name = user_name;
        this.pass_word = pass_word;
    }

    public void displayDetails() {
        System.out.println("SuperAdmin's Username: " + user_name);
        System.out.println("SuperAdmin's Password: " + pass_word);
    }

    public String getUserName() {
        return user_name;
    }

    public String getPassword() {
        return pass_word;
    }

    public void setUserName(String user_name) {
        this.user_name = user_name;
    }

    public void setPassword(String pass_word) {
        this.pass_word = pass_word;
    }
}

public class min {
    static ArrayList<Student> students = new ArrayList<>();
    static ArrayList<Staff> staffs = new ArrayList<>();
    static ArrayList<Nonstaff> nonstaffs = new ArrayList<>();
    static ArrayList<Admin> admins = new ArrayList<>();
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        boolean run = true;
        while (run) {
            System.out.println("PC Registration System");
            System.out.println("-----------------------");
            System.out.println("What do you want to do?");
            System.out.println("1. A Super Admin");
            System.out.println("2. An Admin");
            System.out.println("3. Exit");

            System.out.println("Insert your choice:");
            int choice = input.nextInt();
            if (choice == 1) {
                superAdmin();
            } else if (choice == 2) {
                admin();
            } else if (choice == 3) {
                System.out.println("Exiting...... Have a good Day");
                break;
            } else {
                System.out.println("Invalid choice");
            }
        }
    }

    public static void superAdmin() {
        SuperAdmin superAdminInstance = new SuperAdmin("superadmin", "0000");
        System.out.println("Super Admin");
        System.out.println("-----------");
        System.out.println("Enter your username:");
        String username = input.next();
        System.out.println("Enter your password:");
        String password = input.next();
        if (username.equals(superAdminInstance.getUserName()) && password.equals(superAdminInstance.getPassword())) {
            System.out.println("Welcome " + superAdminInstance.getUserName());
            while (true) {
                System.out.println("What do you want to do?");
                System.out.println("-----------------------");
                System.out.println("1. Change username and password");
                System.out.println("2. Add Admin");
                System.out.println("3. Remove Admin");
                System.out.println("4. Search Admin");
                System.out.println("5. Display All Admins");
                System.out.println("6. Display All PCs in the System");
                System.out.println("7. Exit");
                System.out.println("-----------------------");

                System.out.println("Insert your choice:");
                int choice = input.nextInt();
                if (choice == 1) {
                    changeUsernameAndPassword(superAdminInstance);
                } else if (choice == 2) {
                    addAdmin();
                } else if (choice == 3) {
                    removeAdmin();
                } else if (choice == 4) {
                    searchAdmin();
                } else if (choice == 5) {
                    displayAdmin();
                } else if (choice == 6) {
                    displayAllPCs();
                } else if (choice == 7) {
                    System.out.println("Exiting...... Have a good Day");
                    break;
                } else {
                    System.out.println("Invalid choice");
                }
            }
        } else {
            System.out.println("Invalid username or password");
        }
    }

    public static void changeUsernameAndPassword(SuperAdmin superAdminInstance) {
        while (true) {
            System.out.println("Change username and password");
            System.out.println("--------------------");
            System.out.println("What do you want to do?");
            System.out.println("1. Change username");
            System.out.println("2. Change password");
            System.out.println("3. Exit");
            System.out.println("--------------------");
    
            System.out.println("Insert your choice:");
            int choice = input.nextInt();
            if (choice == 1) {
                changeUsername(superAdminInstance);
            } else if (choice == 2) {
                changePassword(superAdminInstance);
            } else if (choice == 3) {
                System.out.println("Exiting...... Have a good Day");
                break;
            } else {
                System.out.println("Invalid choice");
            }
        }
    }
    
    public static void changeUsername(SuperAdmin superAdminInstance) {
        System.out.println("Enter old username:");
        String oldUsername = input.next();
        if (!oldUsername.equals(superAdminInstance.getUserName())) {
            System.out.println("Invalid username");
            return;
        }
        System.out.println("Enter new username:");
        String newUsername = input.next();
        superAdminInstance.setUserName(newUsername);
        System.out.println("Username changed successfully");
        System.out.println("New username: " + superAdminInstance.getUserName());
    }
    
    public static void changePassword(SuperAdmin superAdminInstance) {
        System.out.println("Enter old password:");
        String oldPassword = input.next();
        if (!oldPassword.equals(superAdminInstance.getPassword())) {
            System.out.println("Invalid password");
            return;
        }
        System.out.println("Enter new password:");
        String newPassword = input.next();
        superAdminInstance.setPassword(newPassword);
        System.out.println("Password changed successfully");
        System.out.println("New password: " + superAdminInstance.getPassword());
        System.out.println("--------------------");
    }

    public static void addAdmin() {
        System.out.println("Add Admin to the System");
        System.out.println("--------------------");
        System.out.println("Enter Admin's Roll Number:");
        int rollNumber = input.nextInt();
        System.out.println("Enter Admin's username:");
        String username = input.next();
        System.out.println("Enter Admin's password:");
        String password = input.next();
        Admin newAdmin = new Admin(username, password, rollNumber);
        admins.add(newAdmin);
        System.out.println("Admin added successfully");
    }

    public static void removeAdmin() {
        System.out.println("Enter Admin's Roll Number to remove:");
        int rollNumber = input.nextInt();
        for (int i = 0; i < admins.size(); i++) {
            if (admins.get(i).getRollNumber() == rollNumber) {
                System.out.println("Do you want to remove the Admin: " + admins.get(i).getUserName());
                System.out.println("1. Yes");
                System.out.println("2. No");
                int choice = input.nextInt();
                if (choice == 1) {
                    admins.remove(i);
                    System.out.println("Admin removed successfully");
                    return;
                } else {
                    System.out.println("Admin is not removed");
                    return;
                }
            }
        }
        System.out.println("Admin not found");
    }
    public static void searchAdmin() {
        System.out.println("Enter Admin's Roll Number to search:");
        int rollNumber = input.nextInt();
        for (Admin admin : admins) {
            if (admin.getRollNumber() == rollNumber) {
                admin.displayDetails();
                return;
            }
        }
        System.out.println("Admin not found");
    }

    public static void displayAdmin() {
        for (int i = 0; i < admins.size(); i++) {
            Admin admin = admins.get(i); 
            admin.displayDetails();
            System.out.println("--------------------");
        }
    }

    public static void displayAllPCs() {
        System.out.println("Students' PCs:");
        for (int i = 0; i < students.size(); i++) {
            students.get(i).displayDetails();
        }

        System.out.println("Staff's PCs:");
        for (int i = 0; i < staffs.size(); i++) {
            staffs.get(i).displayDetails();
        }

        System.out.println("Nonstaff's PCs:");
        for (int i = 0; i < nonstaffs.size(); i++) {
            nonstaffs.get(i).displayDetails();
        }
    }

    public static void admin() {
        System.out.println("Admin");
        System.out.println("-----");
        System.out.println("Enter your username:");
        String username = input.next();
        System.out.println("Enter your password:");
        String password = input.next();
        
        for (Admin admin : admins) {
            if (admin.getUserName().equals(username) && admin.getPassword().equals(password)) {
                System.out.println("Welcome " + admin.getUserName());
                while (true) {
                    System.out.println("What do you want to do?");
                    System.out.println("-----------------------");
                    System.out.println("1. Change username and password");
                    System.out.println("2. Register PC for Student");
                    System.out.println("3. Register PC for Staff");
                    System.out.println("4. Register PC for Nonstaff");
                    System.out.println("5. Exit");
                    System.out.println("-----------------------");

                    System.out.println("Insert your choice:");
                    int choice = input.nextInt();
                    if (choice == 1) {
                        changeUsernameAndPassword(admin);
                    } else if (choice == 2) {
                        registerPCForStudent();
                    } else if (choice == 3) {
                        registerPCForStaff();
                    } else if (choice == 4) {
                        registerPCForNonstaff();
                    } else if (choice == 5) {
                        System.out.println("Exiting...... Have a good Day");
                        break;
                    } else {
                        System.out.println("Invalid choice");
                    }
                }
                return;
            }
        }
        System.out.println("Invalid username or password");
    }


    public static void changeUsernameAndPassword(Admin adminInstance) {
        while (true) { 
            System.out.println("Change username and password");
            System.out.println("--------------------");
            System.out.println("What do you want to do?");
            System.out.println("1. Change username");
            System.out.println("2. Change password");
            System.out.println("3. Exit");
            System.out.println("--------------------");
    
            System.out.println("Insert your choice:");
            int choice = input.nextInt();
            if (choice == 1) {
                changeUsername(adminInstance);
            } else if (choice == 2) {
                changePassword(adminInstance);
            } else if (choice == 3) {
                System.out.println("Exiting...... Have a good Day");
                break;
            } else {
                System.out.println("Invalid choice");
            }
        }
    }
    
    public static void changeUsername(Admin adminInstance) {
        System.out.println("Enter old username:");
        String oldUsername = input.next();
        if (!oldUsername.equals(adminInstance.getUserName())) {
            System.out.println("Invalid username");
            return;
        }
        System.out.println("Enter new username:");
        String newUsername = input.next();
        adminInstance.setUserName(newUsername);
        System.out.println("Username changed successfully");
        System.out.println("New username: " + adminInstance.getUserName());
        System.out.println("--------------------");
    }
    
    public static void changePassword(Admin adminInstance) {
        System.out.println("Enter old password:");
        String oldPassword = input.next();
        if (!oldPassword.equals(adminInstance.getPassword())) {
            System.out.println("Invalid password");
            return;
        }
        System.out.println("Enter new password:");
        String newPassword = input.next();
        adminInstance.setPassword(newPassword);
        System.out.println("Password changed successfully");
        System.out.println("New password: " + adminInstance.getPassword());
        System.out.println("--------------------");
    }

    public static void registerPCForStudent() {
        while (true) {
            System.out.println("Register for PC");
            System.out.println("--------------------");
            System.out.println("What do you want to do?");
            System.out.println("1. Add");
            System.out.println("2. Delete");
            System.out.println("3. Search");
            System.out.println("4. Display");
            System.out.println("5. Exit");
            System.out.println("--------------------");

            System.out.println("Insert your choice:");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    addPCForStudent();
                    break;
                case 2:
                    deletePCForStudent();
                    break;
                case 3:
                    searchPCForStudent();
                    break;
                case 4:
                    displayPCForStudent();
                    break;
                case 5:
                    System.out.println("Exiting...... Have a good Day");
                    return;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

    public static void addPCForStudent() {
        System.out.println("Enter student name:");
        String full_name = input.next();
        System.out.println("Enter student ID:");
        String student_id = input.next();
        System.out.println("Enter student gender:");
        String gender = input.next();
        System.out.println("Enter student phone number:");
        String phone_number = input.next();
        System.out.println("Enter student department:");
        String department = input.next();
        System.out.println("Enter student year:");
        int year = input.nextInt();
        System.out.println("Enter student's PC name:");
        String pc_name = input.next();
        System.out.println("Enter student's PC serial:");
        String pc_serial = input.next();
        System.out.println("Enter student's PC model:");
        String pc_model = input.next();

        Student student = new Student(full_name, student_id, gender, phone_number, department, year, pc_name, pc_serial, pc_model);
        students.add(student);
        System.out.println("Student registered successfully");
    }

    public static void deletePCForStudent() {
        System.out.println("Enter student ID to delete:");
        String student_id = input.next();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).student_id.equals(student_id)) {
                students.remove(i);
                System.out.println("Student removed successfully");
                return;
            }
        }
        System.out.println("Student not found");
    }

    public static void searchPCForStudent() {
        System.out.println("Enter student ID to search:");
        String student_id = input.next();
        for (Student student : students) {
            if (student.student_id.equals(student_id)) {
                student.displayDetails();
                return;
            }
        }
        System.out.println("Student not found");
    }

    public static void displayPCForStudent() {
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            student.displayDetails(); 
        }
    }
    
    public static void registerPCForStaff() {
        while (true) {
            System.out.println("Register for PC");
            System.out.println("--------------------");
            System.out.println("What do you want to do?");
            System.out.println("1. Add");
            System.out.println("2. Delete");
            System.out.println("3. Search");
            System.out.println("4. Display");
            System.out.println("5. Exit");
            System.out.println("--------------------");

            System.out.println("Insert your choice:");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    addPCForStaff();
                    break;
                case 2:
                    deletePCForStaff();
                    break;
                case 3:
                    searchPCForStaff();
                    break;
                case 4:
                    displayPCForStaff();
                    break;
                case 5:
                    System.out.println("Exiting...... Have a good Day");
                    return;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

    public static void addPCForStaff() {
        System.out.println("Enter staff name:");
        String full_name = input.next();
        System.out.println("Enter staff ID:");
        String staff_id = input.next();
        System.out.println("Enter staff gender:");
        String gender = input.next();
        System.out.println("Enter staff phone number:");
        String phone_number = input.next();
        System.out.println("Enter staff department:");
        String department = input.next();
        System.out.println("Enter staff year:");
        int year = input.nextInt();
        System.out.println("Enter staff's job:");
        String job = input.next();
        System.out.println("Enter staff's PC name:");
        String pc_name = input.next();
        System.out.println("Enter staff's PC serial:");
        String pc_serial = input.next();
        System.out.println("Enter staff's PC model:");
        String pc_model = input.next();

        Staff staff = new Staff(full_name, staff_id, gender, phone_number, department, job, year, pc_name, pc_serial, pc_model);
        staffs.add(staff);
        System.out.println("Student registered successfully");
    }

    public static void deletePCForStaff() {
        System.out.println("Enter Staff's ID to delete:");
        String staff_id = input.next();
        for (int i = 0; i < staffs.size(); i++) {
            if (staffs.get(i).staff_id.equals(staff_id)) {
                students.remove(i);
                System.out.println("Staff removed successfully");
                return;
            }
        }
        System.out.println("Student not found");
    }

    public static void searchPCForStaff() {
        System.out.println("Enter Staff's ID to search:");
        String staff_id = input.next();
        for (int i = 0; i < staffs.size(); i++) {
            if (staffs.get(i).staff_id.equals(staff_id)) {
                staffs.get(i).displayDetails(); 
                return;
            }
        }
        System.out.println("Staff not found");
    }

    public static void displayPCForStaff() {
        for (int i = 0; i < staffs.size(); i++) {
            Staff staff = staffs.get(i); 
            staff.displayDetails(); 
        }
    }

    public static void registerPCForNonstaff() {
        while (true) {
            System.out.println("Register for PC");
            System.out.println("--------------------");
            System.out.println("What do you want to do?");
            System.out.println("1. Add");
            System.out.println("2. Delete");
            System.out.println("3. Search");
            System.out.println("4. Display");
            System.out.println("5. Exit");
            System.out.println("--------------------");

            System.out.println("Insert your choice:");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    addPCFornonstaff();
                    break;
                case 2:
                    deletePCForNonstaff();
                    break;
                case 3:
                    searchPCForNonstaff();
                    break;
                case 4:
                    displayPCForNonstaff();
                    break;
                case 5:
                    System.out.println("Exiting...... Have a good Day");
                    return;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }
    public static void addPCFornonstaff() {
        System.out.println("Enter nonstaff name:");
        String full_name = input.next();
        System.out.println("Enter nonstaff ID:");
        String nonstaff_id = input.next();
        System.out.println("Enter nonstaff gender:");
        String gender = input.next();
        System.out.println("Enter nonstaff phone number:");
        String phone_number = input.next();
        System.out.println("Enter nonstaff's PC name:");
        String pc_name = input.next();
        System.out.println("Enter nonstaff's PC serial:");
        String pc_serial = input.next();
        System.out.println("Enter nonstaff's PC model:");
        String pc_model = input.next();

        Nonstaff nonstaff = new Nonstaff(full_name, nonstaff_id, gender, phone_number, pc_name, pc_serial, pc_model);
        nonstaffs.add(nonstaff);
        System.out.println("Student registered successfully");
    }

    public static void deletePCForNonstaff() {
        System.out.println("Enter student ID to delete:");
        String nonstaff_id = input.next();
        for (int i = 0; i < nonstaffs.size(); i++) {
            if (nonstaffs.get(i).nonstaff_id.equals(nonstaff_id)) {
                students.remove(i);
                System.out.println("Nonstaff removed successfully");
                return;
            }
        }
        System.out.println("Nonstaff not found");
    }

    public static void searchPCForNonstaff() {
        System.out.println("Enter Nonstaff ID to search:");
        String nonstaff_id = input.next();
        for (int i = 0; 1 < nonstaffs.size();i++) {
            if (nonstaffs.get(i).nonstaff_id.equals(nonstaff_id)) {
                nonstaffs.get(i).displayDetails();
                return;
            }
        }
        System.out.println("Nonstaff not found");
    }

    public static void displayPCForNonstaff() {
        for (int i = 0; i < nonstaffs.size(); i++) {
            Nonstaff nonstaff = nonstaffs.get(i);
            nonstaff.displayDetails(); 
        }
    }
}