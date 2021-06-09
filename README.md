# README #
This README documents how the webservice is created using a springboot framework and how to run it as a service using a configuration management tool ansible and displaying the content
as "BLOCKED" or "ALLOWED" based on the query string value in the URL

## Creating a small webservice using springboot framework ##

## Prerequisite ##
JAVA 8 and above

## Build Tool ##
Maven integrated as part of the project

## Command to generate the JAR file ##
JAR file is already built as part of this project and stored in bin folder. You can use the existing jar.

If you want to generate a new jar, please run the below command

./mvnw clean install

This generates a new jar file called "spring-boot-web-service-1.0-SNAPSHOT.jar" and this gets stored in the target folder of spring-boot-web-service.

## How to start the webservice ##
java -jar spring-boot-web-service-1.0-SNAPSHOT.jar

## Displaying content as BLOCKED if the URL contains a malware query string ##
For this assignment, webservice runs on port 8080 

e.g Hitting the below url displays the content as "BLOCKED" as it contains the malware query string we specified

http://localhost:8080//urlinfo/1/google.com:8080/search-the-internet.html?v=thevalue

NOTE: replace localhost by the your server ip/domain name

## Automating the webservice as a service using ansible##

Running the playbook.yml in ansible folder will create a webservice application as a service

