# Use Maven on OpenJDK 8 as the base image
FROM maven:3.6.3-openjdk-8

COPY . /usr/src/vicariusautomation
WORKDIR /usr/src/vicariusautomation

RUN mvn clean install -DskipTests

ENTRYPOINT ["mvn", "test", "-Dheadless=true", "-Dremote=true"]
