# Build the Java application
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Run the application with Tomcat
FROM tomcat:10.1.59-jdk17-temurin

# Remove Tomcat's default applications
RUN rm -rf /usr/local/tomcat/webapps/*

# Render expects the web service to listen on port 10000
RUN sed -i 's/port="8080"/port="10000"/' /usr/local/tomcat/conf/server.xml

# Deploy our WAR as the root application
COPY --from=build /app/target/StudentManagementSystem.war \
    /usr/local/tomcat/webapps/ROOT.war

EXPOSE 10000

CMD ["catalina.sh", "run"]