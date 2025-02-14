
1. **Java (JDK 17)**  
2. **IDE / Editors** (VS Code and/or IntelliJ)  
3. **Git**  
4. **PostgreSQL**  
5. **DBeaver**  
6. **Postman**  
7. **Spring & Spring Boot**  
8. **React & TypeScript**  
9. **Docker**  
10. **Jenkins**  
11. **AWS** (with AWS CLI for EC2)

---

## 1. Java (JDK 17)
- **Needed for**: Spring, Spring Boot (Java-based backend).
- **Install**: [Adoptium (Temurin)](https://adoptium.net/) or [Oracle JDK 17](https://www.oracle.com/java/technologies/downloads/).
- **Check PATH**:
  - On Windows, the installer usually sets `JAVA_HOME` and updates PATH automatically. If not, add the bin folder to `PATH` manually.
  - **Verify** with `java -version`.

---

## 2. IDE / Editors

### Visual Studio Code
- **Needed for**: General coding, Java or front-end (React/TypeScript).
- **Install**: [https://code.visualstudio.com/](https://code.visualstudio.com/).  
- **Recommended extensions**:
  - Java Extension Pack (for Java)
  - Spring Boot Extension Pack
  - Docker extension
  - ESLint/Prettier (for JS/TS)

### IntelliJ IDEA
- **Needed for**: More advanced Java/Spring features (if not using VS Code).
- **Install**: [https://www.jetbrains.com/idea/](https://www.jetbrains.com/idea/).  
  - Community Edition is free.
  - Ultimate Edition has advanced Spring features.

*(Choose **one** for Java—VS Code **or** IntelliJ. You can still use VS Code for front-end if you prefer IntelliJ for backend. I use both.)*

---

## 3. Git
- **Needed immediately** for version control and collaboration.
- **Install**: [https://git-scm.com/downloads](https://git-scm.com/downloads)
- **Check PATH**:
  - On Windows, the Git installer usually sets PATH automatically.
  - **Verify** with `git --version`.

---

## 4. PostgreSQL
- **Needed for**: Relational database usage.
- **Install**: [https://www.postgresql.org/download/](https://www.postgresql.org/download/)
  - You can **skip pgAdmin** if using DBeaver.
- **Add `psql` to PATH** if not done automatically:
  - On Windows, add `C:\Program Files\PostgreSQL\<version>\bin` to PATH.
  - On macOS/Linux, add `/Library/PostgreSQL/<version>/bin` or `/usr/pgsql-<version>/bin`.
- **Verify** with `psql -U postgres`.

---

## 5. DBeaver
- **Needed for**: UI database management and SQL querying (instead of pgAdmin). Alternative, can use the command line. 
- **Install**: [https://dbeaver.io/download/](https://dbeaver.io/download/)

---

## 6. Postman
- **Needed for**: Testing REST APIs, like your project0.
- **Install**: [https://www.postman.com/downloads/](https://www.postman.com/downloads/)

---

## 7. Spring & Spring Boot
- **Needed for**: Java-based backend frameworks.
- **Install**:  
  - Typically included as dependencies in your Maven/Gradle project (no separate installer).
  - Use [https://start.spring.io/](https://start.spring.io/) or your IDE’s project wizard.

---

## 8. React & TypeScript
- **Needed for**: Front-end development.
- **Install**:
  - First install **Node.js** (which includes `npm`):
    - [https://nodejs.org/en/download/](https://nodejs.org/en/download/)
    - **Add** Node.js to PATH if not automatically done.
    - **Verify** with `node -v` and `npm -v`.
  - **React** is typically set up via `create-react-app` or Vite:
    ```bash
    npx create-react-app my-app
    # or
    npm create vite@latest
    ```
  - **TypeScript** can be installed per-project or globally. We will install globally:
    ```bash
    npm install -g typescript
    ```
    **Verify** with `tsc -v`.

---

## 9. Docker
- **Needed for**: Containerizing and running your applications.
- **Install**:
  - **Docker Desktop** (Windows/macOS): [https://www.docker.com/products/docker-desktop/](https://www.docker.com/products/docker-desktop/)
  - **Docker Engine** (Linux): Via your distribution’s package manager.
- **Verify**: `docker --version`

---

## 10. Jenkins
- **Needed for**: Continuous Integration (CI) / Continuous Delivery (CD).
- **Install**:
  - [https://www.jenkins.io/download/](https://www.jenkins.io/download/)
  - Or run via Docker:
    ```bash
    docker run -p 8080:8080 jenkins/jenkins:lts
    ```

---

## 11. AWS (with AWS CLI for EC2)
- **Needed for**: Deploying to and managing AWS services (EC2, S3, etc.).
- **AWS CLI**:
  - [https://docs.aws.amazon.com/cli/latest/userguide/getting-started-install.html](https://docs.aws.amazon.com/cli/latest/userguide/getting-started-install.html)
  - **Verify** with `aws --version`.
- **AWS Account**: Sign up at [https://aws.amazon.com/](https://aws.amazon.com/) if you don’t already have one. Careful to stay within free-tier if you try this without my instructions.

---
