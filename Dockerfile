FROM openjdk:17
VOLUME /tmp
EXPOSE 8080
ADD target/tenpo-challenge.jar tenpo-challenge.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/tenpo-challenge.jar"]