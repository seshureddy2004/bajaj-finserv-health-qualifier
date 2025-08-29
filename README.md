# bajaj-finserv-health-qualifier
Bajaj Finserv Health Qualifier 1 — Spring Boot
Project Overview
This Spring Boot application automates the solution workflow required for the Bajaj Finserv Health Qualifier 1 coding challenge.
On application startup, it sends a POST request to register a webhook, solves a given SQL problem, and submits the final SQL query to the webhook URL, using a JWT token provided in the response.

Features
Fully automatic workflow (no manual controller/endpoint triggers)

Uses RestTemplate for HTTP requests

Reads endpoint and token on startup, solves and sends the SQL query

Requirements
Java 17 or newer

Maven

How the Workflow Runs
The application's logic is executed as soon as the app starts using CommandLineRunner.

No REST endpoints or manual triggers are used.

API calls and solution submission are handled automatically without user intervention.

How to Build & Run
Build the project with Maven:

text
mvn clean package
Run the generated JAR file (replace with the actual JAR name, if different):

text
java -jar target/demo-0.0.1-SNAPSHOT.jar
What the App Does
Sends a POST request to register a webhook and receive an access token.

Determines the SQL question based on registration number (regNo ends in "47" = question 1).

Creates the solution SQL query for the task.

Submits the SQL query automatically to the provided webhook URL using the access token as a JWT bearer token.

Output
The app prints the webhook responses to the console.

All logic happens automatically--no REST endpoints are exposed.

Author
Name: guvva

Email: your@email.com
