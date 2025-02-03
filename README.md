
---

### **Entities & Components:**
1. **Super Admin**  
   - Manages Admins (Create, Update, Delete, Display, Search)  
   - Uses `AdminService` to perform actions  

2. **Admin**  
   - Manages Students and Staff (Create, Update, Delete, Display, Search)  
   - Uses `StudentService` & `StaffService`  

3. **Student & Staff**  
   - Basic operations (CRUD) managed by Admin  
   - Data stored in text files via `FileManager`  

4. **Service Files (`AdminService`, `StudentService`, `StaffService`)**  
   - Performs logic (CRUD operations) 
   - Defines **where to save** data  
   - Calls `FileManager` to handle logic  

5. **File Manager**  
   - Reads/Writes data from/to files  
 

6. **Storage Files**  
   - `admin.txt` → Stores Admin Data  
   - `student.txt` → Stores Student Data  
   - `staff.txt` → Stores Staff Data  

---

### **Use Case Relationship Diagram:**
#### **1. Super Admin (Manages Admins)**
- `Super Admin` → **CRUD Operations** on Admin  
- Uses **AdminService** to interact with **FileManager**  
- `AdminService` handles logic and saves data in `admin.txt` using file manager 

#### **2. Admin (Manages Students & Staff)**
- `Admin` → **CRUD Operations** on Students & Staff  
- Uses **StudentService** & **StaffService**  
- Each service file defines **storage path** (`student.txt`, `staff.txt`)  
- `FileManager` processes data  

#### **3. CRUD Operations & Flow**
- **Create, Update, Delete, Display, Search**  
- `AdminService`, `StudentService`, `StaffService` call `FileManager`  
- `FileManager` open a gate to manipulate `admin.txt`, `student.txt`, `staff.txt`  

---

### **Full Structure with Paths:**
#### **Super Admin Manages Admins**
```plaintext
Super Admin → AdminService → FileManager → admin.txt
```

#### **Admin Manages Students**
```plaintext
Admin → StudentService → FileManager → student.txt
```

#### **Admin Manages Staff**
```plaintext
Admin → StaffService → FileManager → staff.txt
```

---


##  **-> CRUD Operation Flow**
### **1. Create (Handled in Service)**
- `AdminService.createAdmin(data)`
  - Calls `FileManager.write(data, "admin.txt")`
- `StudentService.createStudent(data)`
  - Calls `FileManager.write(data, "student.txt")`
- `StaffService.createStaff(data)`
  - Calls `FileManager.write(data, "staff.txt")`

### **2. Update (Handled in Service)**
- `AdminService.updateAdmin(id, new_data)`
  - Calls `FileManager.read("admin.txt")`
  - Updates record in memory
  - Calls `FileManager.write(updated_data, "admin.txt")`
- `StudentService.updateStudent(id, new_data)`
  - Calls `FileManager.read("student.txt")`
  - Updates record
  - Calls `FileManager.write(updated_data, "student.txt")`
- `StaffService.updateStaff(id, new_data)`
  - Calls `FileManager.read("staff.txt")`
  - Updates record
  - Calls `FileManager.write(updated_data, "staff.txt")`

### **3. Delete (Handled in Service)**
- `AdminService.deleteAdmin(id)`
  - Calls `FileManager.read("admin.txt")`
  - Removes record from memory
  - Calls `FileManager.write(updated_data, "admin.txt")`
- `StudentService.deleteStudent(id)`
  - Calls `FileManager.read("student.txt")`
  - Removes record
  - Calls `FileManager.write(updated_data, "student.txt")`
- `StaffService.deleteStaff(id)`
  - Calls `FileManager.read("staff.txt")`
  - Removes record
  - Calls `FileManager.write(updated_data, "staff.txt")`

### **4. Display (Handled in Service)**
- `AdminService.getAdmins()`
  - Calls `FileManager.read("admin.txt")`
  - Returns all records
- `StudentService.getStudents()`
  - Calls `FileManager.read("student.txt")`
  - Returns all records
- `StaffService.getStaff()`
  - Calls `FileManager.read("staff.txt")`
  - Returns all records

### **5. Search (Handled in Service)**
- `AdminService.searchAdmin(criteria)`
  - Calls `FileManager.read("admin.txt")`
  - Filters data in memory
  - Returns matching records
- `StudentService.searchStudent(criteria)`
  - Calls `FileManager.read("student.txt")`
  - Filters data
  - Returns matching records
- `StaffService.searchStaff(criteria)`
  - Calls `FileManager.read("staff.txt")`
  - Filters data
  - Returns matching records

---

## **Final Structure**
1. **Super Admin manages Admins**
   ```plaintext
   Super Admin → AdminService (CRUD) → FileManager → admin.txt
   ```
2. **Admin manages Students & Staff**
   ```plaintext
   Admin → StudentService (CRUD) → FileManager → student.txt
   Admin → StaffService (CRUD) → FileManager → staff.txt
   ```
3. **Service Files (AdminService, StudentService, StaffService)**
   - Handle **all CRUD logic**
   - Call `FileManager.read()` or `FileManager.write()`
4. **FileManager**
   - Only reads/writes files
   - Acts as the bridge between Services and Storage

---
