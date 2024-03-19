# Users Microservice

The Users Microservice is responsible for managing user-related functionalities within the car rental application. It provides endpoints for user registration, authentication, user profile management, and user-related operations.

## Table of Contents
* Endpoints
* Technologies Used
* Getting Started
* Usage
* Testing

## Endpoints
* POST /api/users/register: Registers a new user in the system.
Login
* POST /api/users/login: Login a user.
User Management
* PUT /api/users/{userId}: Updates user profile.
* DELETE /api/users/{userId}: Deletes a user.
* GET /api/users/{userId}: Retrieves user information by ID.
* GET /api/users/all: Retrieves all users.

## Technologies Used
* Java
* Spring Boot
* MongoDB
* Spring Data MongoDB
* Spring Security
* Maven

## Getting Started

To get started with the Users Microservice, follow these steps:

* Ensure you have Java Development Kit (JDK) installed on your machine.
* Install MongoDB and ensure it is running.
* Clone this repository to your local machine.
* Navigate to the project directory.
* Build the project using Maven: mvn clean install.
* Run the application: java -jar target/userservice.jar.
