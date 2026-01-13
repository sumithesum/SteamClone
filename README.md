# SteamClone – Spring Boot Backend API

SteamClone is a RESTful backend application developed using Spring Boot and MySQL, designed to simulate the core functionality of a digital game distribution platform similar to Steam.

The application implements a layered architecture (Controller – Service – Repository – Model) and supports complete business workflows such as game publishing, user management, social interactions and transactional game purchases.

# Main Features

- Manage Players and Developers

- Publish and manage Games

- Friends system with bidirectional relationships

- Game purchase workflow with transactional money transfer between players and developers

- Library system tracking owned games and playtime

- Messaging system between players

- Review & rating system with votes in range [-1, 1] and SQL aggregation using AVG

- Automatic cleanup using foreign key cascading rules

# Technical Highlights

- JDBC persistence with JdbcTemplate

- Transactional business logic using @Transactional

- Validation with Jakarta Bean Validation

- Fully documented API using Swagger / OpenAPI

- Demonstration and testing using Postman

- Complete unit testing using JUnit, Mockito and MockMvc

# Architecture

- The project follows a clean separation of concerns:

- Controller – Handles HTTP requests and responses

- Service – Implements business logic and transactions

- Repository – Manages database interaction

- Model – Defines application entities

- This project was developed as part of a university assignment and represents a complete backend system with real-world design principles.

‎ 

# DataBase Diagram

![alt text](https://github.com/sumithesum/SteamClone/blob/master/Diagram%20DB.png "Logo Title Text 1")

# Start

(From the folder where is mvnw )

### Windows

#### mvnw spring-boot:run


###Linux

#### ./mvnw spring-boot:run
