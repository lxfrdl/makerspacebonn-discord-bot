# Use the official Amazon Corretto image from the Docker Hub
FROM amazoncorretto:21-alpine-jdk

# Set the working directory
WORKDIR /app

# Copy the JAR file into the container
COPY build/libs/makerspacebonn-discord-bot.jar /app/makerspacebonn-discord-bot.jar

# Expose the port your application runs on
EXPOSE 8080

# Set the entry point to run the JAR file
ENTRYPOINT ["java", "-jar", "/app/makerspacebonn-discord-bot.jar"]