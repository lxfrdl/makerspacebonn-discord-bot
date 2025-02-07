# Stage 1: Build the application
FROM eclipse-temurin:21-jdk as build

# Set the working directory
WORKDIR /app

# Copy the Gradle wrapper and project files
COPY gradlew gradlew
COPY gradle gradle
COPY build.gradle.kts build.gradle.kts
COPY settings.gradle.kts settings.gradle.kts
COPY src src

# Grant execute permission for the Gradle wrapper
RUN chmod +x gradlew

# Build the application
RUN ./gradlew build

# Stage 2: Create the runtime image
FROM amazoncorretto:21-alpine-jdk

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/build/libs/makerspacebonn-discord-bot.jar /app/app.jar

# Expose the port your application runs on
EXPOSE 8080

# Set the entry point to run the JAR file
ENTRYPOINT ["java", "-jar", "/app/app.jar"]