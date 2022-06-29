FROM openjdk:11
VOLUME /tmp
EXPOSE 80
ADD . .
RUN ./gradlew build -x test --stacktrace
ARG JAR_FILE=build/libs/Shortly-0.0.1.jar
COPY ${JAR_FILE} Shortly.jar
ENTRYPOINT ["java","-jar","/Shortly.jar"]