## AWS Fundamentals, CodeBuild/CodeCommit/CodeDeploy, Splunk, and other DevOps Concepts

### **Section 1: AWS Fundamentals**

#### 1.1 **Amazon EC2 (Elastic Compute Cloud)**

- **What is EC2?**
  - EC2 is a web service that provides resizable compute capacity in the cloud, allowing you to run applications on virtual servers called instances.

- **Why would you choose to use an EC2 to host your DB?**
  - Full control over the database server, including operating system configurations and specific database software versions.
  - Ability to customize hardware specifications, security settings, and data backups.
  - EC2 is a good fit when the database requires special configurations not supported by managed services like RDS.

- **Key Features of EC2**:
  - **Auto Scaling**: Automatically add or remove EC2 instances based on specified conditions like CPU utilization.
  - **Elastic Load Balancing (ELB)**: Distributes incoming application traffic across multiple EC2 instances.

- **Setting Up EC2 Auto Scaling**:
  - Create a **Launch Configuration** or **Launch Template**.
  - Define an **Auto Scaling Group** and configure scaling policies to scale in and out based on metrics like CPU usage or memory consumption.

- **Select the Correct Syntax for SSH Command**:
  - The correct syntax to connect to an EC2 instance is:
    ```bash
    ssh -i "your-key.pem" ec2-user@ec2-x-x-x-x.compute-1.amazonaws.com
    ```
    - `your-key.pem`: The key pair file.
    - `ec2-user`: Default username for Amazon Linux AMIs.
    - `ec2-x-x-x-x.compute-1.amazonaws.com`: Public DNS of the EC2 instance.

- **It is Impossible to Reassign a New Key to an EC2 Instance After It Is Created.**
  - **False**: You can reassign a new key pair by creating a new image (AMI) of the instance or by mounting the EBS volume on another instance to change the key configuration.

- **How Do You Secure EC2?** (Select all valid options)
  - Use **Security Groups** and **Network ACLs** to control traffic.
  - Apply **IAM Roles** for permissions management.
  - Enable **Encryption** for data at rest and in transit.
  - Regularly update the instance OS and software to patch security vulnerabilities.

- **Programmatic Access to AWS Services**:
  - Create an **IAM User** with programmatic access enabled and generate an **Access Key ID** and **Secret Access Key**.

- **In AWS, Security Groups Allow All Traffic by Default.**
  - **False**: Security Groups deny all inbound traffic by default and allow all outbound traffic.

- **AWS Regions and Availability Zones**:
  - **Regions**: Geographical locations with multiple Availability Zones.
  - **Availability Zones**: Discrete data centers with redundant power, networking, and connectivity.

---

#### 1.2 **Amazon RDS (Relational Database Service)**

- **What is Amazon RDS?**
  - A managed relational database service that supports multiple database engines, including MySQL, PostgreSQL, Oracle, and SQL Server.

- **Why Choose RDS Over Hosting on EC2?**
  - RDS handles database management tasks such as backups, patching, and replication, allowing you to focus on your application rather than database maintenance.

- **RDS Backup and Restore Options**:
  - Automatic daily snapshots.
  - Point-in-time recovery using transaction logs.

---

#### 1.3 **Amazon S3 (Simple Storage Service)**

- **Amazon S3 is Which Type of Storage Service?**
  - Object Storage Service.

- **Possible Uses of an S3 Bucket?** (Select all that apply)
  - Storing application backups.
  - Hosting static websites.
  - Storing and retrieving data for web applications.
  - Storing media files like images and videos.

- **Rules for Setting Up an S3 Bucket**:
  - Bucket names must be globally unique.
  - Names must be DNS-compliant (lowercase letters, numbers, and hyphens).

- **Controlling Access to S3 Objects**:
  - Use **Bucket Policies**, **IAM Policies**, or **Access Control Lists (ACLs)**.

- **S3 Buckets for Hosting Websites**:
  - S3 buckets can host static websites without needing a web server. Enable **static website hosting** and set the bucket policy to allow public access.

- **You Want to Host a Front-End Website in AWS. Which Service Should You Use?**
  - **Amazon S3**.

- **How to Make an S3 Bucket Public for Webpage Access?**
  - Configure the bucket policy to allow public read access.
  - Enable static website hosting in the bucket settings.

---

#### 1.4 **AWS Elastic Block Store (EBS)**

- **What is EBS?**
  - A block storage service used for storing data persistently with EC2 instances. It provides highly available and durable volumes that can be attached to EC2 instances.

- **Types of EBS Volumes**:
  - General Purpose SSD (gp2, gp3).
  - Provisioned IOPS SSD (io1, io2).
  - Throughput Optimized HDD (st1).
  - Cold HDD (sc1).

- **Snapshots and Backups**:
  - You can create snapshots of EBS volumes for backup or disaster recovery purposes.

---

### **Section 2: CodeBuild, CodeCommit, CodeDeploy**

- **What is AWS CodeBuild?**
  - A fully managed build service that compiles source code, runs tests, and produces software packages.

- **CodeBuild’s Key Features**:
  - Supports build environments like Amazon Linux, Ubuntu, and Windows.
  - Can execute any build commands defined in a `buildspec.yml` file.

- **What File Does CodeDeploy Read by Default?**
  - The `appspec.yml` file.

- **Difference Between CodeBuild and CodeDeploy**:
  - **CodeBuild**: Runs build commands in a temporary environment.
  - **CodeDeploy**: Deploys applications to EC2 instances, on-premises servers, or Lambda functions.

---

### **Section 3 (OPTIONAL, no QC): Splunk**

- **Role of a Splunk Indexer**:
  - An indexer indexes incoming data and enables fast search and retrieval of this data.

- **Primary Function of the Splunk Forwarder**:
  - Collects and sends log data from a source (e.g., server, application) to the Splunk indexer for indexing and analysis.

- **Optimizing Searches for Faster Performance**:
  - Use selective fields (`fields` command) to reduce data retrieval.
  - Limit the time range of the search.
  - Use `tstats` for optimized searches on large datasets.

- **Command Used to Transform Events into Statistical Data Tables**:
  - `stats`

- **Extracting Fields from Unstructured Data**:
  - Use the `rex` command to extract fields using regular expressions.

---

### **Section 4: DevOps Concepts**

- **DevOps and Agile**:
  - DevOps integrates with Agile by promoting collaboration, continuous feedback, and automation, enabling faster and more reliable software delivery.

- **Primary Goal of DevOps**:
  - Shorten the development lifecycle and deliver high-quality software rapidly and reliably.

- **Continuous Integration (CI)**:
  - The practice of merging code changes into a shared repository frequently, with automated builds and tests triggered for each change.

- **Continuous Delivery (CD)**:
  - Automatically prepares code changes for release to production, ensuring that every build is production-ready.

- **Continuous Deployment (CD)**:
  - Automatically deploys code changes to production without manual intervention.

---

### **Section 5: Docker Fundamentals**

- **What is Docker?**
  - Docker is a platform that enables developers to package applications and their dependencies into containers, providing a consistent environment across different stages of development and deployment.

- **Why Use Docker Instead of Virtual Machines (VMs)?**
  - Docker containers are lightweight and start quickly compared to VMs. Containers share the host OS kernel, which reduces overhead.

- **Key Docker Commands**:
  - `docker build -t image-name .` – Build a Docker image.
  - `docker run -p 8080:8080 image-name` – Run a container from an image.
  - `docker ps` – List running containers.
  - `docker push image-name` – Push a Docker image to a registry