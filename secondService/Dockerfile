FROM openjdk:12
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Dserver.port=${SERVER_PORT}","-Dspring.data.mongodb.uri=${MONGODB_URI}","-Dsecond-service.ribbon.listOfServers=${SECOND_SERVICE_URL}","-jar","app.jar"]