FROM openjdk:11

ARG PROFILE

ARG ADDITIONAL_OPTS

ENV PROFILE=${PROFILE}
ENV ADDITIONAL_OPTS=${ADDITIONAL_OPTS}

WORKDIR /opt/spring_boot

COPY /target/mvc-spring-ajax-0.0.1-SNAPSHOT.jar /app/mvc-spring-ajax.jar

SHELL ["/bin/sh","-c"]

EXPOSE 8082
EXPOSE 8080

CMD java ${ADDITIONAL_OPTS} -jar mvc-spring-ajax-0.0.1-SNAPSHOT.jar --spring.profile.active=${PROFILE}