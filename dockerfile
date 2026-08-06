FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY target/department-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 9001

ENTRYPOINT ["java","-jar","app.jar"]