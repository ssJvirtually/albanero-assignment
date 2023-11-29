FROM openjdk:21-jdk
EXPOSE 9129
ADD target/payment-service.jar payment-service.jar
ENTRYPOINT ["java","-jar","/payment-service.jar"]