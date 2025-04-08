# Use official Jenkins LTS image
FROM jenkins/jenkins:latest

USER root

# Install Maven and Node.js v18 LTS
RUN apt-get update && \
    apt-get install -y maven curl gnupg && \
    curl -fsSL https://deb.nodesource.com/setup_18.x | bash - && \
    apt-get install -y nodejs && \
    npm install -g npm@10 && \
    apt-get clean

# Verify installations
RUN mvn -v && node -v && npm -v

USER jenkins
