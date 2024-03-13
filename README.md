## Project Title
    Vicarius Sanity Tests

## Description
    VicariusSanityTest class contains 3 sanity tests, signup, signin, and product pages.
    signupSanityTest covers the negative and positive flows of the signup page.
    signinSanityTest covers the negative and positive flows of the signin page.
    productPageSanityTest covers navigate, hovring, and scrolling over page elements.
    Each test covers multiple flows.

## Prerequisites
    Java 8 should be install on your machine.

## Install Maven
    brew install maven

## Run The Project Using Maven
    Navigate to the project folder and run this command:
    mvn test

## Run The Project Using Maven In Headless Mode
    Navigate to the project folder and run this command:
    mvn test -Dheadless=true

## Run The Project Using IDE
    Navigate to VicariusSanityTest class, then double click on the class name and click on Run 'VicariusSanityTest', you can also run each test separately.
    If you want to open the project using IDE, you should open it as maven project.
    For exmaple, if you are using IntelliJ, you just need to open the pom.xml file and it will be converted to mave project.

## Run The Project Using Docker Compose
    First, you need docker to be installed on your machine.
    Run this command inside the project folder:
    docker-compose up --build
    This command will run the project on a selenium grid container as a remote webdriver.

## Report File
    For a maven or IDE run, the report html file will be in this path: vicarius-automation-homework/target/surefire-reports/html/extent_reports_index.html
    For docker compose run, the report html file will be in this path: vicarius-automation-homework/local-surefire-reports/html/extent_reports_index.html

## Note
    Sometimes, signin test may fail because of the I'm not a robot popup.
