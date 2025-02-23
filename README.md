

# PC Registration System (Java-Based)

## Project Overview:
This is a Java-based **PC Registration System** that allows super admins to manage other admins and enables admins to manage students and staff registrations. The system uses file-based storage for all data (admin, student, and staff records) and performs CRUD operations. The GUI is integrated using Java Swing and it is designed in an object-oriented manner for maintainability and scalability.

## Features:
- **Super Admin** can:
  - Manage admins (create, update, delete, display, and search).
  - Use secure login credentials to access the system.
  
- **Admin** can:
  - Manage students and staff (create, update, delete, display, and search).
  - Securely log in and perform CRUD operations on students and staff records.

- **Student & Staff**:
  - CRUD operations (Create, Read, Update, Delete) are performed by admins.
  - Data is stored in separate text files for students and staff.

- **Data Storage**:
  - All user and PC-related data is stored in **text files** (`admin.txt`, `student.txt`, `staff.txt`).
  
- **Service Files** handle all business logic and CRUD operations.

## Project Structure:

### **1. Entities and Components:**

- **Super Admin:**
  - Manages Admins (CRUD).
  - Logs in using default credentials stored in `superadmin.txt`.
  
- **Admin:**
  - Manages Students and Staff (CRUD).
  - Logs in securely, creates users, and stores data in text files.

- **Student & Staff:**
  - Managed by Admins for CRUD operations.
  - Stored in text files via the `FileManager` class.

- **Service Files (`AdminService`, `StudentService`, `StaffService`):**
  - Handle the logic of CRUD operations and define the storage paths (admin.txt, student.txt, staff.txt).
  - Perform file read/write operations via `FileManager`.

- **File Manager:**
  - Reads from and writes data to text files.

### **2. File Storage:**

- **admin.txt** → Stores Admin Data.
- **student.txt** → Stores Student Data.
- **staff.txt** → Stores Staff Data.

---

## **How to Get Started:**

### **1. Clone the Repository:**

```bash
git clone https://github.com/Fitsumhelina/pc-registration-system.git
cd src
```

### **2. Set Up Files:**

In the `src/data` folder, create a file called `superadmin.txt`. This file should contain the default login credentials for the Super Admin:

```plaintext
yourname,yourpassword
```

This will allow the Super Admin to log in initially and start managing other admins.

### **3. Import the Project:**
- Open the project in an IDE like **IntelliJ IDEA** or **Eclipse**.
- if you're using Vs code y,,ou have to install java kit from [here](), then just install it in your computer then you can open up your project in Vscode 
- Ensure the Java Development Kit (JDK) is installed.

### **4. Run the Application:**

To run the application:
- all of ther `.java` files also have to be compiled to `.class` in order to do that you can use `javac + file name ` and it's better to compile `models`->`utils`-> `services` -> `gui`->`app.java` files respectively 
- Run the `App.java` file as a Java Application using `javac App.java`.
- The login screen will appear, where you can log in as the Super Admin using the credentials in `superadmin.txt`.

### **5. System Usage:**

#### **Super Admin:**
1. The Super Admin logs in using the credentials (`yourUsername,yourPassword`).
2. Once logged in, they can:
   - Create, update, delete, search, and display Admins.
   - Admins are stored in `admin.txt` automatically.

#### **Admin:**
1. Once logged in as an Admin, they can:
   - Manage students and staff (create, update, delete, search, display).
   - Student and staff data is stored in `student.txt` and `staff.txt`, respectively.

#### **Student/Staff Data:**
- Admins can perform CRUD operations to manage student and staff details.
- All data is stored in text files (`student.txt`, `staff.txt`) implicitly.
- Data can be displayed in a table format within the GUI.

### **6. Data Storage:**

- The `FileManager` class handles file reading and writing.
- Admin, student, and staff data are stored in simple text files (`admin.txt`, `student.txt`, `staff.txt`), making the system lightweight and easy to maintain.

### **7. Security:**

- The login system is secured using basic username/password authentication.
- Admins are authenticated before accessing any CRUD functionality.
  
---

## **Directory Structure:**

```plaintext
src/
│
├── gui/
│   ├── LoginFrame.java
│   ├── SuperAdminFrame.java
│   ├── AdminFrame.java
│   ├── RegisterUserFrame.java
│   └── DeleteUserFrame.java
│   └── SearchUserFrame.java
│   └── UpdateUserFrame.java
│   └── ViewAllUsersFrame.java
│  
│
├── model/
│   ├── Admin.java
│   ├── Student.java
│   └── Staff.java
│
├── service/
│   ├── AdminService.java
│   ├── StudentService.java
│   └── StaffService.java
│
├── utils/
│   └── FileManager.java
│
└── data/
    ├── admin.txt
    ├── student.txt
    └── staff.txt
    └── superadmin.txt
```

---

## **Services & Operations:**

### **CRUD Operations:**

#### **Create:**
- **AdminService.createAdmin(data)** → Creates an admin and saves data in `admin.txt`.
- **StudentService.createStudent(data)** → Creates a student and saves data in `student.txt`.
- **StaffService.createStaff(data)** → Creates staff and saves data in `staff.txt`.

#### **Update:**
- **AdminService.updateAdmin(id, new_data)** → Updates an admin record in `admin.txt`.
- **StudentService.updateStudent(id, new_data)** → Updates a student record in `student.txt`.
- **StaffService.updateStaff(id, new_data)** → Updates a staff record in `staff.txt`.

#### **Delete:**
- **AdminService.deleteAdmin(id)** → Deletes an admin record from `admin.txt`.
- **StudentService.deleteStudent(id)** → Deletes a student record from `student.txt`.
- **StaffService.deleteStaff(id)** → Deletes a staff record from `staff.txt`.

#### **Display/Search:**
- **AdminService.getAdmins()** → Displays all admins.
- **StudentService.getStudents()** → Displays all students.
- **StaffService.getStaff()** → Displays all staff.

### **File Management:**
- **FileManager.read(file)** → Reads data from a specified file.
- **FileManager.write(data, file)** → Writes data to a specified file.

---
