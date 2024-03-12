# Use Maven on OpenJDK 8 as the base image
FROM maven:3.6.3-openjdk-8

# Copy your project files into the image
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp

# Compile your project. This step is optional but can speed up tests later.
RUN mvn clean install -DskipTests

# The command to run tests. We don't execute it here but specify how to run it.
CMD ["mvn", "test", "-Dheadless=true", "-Dremote=true"]
