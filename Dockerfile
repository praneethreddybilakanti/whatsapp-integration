
# Use the official maven/Java 11 image to create a build artifact.
# https://hub.docker.com/_/maven
FROM maven:3.8.3-openjdk-17-slim  AS builder

# Set the working directory to /app
WORKDIR /source
# Copy the pom.xml file to download dependencies
COPY pom.xml ./
# Copy local code to the container image.
COPY src ./src

# Download dependencies and build a release artifact.
RUN mvn package -DskipTests

# Use OpenJDK for base image.
# https://hub.docker.com/_/openjdk
# https://docs.docker.com/develop/develop-images/multistage-build/#use-multi-stage-builds
FROM openjdk:17-alpine

# Copy the jar to the production image from the builder stage.
COPY --from=builder /source/target/social-marketing-services-*.jar /social-marketing-services.jar

EXPOSE 8081
# Run the web service on container startup.

CMD ["java", "-jar", "/social-marketing-services.jar"]
