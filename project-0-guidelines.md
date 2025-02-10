## **Iteration 1 (Project 0)**
**Goal**: Create a minimal back-end application using Java, JDBC, and Javalin. There is **no dedicated front-end**—all endpoints are tested via Postman or similar tools.

### **Key Features**
1. **User Management**  
   - Create new user accounts.  
   - Basic CRUD operations on user profiles (e.g., update profile information).
2. **Loan Applications**  
   - Users can create a new loan application.  
   - Users can view or edit their existing loan applications.  
3. **Data Persistence**  
   - Use **JDBC** to interact with a relational database (PostgreSQL).  
4. **API Endpoints with Javalin**  
   - Set up RESTful routes (e.g., `/users`, `/loans`) using **Javalin**.  
   - Test endpoints using **Postman** (or similar).  

### **Technologies**
- **Java 17**  
- **Javalin** (for lightweight REST API)  
- **JDBC** (for database connectivity)  
- **Postman** (for testing)  
- **Relational Database** (PostgreSQL)

### **Project Structure**
- **Controller Layer**: Javalin handlers mapping HTTP routes to service calls.  
- **Service Layer**: Core business logic, validations.  
- **Data Access Layer**: JDBC classes handling direct CRUD operations.  

### **Deliverables**
- A Git repository containing:  
  - Javalin-based Java code.  
  - SQL scripts (if needed) or embedded DB setup.  
  - Instructions on how to run the application and test with Postman.
