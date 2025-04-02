Optional - To push an image to Docker Hub, follow these steps:

---

### **Step 1: Log In to Docker Hub**
You need to authenticate with Docker Hub using the `docker login` command.

1. Open a terminal.
2. Run:
   ```bash
   docker login
   ```
3. Enter your **Docker Hub username** and **password** (or personal access token for enhanced security).

---

### **Step 2: Tag Your Docker Image**
Docker Hub requires images to be tagged with your Docker Hub username and repository name before pushing.

1. List your Docker images:
   ```bash
   docker images
   ```
2. Tag the image for Docker Hub:
   ```bash
   docker tag <image-id> <dockerhub-username>/<repository-name>:<tag>
   ```
   Replace:
   - `<image-id>`: The ID of the image from the `docker images` command.
   - `<dockerhub-username>`: Your Docker Hub username.
   - `<repository-name>`: The name of the repository you want to create or use.
   - `<tag>`: (Optional) A tag like `v1.0` or `latest`. If omitted, it defaults to `latest`.

   Example:
   ```bash
   docker tag 123abc456def myusername/my-app:v1.0
   ```

---

### **Step 3: Push the Image**
Push the tagged image to your Docker Hub repository:
```bash
docker push <dockerhub-username>/<repository-name>:<tag>
```

Example:
```bash
docker push myusername/my-app:v1.0
```

---

### **Step 4: Verify on Docker Hub**
1. Log in to your Docker Hub account: [https://hub.docker.com/](https://hub.docker.com/).
2. Navigate to your repositories to confirm the image was successfully pushed.

---

### Example Commands (Complete Workflow)
1. Build the Docker image:
   ```bash
   docker build -t my-app .
   ```
2. Tag the image:
   ```bash
   docker tag my-app myusername/my-app:latest
   ```
3. Push the image:
   ```bash
   docker push myusername/my-app:latest
   ``` 
