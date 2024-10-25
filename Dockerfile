# Use the official Gradle image with JDK 17
FROM gradle:7.6-jdk17 AS build

# Set the working directory
WORKDIR /app

# Copy the Gradle wrapper and build files
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# Copy the source code
COPY src src

# Give execute permission to the gradlew script
RUN chmod +x gradlew

# Build the application
RUN ./gradlew build -x test --no-daemon

# Use a smaller image for the final runtime
FROM openjdk:17-slim

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the build stage
# COPY --from=build /app/build/libs/school-management-0.0.1-SNAPSHOT.jar
COPY --from=build /app/build/libs/*.jar app.jar

#Expose port : 8080
EXPOSE 8080

# Specify the command to run the application
# ENTRYPOINT ["java", "-jar", "/app/build/libs/school-management-0.0.1-SNAPSHOT.jar"]
ENTRYPOINT ["java", "-jar", "app.jar"]