FROM openjdk:11

EXPOSE 8080

ADD target/mvc-spring-ajax-0.0.1-SNAPSHOT.jar mvc-spring-ajax.jar

ENTRYPOINT [ "java","-jar","mvc-spring-ajax.jar"]