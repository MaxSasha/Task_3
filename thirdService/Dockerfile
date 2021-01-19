FROM  gradle:6.7-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon 

FROM openjdk:12

COPY --from=build /home/gradle/src/build/libs/*.jar /app.jar

ENTRYPOINT ["java","-Dserver.port=${SERVER_PORT}","-Dspring.data.mongodb.uri=${MONGODB_URI}","-jar","app.jar"]

