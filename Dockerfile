FROM openjdk:11-jdk
VOLUME /tmp
EXPOSE 80
ADD . .
RUN chmod +x gradlew && ./gradlew bootJar
ENTRYPOINT ["java","-jar","build/libs/Shortly-0.0.1.jar"]