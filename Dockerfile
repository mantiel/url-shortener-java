# Stage 1: Build the application using the JDK
FROM eclipse-temurin:25-jdk-alpine AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy Gradle wrapper and configuration files
COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts settings.gradle.kts ./

# Make the Gradle wrapper executable
RUN chmod +x gradlew

# Download dependencies and cache them in a separate layer
RUN ./gradlew dependencies --no-daemon

# Copy the application source code
COPY src src

# Build the application without running tests
RUN ./gradlew build -x test --no-daemon

# Stage 2: Create a lightweight runtime image using the JRE
FROM eclipse-temurin:25-jre-alpine

# Set the working directory for the application
WORKDIR /app

# Copy the generated JAR from the build stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Start the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]