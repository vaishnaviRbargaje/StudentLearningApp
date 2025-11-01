# Use Java 17 base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the JAR built by Maven
COPY target/*.jar app.jar

# Railway uses dynamic ports
EXPOSE 8080

# Command to run the app
CMD ["java", "-jar", "app.jar"]
