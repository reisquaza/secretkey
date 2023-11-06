#
# Build stage
#
FROM maven:3.9.4-eclipse-temurin AS build
COPY . .
RUN mvn clean package -DskipTests

#
# Package stage
#
FROM openjdk:17
COPY --from=build /target/secretkey-0.0.1-SNAPSHOT.jar secretkey.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","secretkey.jar"]