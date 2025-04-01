# Project 2: Cloud Infrastructure (Step 1)

## Overview
**Implementing Cloud Infrastructure** by hosting our **React + Spring Boot** application on **AWS**. Specifically, we will:

1. Host the **Spring Boot backend** on AWS **EC2** and configure **RDS** for our database.
2. Use **IAM roles** to manage access and permissions securely.
3. Host the **React frontend** on AWS **S3** for a simpler setup instead of deploying it on EC2.

---

## Prerequisites
1. **AWS Account**: You need an active AWS account.  
2. **AWS CLI (Optional but Recommended)**: [Install AWS CLI](https://docs.aws.amazon.com/cli/latest/userguide/getting-started-install.html) for easier resource management via the command line.  
3. **Java and Maven** installed locally to build the Spring Boot application.  
4. **Node.js and npm** installed locally to build the React frontend.

---

## Step 1: Create an IAM Role for EC2
1. **Go to the IAM console**: [AWS IAM](https://console.aws.amazon.com/iamv2/home)  
2. **Create Role**:
   - Select **Role** from the sidebar and click **Create role**.
   - Select **AWS service** as the trusted entity.
   - Choose **EC2** for the use case.
   - Attach a policy that allows access to RDS (e.g., `AmazonRDSFullAccess`) or a custom policy with the specific RDS permissions you need.
   - Give your role a descriptive name, like `EC2-RDS-Access-Role`.
3. **Review** and **Create** the role.

> **Tip**: Keep permissions principle of least privilege in mind, meaning grant only the access your EC2 instance needs.

---

## Step 2: Launch an EC2 Instance
1. **Navigate to EC2**: [AWS EC2](https://console.aws.amazon.com/ec2)
2. **Create Instance**:
   - Choose **Amazon Linux 2**, **Ubuntu**, or another preferred AMI.
   - Select an **Instance Type** (e.g., `t2.micro` under the free tier).
3. **Configure Instance Details**:
   - **Network**: Can keep VPC and other options as default.  
   - **Security Group**: Create a new security group. You will need to add port `8080` (for Spring Boot) later under inbound rules.
   - **IAM role (under Advanced Details)**: Choose the `EC2-RDS-Access-Role` created earlier.
4. **Key Pair**: Create or select an existing key pair for SSH access.
5. **Review** and **Launch** the instance.

---

## Step 3: Provision RDS for Persistent Storage
1. **Navigate to RDS**: [AWS RDS](https://console.aws.amazon.com/rds/home)
2. **Create Database**:
   - Select **Databases** from sidebar.
   - Engine: Choose **MySQL** or **PostgreSQL** (community editions are fine).
   - **IMPORTANT - Template**: Make sure to select `Free tier`.
   - Configure DB instance settings: DB instance identifier, master username, and password.
   - Storage: Keep default or minimize cost.
   - **Security Group**: Either use the same VPC security group (recommended) or create a new one that allows traffic on the DB port (3306 for MySQL, 5432 for PostgreSQL).
3. **Create Database** and wait for it to become available.

---

## Step 4: Configure the Spring Boot App for RDS
1. **In your Spring Boot `application.properties`** (or `application.yml`), replace H2 properties with RDS configuration. Use environment variables and AWS Secret Manager (for production) or `.gitignore` this file for a simpler setup:

   ```properties
   # Example for MySQL
   spring.datasource.url=jdbc:mysql://<RDS-ENDPOINT>:3306/<DATABASE-NAME>
   spring.datasource.username=<YOUR-RDS-USERNAME>
   spring.datasource.password=<YOUR-RDS-PASSWORD>
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

   # OR for PostgreSQL
   # spring.datasource.url=jdbc:postgresql://<RDS-ENDPOINT>:5432/<DATABASE-NAME>
   # spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
   ```
2. **Build your application** (Maven or Gradle). For Maven:
   ```bash
   mvn clean package
   ```
3. **Test Locally**: Verify that you can connect to RDS by running the Spring Boot app and checking logs for a successful connection.

---

## Step 5: Deploy the Backend to EC2
1. **SSH into the EC2 instance**: 
   ```bash
   ssh -i "MyKeyPair.pem" ec2-user@<EC2-Public-IP>
   ```
2. **Install Java** if it’s not already:
   ```bash
   sudo yum update -y
   sudo yum install java-17 -y
   ```
3. **Transfer the Spring Boot JAR** or clone your repo:
   ```bash
   scp -i "MyKeyPair.pem" target/social-media-app.jar ec2-user@<EC2-Public-IP>:/home/ec2-user
   scp -i "spring-todo-kp.pem" C:/Users/PabloDeLaCruz/Documents/Notes/ISM/Mexico250210/demos/SpringTodoApp/target/SpringTodoApp-0.0.1-SNAPSHOT.jar ec2-user@<EC2-Public-IP>:/home/ec2-user
   # OR clone your GitHub repo directly:
   git clone https://github.com/<username>/<repo>.git
   ```
4. **Run the Spring Boot app** from the EC2 console:
   ```bash
   java -jar social-media-app.jar
   ```
5. **Verify** it’s running on port `8080` by visiting:
   ```
   http://<EC2-Public-IP>:8080
   ```

---

## Step 6A: (Option A) Host React on the Same EC2 Instance
1. **Build the React app** locally:
   ```bash
   cd frontend
   npm install
   npm run build
   ```
2. **Transfer the build folder** to your EC2 instance:
   ```bash
   scp -i "MyKeyPair.pem" -r build ec2-user@<EC2-Public-IP>:/home/ec2-user
   ```
3. **Serve the React app** using a static server or configure Nginx/Apache:
   - **Static Server**:
     ```bash
     npm install -g serve
     serve -s build -p 3000
     ```

---

## Step 6B: (Option B) Host React on AWS S3
For a simpler and cost-effective setup, you can host your **React** frontend as a static website on **S3**:

1. **Build the React App**:
   ```bash
   cd frontend
   npm install
   npm run build
   ```
   This creates a production-ready `build` folder.

2. **Create an S3 Bucket**:
   - Go to the S3 console: [AWS S3](https://console.aws.amazon.com/s3)
   - Click **Create bucket**.
   - Provide a unique bucket name (e.g., `my-react-app-bucket`).
   - Choose a region that matches or is near your EC2 region (optional for latency).
   - Leave default settings or adjust to your needs.

3. **Enable Static Website Hosting**:
   - After the bucket is created, go to **Properties**.
   - Under **Static website hosting**, choose **Enable**.
   - Specify `index.html` as the index document (and optionally `error.html` for error document).

4. **Upload the Build Folder**:
   - Either upload manually via the S3 console (by dragging the `build` contents into the bucket, not the build folder itself, make sure you add the static folder).
   - Or use the AWS CLI:
   - SSH into the EC2 instance: 
     ```bash
     ssh -i "MyKeyPair.pem" ec2-user@<EC2-Public-IP>
     ```
     ```bash
     aws s3 sync build/ s3://my-react-app-bucket --acl public-read
     ```
   - Make sure your files are **publicly accessible** if you want anyone to visit without signing in (can configure bucket policies or object permissions).

5. **Access Your React App**:
   - Find the **Endpoint** under **Static website hosting** (e.g., `http://my-react-app-bucket.s3-website-us-east-1.amazonaws.com`).
   - Visit that URL and confirm your React app is online.   
---

## Step 7: Confirm Access & Security
1. **Test from your local machine**:
   - **Frontend (S3)**: `http://my-react-app-bucket.s3-website-<region>.amazonaws.com`
   - **Backend (EC2)**: `http://<EC2-Public-IP>:8080`
2. **Security Groups**:
   - Ensure inbound rules allow traffic on port 8080 for the backend.
3. **IAM Role Verification**:
   - Confirm that no access keys or secrets are stored on the instance—EC2 uses the IAM role to manage RDS access securely.

---

## Next Steps
- **Containerize**: Dockerize your backend (and optionally the frontend).
- **CI/CD**: Implement Jenkins or another tool to automate builds and deployments.
- **Scalability & Monitoring**: Add Auto Scaling and AWS CloudWatch metrics.

---