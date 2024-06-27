FROM openjdk:11-jre-slim
VOLUME /tmp
COPY target/zipkin-server.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
