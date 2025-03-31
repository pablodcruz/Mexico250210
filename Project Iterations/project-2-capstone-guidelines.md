# **Iteration 3 (Project 2) — Loan Management System: DevOps & Cloud Integration**

## **Goal**

Building on Iteration 2’s Spring Boot & React application, the goal of Iteration 3 is to **integrate DevOps practices** (using Docker, Jenkins, and AWS) to achieve **continuous integration and continuous deployment (CI/CD)** of your Loan Management System. By the end of this iteration, your application should be containerized and deployed on AWS, with an automated build-and-deploy pipeline set up.

---

## **Major Changes from Iteration 2**

1. **Cloud Hosting & Infrastructure**  
   - Deploy the Loan Management System on **AWS** (EC2 instances, RDS for the database, S3 optional for static files or logs, IAM for secure permissions).

2. **Containerization**  
   - **Dockerize** the Spring Boot back-end (and optionally the React front-end) to ensure consistent runtime environments.

3. **CI/CD Pipeline**  
   - Integrate **Jenkins** (or another CI/CD tool) to automate building, testing, and deploying your Docker containers.

4. **Monitoring & Logging (Optional but Recommended)**  
   - Consider configuring AWS CloudWatch or similar tools to monitor application health and logs.

---

## **DevOps & Cloud Integration**

Below is a high-level outline of how DevOps will be integrated into your Loan Management System:

1. **AWS Setup**  
   - **EC2**: Provision one or more EC2 instances to host your containerized application.  
   - **RDS**: Set up a PostgreSQL database instance with proper security groups and IAM roles to manage database access.  
   - **IAM**: Use IAM roles and policies to grant your EC2 instances appropriate permissions (e.g., access to pull images from ECR(Amazon Elastic Container Registry for docker images), read/write logs to CloudWatch).

2. **Docker**  
   - Create a **Dockerfile** in your back-end project directory.  
   - Configure the Dockerfile to:  
     - Use a suitable base image (e.g., `amazoncorretto:17` or `openjdk:17-jdk-alpine` for the Spring Boot application).  
     - Copy and build your Spring Boot JAR.  
     - Expose the necessary port(s).  
   - (Optional) For the React front end, create a separate Dockerfile if you want to containerize the front end. Alternatively, you can serve the React build from within the same container or host it separately.

3. **Jenkins CI/CD Pipeline**  
   - **Source Control**: Jenkins should monitor your Git repository (GitHub, GitLab, etc.).  
   - **Pipeline Stages**:  
     1. **Checkout**: Pull the latest code from the repository.  
     2. **Build**:  
        - Back-End: Run Maven or Gradle commands to compile and run tests (e.g., `mvn clean install`).  
        - Front-End: Run `npm install` and `npm run build` (if containerizing or building the front end).  
     3. **Docker Build & Push**:  
        - Build your Docker image(s).  
        - Tag and push the image to a container registry (e.g., **Docker Hub**, **AWS ECR**, or similar).  
     4. **Deploy**:  
        - On a successful build, deploy the container(s) to AWS EC2. This can be done via SSH commands, AWS ECS tasks, or other automation scripts.  
   - **Testing**:  
     - Integrate your **JUnit 5** unit tests (plus any integration tests) into the pipeline to ensure code quality before deployment.  
   - **Notifications (optional)**:  
     - Configure Jenkins to send notifications (Slack, email, etc.) on pipeline success or failure.

4. **Optional Enhancements**  
   - **Auto-Scaling**: Use AWS features (like AWS ECS Fargate or an EC2 Auto Scaling group) to automatically scale instances based on load.  
   - **Monitoring & Logging**: Set up AWS CloudWatch for log aggregation and real-time monitoring of CPU, memory, and application logs.

---

## **Updated Technology Stack**

- **Java 17**  
- **Spring Boot**  
- **Spring MVC**  
- **Spring Data JPA**  
- **PostgreSQL**  
- **React (TypeScript)**  
- **Docker**  
- **Jenkins**  
- **AWS** (EC2, RDS, IAM; optionally ECS/ECR)  
- **JUnit 5**  
- **Logback** (with SLF4J)  
- **Git**  
- **Lombok (optional)**

