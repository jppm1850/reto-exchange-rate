FROM amazoncorretto:11-alpine-jdk as build
MAINTAINER junior pedro pecho mendoza <jppm1850@gmail.com>
WORKDIR /workspace/app
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src
RUN ./mvnw clean install

FROM amazoncorretto:11-alpine-jdk
MAINTAINER junior pedro pecho mendoza <jppm1850@gmail.com>
COPY --from=build /workspace/app/target/api-exchange-rate-0.0.1-SNAPSHOT.jar api.jar
ENTRYPOINT ["java","-jar","/api.jar"]
EXPOSE 8080