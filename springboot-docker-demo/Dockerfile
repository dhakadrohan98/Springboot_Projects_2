#base-image
FROM eclipse-temurin:17
#Add metadata to an image.
LABEL maintainer="roha.dhakad@wissen.com"
#whenever we run a container, app directory will be created in a container
WORKDIR /app
#copy source file (springboot-docker-demo jar file) into destination (our container)
COPY target/springboot-docker-demo-0.0.1-SNAPSHOT.jar /app/springboot-docker-demo.jar
#default executable
ENTRYPOINT ["java", "-jar", "springboot-docker-demo.jar"]