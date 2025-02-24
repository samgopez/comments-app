# Comments App

## Prerequisites
- Java 21
- Maven
- Docker

## Running the Application

### 1. Running Locally using Maven
```sh
./mvnw spring-boot:run
```

### 2. Running the Tests
To execute tests:
```sh
./mvnw clean test
```

After running tests, generate the JaCoCo report:
```sh
./mvnw jacoco:report
```

This will create a coverage report in:
```
target/site/jacoco/index.html
```

To execute tests and generate the JaCoCo html report:
```sh
./mvnw clean verify
```

### 3. Building and Running with Docker
Build the Docker Image
```sh
docker build -t comments-app .
```

Run the Application in a Container
```sh
docker run -p 8080:8080 comments-app
```

### 4. Running with Docker Compose
```sh
docker-compose up --build
```

### Database Access
H2 console is available at: http://localhost:8080/h2-console

Use JDBC URL: jdbc:h2:mem:comment_test_db