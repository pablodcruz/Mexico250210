# 1. Use a lightweight Java base image
FROM eclipse-temurin:17-jdk-alpine

# 2. Create and use an application directory
WORKDIR /app

# 3. Copy the JAR file from the build context
COPY SpringTodoApp-0.0.1-SNAPSHOT.jar app.jar

# 4. Expose the Spring Boot port
EXPOSE 8080

# 5. Run the application
CMD ["java", "-jar", "app.jar"]