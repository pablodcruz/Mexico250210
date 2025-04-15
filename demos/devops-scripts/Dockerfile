# Define the parent image
FROM ubuntu

# Install needed programs
RUN apt-get update
RUN apt-get -y dist-upgrade
RUN apt-get -y install default-jdk

# Within the image and thus container, set the working directory to the new directory example
WORKDIR /example

# Create Hello World Java program and save it in the appropriate file
RUN echo 'public class HiWorld{ public static void main(String[] args){System.out.println("Hi world");}}' > HiWorld.java

# Compile the Java program, creating the file that the JVM can actually run
RUN javac HiWorld.java

# Run the HelloWorld program in the container
CMD ["java", "HiWorld"]
