
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

### **CRUD Operations (Detailed Flow)**
#### **1. Create**
- **AdminService, StudentService, StaffService**  
  - Call `FileManager.create(data, file_path)`  
  - `FileManager` writes data to respective file  

#### **2. Update**
- **AdminService, StudentService, StaffService**  
  - Call `FileManager.update(id, new_data, file_path)`  
  - `FileManager` searches file and updates the record  

#### **3. Delete**
- **AdminService, StudentService, StaffService**  
  - Call `FileManager.delete(id, file_path)`  
  - `FileManager` removes the record from the file  

#### **4. Display**
- **AdminService, StudentService, StaffService**  
  - Call `FileManager.read(file_path)`  
  - `FileManager` reads data from file and returns list  

#### **5. Search**
- **AdminService, StudentService, StaffService**  
  - Call `FileManager.search(criteria, file_path)`  
  - `FileManager` searches and returns matching records  

---

### **Final Concept**
- **Super Admin → Manages Admins (CRUD)**
- **Admin → Manages Students & Staff (CRUD)**
- **Service Files (AdminService, StudentService, StaffService)**
  - Define **Storage Path**  
  - Call **FileManager for Logic**  
- **FileManager**
  - Reads/Writes Data  
  - Performs Logic  
  - Interacts with Storage Files (`admin.txt`, `student.txt`, `staff.txt`)  

---
