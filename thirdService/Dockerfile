FROM openjdk:12
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Dserver.port=${SERVER_PORT}","-Dspring.data.mongodb.uri=${MONGODB_URI}","-jar","app.jar"]