# Use official OpenJDK image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy everything to container
COPY . .

# Build the app
RUN ./mvnw clean package -DskipTests

# Run the app
CMD ["java", "-jar", "target/*.jar"]
