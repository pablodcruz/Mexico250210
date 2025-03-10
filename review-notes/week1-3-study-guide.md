## **Java Fundamentals**

- **Object Creation & References**  
  - How to determine the number of objects instantiated versus reference variables assigned.  
  - Common pitfalls when multiple `new` statements appear in a code block.

- **OOP Concepts (Focus on Overloading & Non‐Access Modifiers)**  
  - Distinguishing method **overloading** rules (method signatures, parameter types/ordering).  
  - Understanding `private` and other access modifiers.
  - Understanding `final` and other **non** access modifiers (`static`, etc.).

- **Exception Handling Flow**  
  - The roles of `try`, `catch`, and `finally`, plus how the flow proceeds if exceptions occur at different points.  
  - Knowing when **checked** exceptions must be caught or declared, and how that differs from runtime exceptions.

- **Collections & Data Structures**  
  - Best practices for choosing a collection to avoid duplicates or ensure a particular ordering.  
  - Basic differences between `List`, `Set`, `Map`, etc., and their typical use cases.

- **JUnit & Testing Fundamentals**  
  - Writing basic unit tests, including how to structure tests.  
  - Understanding how to test code behavior (e.g., verifying the result of a function, or that certain exceptions are thrown).

- **Scope & Static Variables**  
  - Differences between local, instance, and static(global) scope.  
  - Reasons to use `static` (shared resources) vs. reasons to avoid it (tight coupling).

- **Compiling & Running Java**  
  - Typical command‐line steps: using `javac` to compile, `java` to run.  

---

## **SQL**

- **SQL Command Categories**  
  - Understanding **DML** (Data Manipulation Language) vs. **DDL** (Data Definition Language), and examples of each.  
  - Where `UPDATE`, `INSERT`, `SELECT`, and `CREATE TABLE` fit in.

- **PRIMARY KEY & Schema**  
  - Implications of marking a column as a primary key (e.g., uniqueness, indexing).  
  - What typically constitutes a “schema”. Your ERD represents a schema.

- **JDBC Essentials**  
  - Differences between a **simple** `Statement` and a `PreparedStatement`, and when you’d choose one over the other.  
  - Purpose of a `ResultSet` and how you typically interact with query results in JDBC.

- **Filtering & JOINs**  
  - Writing `SELECT` queries with conditions for numeric/string comparisons.  
  - Understanding `INNER JOIN` vs. `OUTER JOIN` (and variants like LEFT, RIGHT) in retrieving matching or nonmatching rows.

- **DAO Pattern**  
  - Common structure for Data Access Objects, separating SQL logic from business logic.  
  - Typical responsibilities (CRUD methods) and relationships to the underlying database.
  - Why it relies on `Interfaces` even if it might not be needed for smaller projects like ours?

---

## **REST/HTTP**

- **HTTP Verbs**  
  - The roles of GET, POST, PUT, DELETE, PATCH in **RESTful** design and how they typically map to CRUD.  
  - Common mistakes when designing endpoints (e.g., misusing POST vs. PUT).

- **HTTP Status Codes & Responses**  
  - How to interpret 2xx, 3xx, 4xx, and 5xx codes (client errors vs. server errors).  
  - General guidelines for returning meaningful status codes.

- **RESTful API Design**  
  - Deciding on resource naming, path structure, query parameters vs. path parameters.  
  - Handling request/response lifecycles (body, headers, etc.).

---

## **Javalin**

- **Route Handlers**  
  - Defining routes for GET, POST, etc., and how you handle requests (e.g., reading JSON bodies).  
  - Returning responses. Why do we use `.json()` vs `.result()`

- **HTTP Status & ctx Methods**  
  - Using `ctx.status(...)` and `ctx.result(...)` to control response codes and body.  
  - How to send a **201 Created** response with JSON in a practical sense.

- **Initialization & Configuration**  
  - Creating a Javalin instance, starting the server, configuring the port.

- **Query Parameters & Default Values**  
  - Handling optional parameters in Javalin, including providing fallback defaults if they’re not present. Read about defaults in the documentation.

- **Error/Exception Handling**  
  - How `ExceptionHandler` works to catch and handle exceptions at a global level. Read about this in the documentation.  

---

### **How to Use This Guide**

1. **Concept Mastery**: Rather than memorizing questions, **understand** each topic. Be able to explain it in your own words and apply it in sample code.  
2. **Practice**: Write short snippets that demonstrate the points (e.g., a small JUnit test, a simple SQL query, a Javalin endpoint).  
3. **Cross‐Reference**: If you see references to these concepts in notes, docs, or lectures, focus on how they connect in a larger application context.  
4. **Stay Curious**: If a topic feels vague (like prepared statements vs. statements), dive deeper with official docs or quick coding examples. **Ask questions**.
