# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Install the necessary packages for MySQL and the dependencies for the Spring project

RUN apt-get update \
    && apt-get install -y --no-install-recommends netcat \
    && apt-get clean \
    && apt-get install -y locales \
# Set the working directory inside the container
WORKDIR /app

# Copy the application JAR file to the container
COPY target/Bookshop-0.0.1-SNAPSHOT.jar /app

# Expose the port the application will run on
EXPOSE 8085

# Command to run the JAR file
ENTRYPOINT ["java", "-jar", "Bookshop-0.0.1-SNAPSHOT.jar"]