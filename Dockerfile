FROM java:8
COPY build/libs/*.jar /var/www/java/
WORKDIR /var/www/java
CMD ["java","-jar","gs-rest-service-0.1.0.jar"]