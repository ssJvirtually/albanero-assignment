FROM openjdk:21-jdk
EXPOSE 9130
ADD ./shipment/target/shipment-service.jar shipment-service.jar
ENTRYPOINT ["java","-jar","/shipment-service.jar","--spring.profiles.active=prod"]