---

## **Reiterated & Extended Core Features**

All core features from Iteration 2 must remain operational. Below is the short list with DevOps notes:

1. **User Registration & Authentication**  
   - Continue to use role-based access (User vs. Manager).  
   - Ensure environment variable security in Docker and AWS (do **not** commit credentials to Git).

2. **User Profile Management**  
   - Keep the existing endpoints.  
   - Confirm that data persists correctly in AWS RDS.

3. **Loan Applications**  
   - Maintain functionality for creation, review, and approval of loans.  
   - Verify proper scaling if you choose to add load testing or auto-scaling in AWS.

4. **Spring MVC REST Endpoints**  
   - Keep endpoints from Iteration 2.  
   - Ensure Jenkins pipeline runs all tests (if applicable) to confirm endpoints function in the containerized environment.

5. **React Front End**  
   - Maintain or enhance existing React features.  
   - Optionally containerize the front end, or host it separately (e.g., S3 static hosting).  
   - Confirm front end can communicate with the back-end via the AWS endpoint or domain name.

---

## **Agile/Scrum Team Instructions**

Your Scrum framework continues with the same ceremonies, but now includes DevOps tasks:

1. **Sprint Planning**  
   - Incorporate tasks for **Dockerfile creation**, **Jenkins pipeline setup**, **AWS provisioning**, etc. into the sprint backlog.

2. **Daily Standups**  
   - Discuss progress on DevOps tasks (infrastructure, Docker scripts, pipeline issues).

3. **Sprint Review**  
   - Demonstrate a successful CI/CD run in Jenkins.  
   - Show the live AWS deployment of the Loan Management System.

4. **Sprint Retrospective**  
   - Reflect on the challenges of containerization, AWS, and Jenkins.  
   - Identify improvements for the pipeline or AWS cost optimizations.

5. **Backlog Refinement**  
   - Continue to refine user stories and incorporate **DevOps** stories (e.g., “As a DevOps engineer, I want to containerize the application so that deployments are consistent.”).

---

## **Deliverables**

1. **Updated Git Repository**  
   - **Source Code** (Back-end & Front-end).  
   - **Dockerfile**(s) & **Jenkinsfile** (or pipeline script).  
   - **Documentation** detailing how to set up CI/CD and run Docker containers locally.  
   - **Database Scripts** or **migration files** if needed.

2. **AWS Deployment**  
   - A public URL or IP where the Loan Management System can be accessed.  
   - Demonstrate the use of AWS services (EC2, RDS, IAM, etc.).

3. **Jenkins Pipeline**  
   - A functioning pipeline that automatically builds, tests, and deploys to AWS upon merges to the main branch (or a designated release branch).  
   - Logs of successful pipeline runs.

4. **Technical Documentation & Diagram**  
   - Include an **architecture diagram** showing how components interact (e.g., Docker images, Jenkins, AWS EC2, RDS).  
   - Instructions on how to replicate the pipeline setup (credentials, environment variables, Docker registry, etc.).

5. **Presentation**  
   - See presentation guidelines.

---

## **Suggested Assessment Criteria**

| **Criteria**                                | **Weight** |
|--------------------------------------------|-----------:|
| **Technical Implementation**               |       50%  |
| - Successful Docker containerization       |            |
| - AWS deployment with EC2, RDS integration |            |
| - Jenkins pipeline (build, test, deploy)   |            |
| **Application Functionality**              |       20%  |
| - All Iteration 2 features still work      |            |
| - Enhancements or bug fixes                |            |
| **Code Quality & Testing**                 |       20%  |
| - Clean, maintainable code                 |            |
| - Comprehensive unit/integration tests     |            |
| **Presentation & Documentation**           |       10%  |
| - Clear, concise explanation of CI/CD      |            |
| - Thorough setup instructions & diagrams   |            |
