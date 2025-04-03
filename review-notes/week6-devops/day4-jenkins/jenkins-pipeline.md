# Project 2: CI/CD Guidelines with Jenkins

## Overview

For the final step of Project 2, you will integrate **Jenkins** into your Social Networking application to automate the CI/CD process. Jenkins will streamline testing, building, and deploying your Dockerized backend application to AWS. The pipeline will ensure efficient code integration, container deployment, and delivery to production.

---

## Prerequisites

1. **Jenkins Installation via Docker**:
   - Ensure Docker is installed and running on your system.
   - Pull and run the Jenkins LTS Docker image.

2. **Credentials**:
   - GitHub: Generate a Personal Access Token.
   - Docker Hub: Username and Password.
   - AWS: IAM Role or Access/Secret Keys.

3. **Dockerized Application**:
   - The Spring Boot backend must be containerized with a functional Dockerfile.
   - Ensure the image is pushed to Docker Hub or AWS ECR.

4. **AWS EC2 Instance**:
   - EC2 instance hosting your backend.
   - Security group allowing Jenkins to deploy (port 22 for SSH and 8080 for the app).

---

## Step 1: Set Up Jenkins with Docker

### Pull the Jenkins Docker Image
1. Run the following command to pull the Jenkins LTS Docker image:
   ```bash
   docker pull jenkins/jenkins:lts
   ```

### Run Jenkins in a Docker Container
2. Start the Jenkins container with persistent storage:
   ```bash
   docker run -d \
     -p 8080:8080 \
     -p 50000:50000 \
     --name jenkins \
     -v jenkins_home:/var/jenkins_home \
     jenkins/jenkins:lts
   ```

   **Explanation**:
   - `-d`: Run the container in detached mode.
   - `-p 8080:8080`: Map port 8080 of the host to port 8080 of the container (Jenkins UI).
   - `-p 50000:50000`: Map port 50000 for Jenkins agent connections.
   - `--name jenkins`: Name the container "jenkins".
   - `-v jenkins_home:/var/jenkins_home`: Persist Jenkins data.

### Access Jenkins Dashboard
3. Open your browser and navigate to:
   ```
   http://localhost:8080
   ```
4. Retrieve the initial admin password to unlock Jenkins. Can also be found in Docker Destop container logs:
   ```bash
   docker exec jenkins cat /var/jenkins_home/secrets/initialAdminPassword
   ```
5. Enter the password and follow the setup wizard to install recommended plugins.
6. Secure Jenkins by creating an admin user with strong credentials.

---

## Jenkins Pipeline Design

### **Pipeline Stages**

1. **Checkout Source Code**:
   - Pull the latest code from the GitHub repository.

2. **Build Docker Image**:
   - Use the Dockerfile to build the Spring Boot application into a Docker image.

3. **Push Docker Image**:
   - Push the built Docker image to Docker Hub or AWS ECR. Refer to `docker-hub-registry.md` is needed.

4. **Deploy to AWS**:
   - SSH into the EC2 instance and deploy the updated container.

---

## Pipeline Configuration

### **Step 2: Configure Jenkins Plugins**

Install the following plugins:

- **Git Plugin**: For GitHub repository integration.
- **Pipeline Plugin**: For creating pipelines using Jenkinsfile.
- **Docker Plugin**: For building and pushing Docker images.
- **SSH Agent Plugin**: For SSH access to the EC2 instance.

---

### **Step 3: Create a Jenkinsfile**

Place the following `Jenkinsfile` in your repository's root directory:

```groovy
pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "<dockerhub-username>/social-media-backend:v${BUILD_NUMBER}"
        EC2_HOST = "<EC2-PUBLIC-IP>"
        EC2_USER = "ec2-user"
        SSH_KEY = credentials('jenkins-ec2-key')
    }

    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    sh "docker build -t ${DOCKER_IMAGE} ."
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    withDockerRegistry([credentialsId: 'docker-hub-credentials', url: '']) {
                        sh "docker push ${DOCKER_IMAGE}"
                    }
                }
            }
        }

        stage('Deploy to AWS EC2') {
            steps {
                sshagent(['jenkins-ec2-key']) {
                    sh "ssh -o StrictHostKeyChecking=no ${EC2_USER}@${EC2_HOST} \"docker pull ${DOCKER_IMAGE} && docker stop social-backend || true && docker rm social-backend || true && docker run -d -p 8080:8080 --name social-backend ${DOCKER_IMAGE}\""
                }
            }
        }
    }

    post {
        success {
            echo 'Deployment Successful!'
        }
        failure {
            echo 'Deployment Failed!'
        }
    }
}
```

---

### **Step 4: Configure Jenkins Credentials**

1. **GitHub Credentials**:
   - Add your Personal Access Token under Jenkins > Manage Credentials.

2. **Docker Hub Credentials**:
   - Add Docker Hub username and password as `docker-hub-credentials`.

3. **SSH Key for EC2**:
   - Add the private key for EC2 access as `jenkins-ec2-key`.

---

### **Step 5: Set Up Jenkins Job**

1. **Create a New Item**:
   - Choose "Pipeline" and name the job.

2. **Source Code Management**:
   - Configure the Git repository URL and branch.

3. **Pipeline**:
   - Choose "Pipeline script from SCM".
   - Set the repository path and branch, and ensure the `Jenkinsfile` path is correct.

4. **Build Triggers**:
   - Enable "Poll SCM" or configure a webhook for GitHub.

---

### **Step 6: Test and Monitor the Pipeline**

1. **Run the Pipeline**:
   - Manually trigger the build initially.

2. **Verify Stages**:
   - Ensure each stage (checkout, build, push, deploy) runs successfully.

3. **Monitor Logs**:
   - View Jenkins console output for build and deployment status.

4. **Check Application**:
   - Visit `http://<EC2-PUBLIC-IP>:8080` to confirm the app is running.

---

## Best Practices

1. **Environment Variables**:
   - Avoid hardcoding sensitive information. Use Jenkins credentials.

2. **Rollback Plan**:
   - Maintain a backup of the previous Docker image for rollback if the deployment fails.

3. **Monitoring**:
   - Use CloudWatch for logs and metrics.