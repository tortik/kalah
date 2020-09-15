FROM openjdk:11-jre-slim
ARG JAR_FILE=target/kalah*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT exec java $JAVA_OPTS  -jar /app.jar