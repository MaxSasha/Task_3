FROM openjdk:12
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
COPY run.sh run.sh
RUN chmod +x run.sh
EXPOSE 8081:8081
CMD ./run.sh