# **Iteration 2 (Project 1) — Loan Management System: Spring Boot & React Application**

## **Goal**

As a team of 5, Refactor the existing Loan Management System into a modern, full-stack Spring Boot application with a React front end. In this iteration, you will convert the current back-end (built with Java 17, JDBC, and Javalin) to use Spring Boot, Spring MVC, and Spring Data JPA with annotation-based configuration. Additionally, you will develop a React front end (with TypeScript) that interacts with your new RESTful APIs.

---

## **Major Changes from Iteration 1**

- **Framework & Architecture:**
  - **Back-End:**  
    - **From:** Javalin with JDBC and a custom DAO layer.  
    - **To:** Spring Boot using Spring MVC for RESTful endpoints and Spring Data JPA for data persistence.
  - **Front-End:**  
    - **From:** Testing endpoints via Postman.  
    - **To:** Building a full React-based front end to interact with the REST APIs.
  - **Configuration:**  
    - Leverage annotation-based configuration exclusively (e.g., `@SpringBootApplication`, `@RestController`, `@Entity`, `@Repository`).

- **Functionality Enhancements:**
  - Maintain all previous core features (user registration, authentication, user profile management, and loan application processing with role-based access).
  - Error handling, and logging using Logback (with SLF4J).
  - Ensure data integrity with robust Spring Data JPA repository methods.

- **Testing & Logging:**
  - **Testing:**  
    - Continue to implement unit tests (using JUnit 5) for your service layer. Implement at least 10 unit tests.
    - (Optional) Consider adding integration tests for your Spring MVC endpoints.
  - **Logging:**  
    - Configure Logback to log significant events and errors in the application.

---

## **Technology Stack**

The following technologies will be used in Iteration 2:

- **Java 17**
- **Spring Boot**
- **Spring MVC**
- **Spring Data JPA**
- **PostgreSQL**
- **React** (with TypeScript)
- **HTML/CSS/JavaScript**
- **JUnit 5**
- **Logback** (with SLF4J)
- **Git**
- **Lombok (Optional)**

---

## **Updated Core Features**

1. **User Registration & Authentication**
   - **Endpoints:**  
     - **POST** `/auth/register`: Register new users.
     - **POST** `/auth/login`: Authenticate users and establish a session or issue a token.
     - **POST** `/auth/logout`: End the current session.
   - **Role Management:**  
     - Store user roles (Regular User vs. Manager) in the database.
     - Use role-based access control on endpoints (e.g., only managers can approve or reject loans).

2. **User Profile Management**
   - Enable users to view and update their own profiles.
   - Optionally allow users to delete their account (maintain referential integrity).

3. **Loan Applications**
   - **Regular Users:**  
     - Create new loan applications.
     - View and edit their own loan applications.
   - **Managers:**  
     - View all loan applications.
     - Approve or reject any loan application.
   - **Data Fields:**  
     - Include necessary fields like amount requested, loan type, and status (*pending*, *approved*, *rejected*).

4. **API Endpoints with Spring MVC**
   - Replace Javalin endpoints with Spring MVC’s `@RestController` annotated methods.
   - Use annotation-based request mappings (`@PostMapping`, `@GetMapping`, `@PutMapping`) to define your API.
   - Secure endpoints using session management or token-based authentication (and optionally Spring Security).

5. **Data Persistence with Spring Data JPA**
   - Define JPA entity classes for Users and Loans.
   - Replace JDBC-based DAOs with Spring Data JPA repositories.
   - Use annotation-based configuration for repository interfaces (e.g., `@Repository`).
   - **Ensure your database schema is designed in 3rd Normal Form (3NF).** (An example schema will be provided separately.)

6. **React Front End**
   - Build a single-page application using React.
   - Implement UI screens for user registration, login, viewing/updating user profiles, and loan management.
   - Interact with the back-end RESTful API to fetch and update data.

---

## **Agile/Scrum Team Instructions**

Since this is a group project, effective collaboration is key. Follow these Agile/Scrum practices to ensure a smooth development process:

### **Team Roles & Responsibilities**

- **Product Owner:**  
  - Defines project requirements and prioritizes the backlog.
  - Communicates with stakeholders to ensure the product meets expectations.

- **Scrum Master:**  
  - Facilitates daily standups, sprint planning, reviews, and retrospectives.
  - Removes impediments and ensures the team follows Agile best practices.

- **Development Team:**  
  - Collaboratively implement features across both back-end and front-end.
  - Write unit and integration tests, and maintain code quality.
  - Commit frequently with meaningful messages and use feature branches for development.

### **Agile Ceremonies & Processes**

- **Sprint Planning:**  
  - Break the project into manageable user stories.
  - Define tasks, assign responsibilities, and set clear sprint goals.

- **Daily Standups:**  
  - Hold brief daily meetings (about 15 minutes) to discuss progress, blockers, and next steps.

- **Sprint Review & Demo:**  
  - At the end of each sprint, demonstrate completed features to gather feedback.
  
- **Sprint Retrospective:**  
  - Reflect on what went well and what can be improved. Document actionable items for the next sprint.

- **Backlog Refinement:**  
  - Regularly review and update the project backlog to prioritize the most critical features.

### **Collaboration & Tools**

- **Git:**  
  - Use a branching strategy (e.g., feature branches) and pull requests for code reviews.
- **Project Management Tools:**  
  - Utilize tools like Jira, Trello, or similar for tracking tasks and sprints.
- **Communication:**  
  - Use team communication tools (e.g., Slack, Microsoft Teams) for efficient collaboration.
- **Documentation:**  
  - Maintain clear documentation for code, API endpoints, configuration, and testing procedures in your repository’s README or wiki.

---

## **Deliverables**

1. **Git Repository:**  
   - **Source Code:**  
     - Spring Boot application (back-end) with controllers, services, repositories, and unit/integration tests.
     - React application (front-end) with components, services, and routing.
   - **Database Scripts:**  
     - SQL scripts or migration files (e.g., Flyway, Liquibase) to create necessary tables.
   - **Documentation:**  
     - A comprehensive README describing how to run the application, configure the database, and execute tests.
   - **Agile Artifacts:**  
     - A project backlog with user stories and documentation of sprint planning, daily standups, sprint reviews, and retrospectives.

2. **API & Front-End Demonstration:**  
   - A Postman collection is no longer required. Instead, ensure that the React front end successfully communicates with the back-end RESTful APIs.
   - Prepare a short demo or presentation showing both back-end functionality and the front-end user experience.