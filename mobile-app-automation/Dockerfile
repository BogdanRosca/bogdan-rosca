FROM openjdk:21-jdk-slim

WORKDIR /app

ENV APPIUM_SERVER_URL=http://host.docker.internal:4723

COPY gradlew .
COPY gradle/ gradle/
COPY build.gradle.kts .

RUN chmod +x gradlew

RUN ./gradlew dependencies

COPY src/ src/

RUN ./gradlew assemble

CMD ["./gradlew", "test", "--info"]