FROM openjdk:19-alpine
MAINTAINER Neto

WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:resolve
COPY src ./src

VOLUME /tmp
EXPOSE 8080

CMD ["./mvnw", "spring-boot:run"]