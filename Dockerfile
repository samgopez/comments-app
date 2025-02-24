FROM eclipse-temurin:21-jdk-alpine AS build

# Set working directory
WORKDIR /app

# Copy Maven Wrapper files
COPY .mvn/ .mvn
COPY mvnw mvnw
RUN chmod +x mvnw

# Copy project files
COPY pom.xml .
COPY src ./src

# Download dependencies and build the application
RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine AS runtime

# Set working directory
WORKDIR /app

# Copy only the built JAR from the first stage
COPY --from=build /app/target/comments-app-0.0.1-SNAPSHOT.jar comments-app.jar

# Expose application port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "comments-app.jar"]