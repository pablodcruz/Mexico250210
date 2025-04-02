## **1. Prepare Your Spring Boot Application**

1. **Build the JAR File**  
   - From your project’s root directory, run:
     ```bash
     mvn clean package
     ```
   - This command generates an executable JAR (e.g., `social-media-app.jar`) in the `target` folder.

2. **Test Locally**  
   - Verify the JAR works by running:
     ```bash
     java -jar target/social-media-app.jar
     ```
   - Check it’s accessible at `http://localhost:8080` (or any configured port).

---

## **2. Create a Dockerfile**

Place this **`Dockerfile`** at the root of your backend project (where the `pom.xml` is located):

```dockerfile
# 1. Use a lightweight Java base image
FROM eclipse-temurin:17-jdk-alpine

# 2. Create and use an application directory
WORKDIR /app

# 3. Copy the JAR file from the build context
COPY target/social-media-app.jar app.jar

# 4. Expose the Spring Boot port
EXPOSE 8080

# 5. Run the application
CMD ["java", "-jar", "app.jar"]
```

**Notes**:
- Update the base image if you’re using Java 8 or 11 (e.g., `eclipse-temurin:11-jdk-alpine`).
- Ensure the `COPY` line matches the exact name of your JAR file.

---

## **3. Build and Test the Docker Image Locally**

1. **Build the Image**:
   ```bash
   docker build -t social-media-backend:v1 .
   ```
   - The `-t` flag assigns a name and version tag (`v1`).
   - `.` uses the current directory. Use `-f` to specify a different location.

2. **Test the Container**:
   ```bash
   docker run -d -p 8080:8080 --name social-backend social-media-backend:v1
   ```
   - `-d` runs it in detached mode.
   - `-p 8080:8080` maps the container’s port 8080 to your local port 8080.
   - Visit `http://localhost:8080` to confirm the app is running inside Docker.

---

## **4. Push to a Container Registry (Optional)**

To **deploy on EC2** in a more automated way, you can store your Docker image in:
- **Docker Hub**  
- **AWS ECR (Elastic Container Registry)**  

**Example: Docker Hub**  
1. **Tag the image**:
   ```bash
   docker tag social-media-backend:v1 <dockerhub-username>/social-media-backend:v1
   ```
2. **Push**:
   ```bash
   docker push <dockerhub-username>/social-media-backend:v1
   ```

*(If you choose AWS ECR (optional), follow [AWS ECR documentation](https://docs.aws.amazon.com/AmazonECR/latest/userguide/getting-started-cli.html).)*

---

## **5. Configure Your EC2 Environment**

### **5.1 Launch an EC2 Instance**

1. **Go to AWS EC2**:
   - Create an instance (e.g., Amazon Linux 2) in a **public subnet**.
   - Security Group: Open **port 22** for SSH and **port 8080** for your Spring Boot app.
   - IAM Role (optional if you’re using ECR): If you push to ECR, assign an IAM role that allows pulling images from ECR.

2. **Connect via SSH**:
   ```bash
   ssh -i "MyKeyPair.pem" ec2-user@<EC2-Public-IP>
   ```

### **5.2 Install Docker on the EC2 Instance**

For **Amazon Linux 2**:
```bash
sudo yum update -y
sudo yum install docker -y
sudo service docker start
sudo usermod -aG docker ec2-user
# Log out (exit command) and back in (or run "newgrp docker") for group permissions to take effect
```

---

## **6. Run the Container on EC2**

### **Option A: Pull from Registry**
If your image is in Docker Hub or ECR:

1. **Login** (If needed for private repos):
   ```bash
   docker login
   ```
2. **Pull the image**:
   ```bash
   docker pull <dockerhub-username>/social-media-backend:v1
   ```
3. **Run the container**:
   ```bash
   docker run -d -p 8080:8080 --name social-backend <dockerhub-username>/social-media-backend:v1
   ```

### **Option B: Copy JAR and Build Image on EC2**
If you prefer not to use a registry:

1. **Copy files** to EC2:
   ```bash
   scp -i "MyKeyPair.pem" -r . ec2-user@<EC2-Public-IP>:/home/ec2-user/social-media
   ```
   *(Make sure your Dockerfile and JAR are included.)*

2. **SSH into EC2** and build:
   ```bash
   cd social-media
   docker build -t social-media-backend:v1 .
   docker run -d -p 8080:8080 --name social-backend social-media-backend:v1
   ```

---

## **7. Verify Everything Works**

1. **Open a Browser**:
   - Enter `http://<EC2-Public-IP>:8080`.
   - You should see your Spring Boot application response (e.g., “Whitelabel Error Page” or your custom home).

2. **Check Logs**:
   ```bash
   docker logs -f social-backend
   ```
   - Look for “Started Application in X seconds.”

3. **Security Group**:
   - Confirm inbound rules have **TCP 8080** open to public (0.0.0.0/0) or an allowed range.

---

## **8. Environment Variables (RDS and Others)**

If your Spring Boot app needs environment variables (e.g., `SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_PASSWORD`), pass them in the `docker run` command:

```bash
docker run -d -p 8080:8080 --name social-backend \
  -e SPRING_DATASOURCE_URL="jdbc:mysql://<rds-endpoint>:3306/<dbname>" \
  -e SPRING_DATASOURCE_USERNAME="username" \
  -e SPRING_DATASOURCE_PASSWORD="secret" \
  social-media-backend:v1
```

Using IAM roles and AWS Secrets Manager is recommended for production credentials, but this is sufficient for a simple setup.

---

## **9. Why Docker on EC2?**

1. **Consistency**:
   - The same container runs identically on local dev machines, EC2, or any container platform.
2. **Scalability**:
   - Easily spin up more container instances if traffic increases.
3. **Simplicity**:
   - No need to install Java, Node, or any dependencies each time—just run your container.
4. **Portable to Other Services**:
   - You can later migrate to **AWS ECS**, **Kubernetes**, or any environment that supports Docker.

---

## **Conclusion**

By following these steps, you will have:
- **Dockerized** your Spring Boot application.
- **Installed and configured Docker on EC2**.
- **Deployed** your container to run on AWS with minimal OS configuration.

This approach streamlines deployments, ensures environment consistency, and prepares you for advanced DevOps steps like **CI/CD** pipelines and container orchestration. 

Happy Containerizing 💪!