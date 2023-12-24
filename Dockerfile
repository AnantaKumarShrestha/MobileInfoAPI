FROM openjdk:21
ADD ./docker-spring.jar docker-spring.jar
ENTRYPOINT ["java","jar","docker-spring.jar"]