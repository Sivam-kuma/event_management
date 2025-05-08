# Use a lightweight Java runtime base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the jar file to the container
COPY build/libs/*.jar app.jar

# Expose port (change if needed)
EXPOSE 8080

# Command to run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
