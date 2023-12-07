FROM openjdk:21-jdk
EXPOSE 8080
ADD target/order-service.jar order-service.jar
ENTRYPOINT ["java","-jar","/order-service.jar","--spring.profiles.active=prod"]