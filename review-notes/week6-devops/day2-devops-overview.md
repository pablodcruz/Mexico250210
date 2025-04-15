## Introduction to Cloud Computing

### What is Cloud Computing?

Cloud computing refers to delivering computing resources (like servers, storage, databases, networking, software) over the internet ("the cloud") to offer faster innovation, flexible resources, and economies of scale. Instead of owning and maintaining physical data centers and servers, you can access these resources on-demand from a cloud provider.

### Why is Cloud Computing Relevant to DevOps?

1. **Scalability and Flexibility**: Supports the rapid scaling up or down of resources based on application demands.
2. **Automation**: Integral for continuous integration, delivery, and deployment (CI/CD pipelines).
3. **Cost Efficiency**: Pay-as-you-go pricing models align with the incremental and iterative nature of DevOps.
4. **Global Reach**: Enables teams across geographies to collaborate in real-time, essential for DevOps practices.

### Types of Cloud Services Supporting DevOps

1. **IaaS (Infrastructure as a Service)**: Provides virtualized hardware and network infrastructure.
2. **PaaS (Platform as a Service)**: Offers tools and frameworks for application development and deployment.
3. **SaaS (Software as a Service)**: Supplies software applications that can streamline DevOps workflows.

---

# DevOps Overview

## DevOps stands for the combination of Development and Operations.
Historically these have been two different teams. The dev team designed the application and then the operations team deployed and maintained the application.  
DevOps is a methodology or mindset that combines development and the operations regarding SDLC. So, a single team can develop, deploy, and maintain an application. This only becomes feasible at scale with a significant amount of automation.  
Thus, the primary goal of DevOps is to expedite the lifecycle of application development and deployment through automation.

---

## General Steps in DevOps Production of Code:
1. **Source Code Control**: Producing or writing code and pushing it to a repository with other team members' code.
2. **Building and Testing**: Running unit tests and ensuring the code base can be compiled into a functioning artifact.
3. **Staging Deployment**: Deploys the build to an environment where higher-level testing can be done. Integration and end-to-end testing generally.
4. **Acceptance Testing**: More complex/higher-level testing to ensure the build meets quality standards and will be appreciated by the end users.
5. **Production Deployment**: Migrating the working build from the staging environment to the production environment where the end users can see the new codebase. This also includes monitoring the deployment to ensure that it continues to function.

---

## CI/CD/CD (Often Shortened as CI/CD)
These terms define the level of automation in a DevOps environment.

### Continuous Integration (CI)
The process of regularly merging code into a central repository that will be integrated with a previously existing codebase. Sometimes the basic unit testing and building of the code.  
**Automation Tools**:
- Git
- Perforce
- Bazaar  
Generally these tools automate step 1 (CI).

### Continuous Delivery (CD)
Requires CI. This automates the DevOps process in something called a pipeline. After code integration and unit testing, the artifact will be built and put into a staging environment where it can be tested.  
**Automation Tools**:
- Jenkins
- CircleCI  
This automates steps 1 and 2.

### Continuous Deployment (CD)
Taking an artifact from the staging environment and deploying it somewhere end users can access it in the production environment.  
**Automation Tools**:
- Jenkins  
Achieved once all steps are automated.

---

## AWS - Amazon Web Services

### Amazon's Cloud Offerings
There are hundreds of services that AWS provides that can form the building blocks of most IT infrastructure.  
- You may pay for what you use.
- How you utilize each service is going to be different depending on the service, but generally, the idea is that they are highly scalable so you can control your costs and only pay for what resources are actually being consumed.
- AWS is highly scalable - it is very easy to purchase additional resources, either scaling by increasing the performance of existing resources, or duplicating resources to run in tandem (horizontal scaling).

### Resource Locations
- **Regions**: Geographic areas of the world that are served by two or more AZs (Availability Zones).
- **Availability Zones**: These are physical locations that hardware is located in. The goal is that every connection to an AZ in a region should be fast enough that there is no noticeable difference between the AZs in that zone. Sometimes though it is important that resources are placed in the same AZ to facilitate easier communication between them.

---

## Categories/Types of Cloud Services:
- **SaaS** - Software as a Service: Provides an entire application.
- **PaaS** - Platform as a Service: This provides an environment for development. So the resources provided have the hardware and the software necessary for all development, but not the actual software that is being designed.
  - Examples: Docker, AWS RDS (Relational Database Service), which will host a particular, prebuilt but empty, SQL database for you.
- **IaaS** - Infrastructure as a Service: A base hardware and very low-level software for you to build your environment and your application in. These are usually VMs (virtual machines), like AWS EC2 (Elastic Compute Cloud).

---

## Some AWS Services:
- **RDS** - Relational Database Service: Creates a virtual, scalable, relational database.
- **EC2** - Elastic Compute Cloud: Virtual computer/server (VM).
- **AMI** - Amazon Machine Image: The blueprint for an OS. You can "mount" the AMI on an EC2 to determine what base system the virtual machine will have.
- **Security Groups**: Function like firewalls for instances (RDS, EC2, etc.) and restrict access by IP addresses and port/protocol, etc.
- **S3** - Simple Storage Solution: Static storage, usually for webpages or image storage.
- **Route53** - Domain Name System: Assigns your resources their web addresses. You choose a custom web address; this is not free.
- **ELB** - Elastic Load Balancing: Load balancer and gateway service. It can direct requests across horizontally scaled resources.
- **EC2 Autoscaling**: Automatically adds and removes EC2 instances according to conditions you define.
  - **Benefits**:
    - Lowers Costs
    - Increases Application Availability
    - Improves Fault Tolerance

---

## EBS

Amazon Elastic Block Store (Amazon EBS) provides block-level storage volumes for use in EC2 instances.  
- EBS volumes behave like raw, unformatted block devices.
- You can mount these volumes as devices on your instances.
- You can create files on top of these volumes (like a physical hard drive).
- Dynamically change the configuration of a volume that's attached.
- You pay for what you use.
- You can make backups to store on your S3.

---

## EBS vs S3
- **S3** is for large amounts of data compared to EBS.
- **EBS** is faster than S3.

## Instance Store:
 - **Instance Store** is a temporary storage solution that is physically attached to the host machine running the EC2 instance. 
 - Data on instance store is lost when the instance is stopped, terminated, or if the host hardware fails. 

---

## Amazon S3

Simple Storage Service - it's an object storage service that offers industry-leading scalability, data availability, security, and performance. This means customers of all sizes and industries can use it to store and protect any amount of data for a range of use cases.

### S3 as an Object Storage System:
- Data is stored as individual objects rather than some kind of hierarchy like you see in a file system or directory structure.
- Each object is put into a bucket, and you can connect to Amazon S3 using a URL.
- The URL will have the name of your object and the name of your bucket/container.
- Uses REST API.
- Your browser is going to do an HTTP PUT request to add objects to your bucket.

---

## Use Cases for S3:
- Backup and Storage
- Application Hosting
- Media Hosting
- Software Delivery: Host software apps that your customers can download.
- Static Website Hosting: You can configure a static website to run from an S3 bucket.  