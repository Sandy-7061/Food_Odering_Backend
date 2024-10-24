# Food Ordering Application Backend

Welcome to the **Food Ordering Application Backend**, a robust and secure backend solution built using **Spring Boot**. This application is designed to manage food ordering systems with features for user authentication, restaurant management, food management, and order processing. With JWT-based authentication and MySQL integration, this system provides secure and efficient access to essential functionalities.

## Table of Contents

- [Features](#features)
- [Prerequisites](#prerequisites)
- [Setup Instructions](#setup-instructions)
  - [Clone the Repository](#1-clone-the-repository)
  - [MySQL Database Setup](#2-mysql-database-setup)
  - [Running the Application](#3-running-the-application)
  - [Postman Collection](#4-postman-collection)
  - [Application Properties](#5-application-properties)
- [API Overview](#api-overview)
  - [User Authentication](#user-authentication)
  - [Category Management](#category-management)
  - [Restaurant Management](#restaurant-management)
  - [Food Management](#food-management)
  - [Order Management](#order-management)
- [JWT Authentication](#jwt-authentication)
- [Troubleshooting](#troubleshooting)
- [Contact](#contact)

## Features

- **User Authentication**: Secure user registration and login processes using JWT.
- **Role-Based Access Control**: Distinct roles for admins, restaurant owners, and regular users to control access to various functionalities.
- **Restaurant Management**: APIs for adding, retrieving, and updating restaurant details.
- **Food Management**: Features for creating, searching, and filtering food items based on various criteria.
- **Order Management**: Users can place orders, and admins can track and manage orders efficiently.
- **Category Management**: Manage food categories specific to each restaurant.
- **Favorites**: Users can favorite restaurants for quick access later.
- **Robust Security**: All endpoints are secured with JWT token-based authentication to ensure data protection.

## Prerequisites

Before you begin, ensure you have the following installed:

- **Java Development Kit (JDK)** (version 17 or higher)
- **Maven** (for project management and build)
- **MySQL Database** (version 8.0 or higher)
- **Postman** (for API testing)

## Setup Instructions

### 1. Clone the Repository
Clone the project repository to your local machine:
```bash
git clone https://github.com/Sandy-7061/Food_Odering_Backend.git
cd Food_Odering_Backend
```

### 2. MySQL Database Setup
- **Create Database**: Open your MySQL client (e.g., MySQL Workbench) and create a new database:
```sql
CREATE DATABASE food_ordering_application;
```

### 3. Running the Application
To run the application, make sure you are in the project directory and use Maven to build and start the application:
```bash
mvn clean install
mvn spring-boot:run
```
The server will start running on **http://localhost:8080**.

### 4. Postman Collection
To test the APIs easily, import the provided Postman collection from the link below:
[Postman Collection](https://github.com/yourusername/food-ordering-backend-postman)  


### 5. Application Properties
Ensure you have the following configurations in your `application.properties` file to control logging levels and other settings:
```properties
spring.application.name=Food_Odering_Application_Backend
spring.datasource.url=jdbc:mysql://localhost:3306/food_ordering_application
spring.datasource.username=root
spring.datasource.password=YourMySqlPassword  # Replace with your actual MySQL password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Set logging levels for debugging
logging.level.org.springframework.web=DEBUG
logging.level.org.springframework.security=DEBUG
logging.level.sandeep.securityinspringboot=DEBUG
```

## API Overview

### **User Authentication**
- **POST** `/api/auth/signup` – Registers a new user.  
  *Request Body*: `{"email": "user@example.com", "password": "yourpassword", "role": "USER"}`

- **POST** `/api/auth/signin` – Authenticates the user and returns a JWT token.  
  *Request Body*: `{"email": "user@example.com", "password": "yourpassword"}`

### **Category Management**
- **POST** `/api/admin/category` – Creates a new category (admin access required).  
  *Request Body*: `{"name": "CategoryName", "restaurantId": 1}`

- **GET** `/api/category/restaurant` – Retrieves categories for a specific restaurant.  
  *Query Parameter*: `restaurantId`

### **Restaurant Management**
- **GET** `/api/restaurants/search?keyword={keyword}` – Searches restaurants by name or other keywords.

- **GET** `/api/restaurants` – Retrieves a list of all restaurants.

- **GET** `/api/restaurants/{id}` – Retrieves details of a specific restaurant by ID.

- **PUT** `/api/restaurants/{id}/add-favorites` – Adds a restaurant to user favorites.  
  *Path Variable*: `id` (Restaurant ID)

### **Food Management**
- **GET** `/api/food/search?name={foodName}` – Searches food items by name.

- **GET** `/api/food/restaurant/{restaurantId}` – Retrieves all food items associated with a specific restaurant.  
  *Path Variable*: `restaurantId`

### **Order Management**
- **PUT** `/api/order` – Creates a new order for the user.  
  *Request Body*: `{"restaurantId": 1, "foodItems": [{"foodId": 1, "quantity": 2}], "userId": 1}`

- **GET** `/api/order/user` – Retrieves the order history for the logged-in user.

## JWT Authentication
To access secured endpoints, include a JWT token in your request headers as follows:

```http
Authorization: Bearer <your-jwt-token>
```
You can obtain the token by signing in through `/api/auth/signin`.

## Troubleshooting
- **Common Issues**:
  - Ensure your MySQL server is running and accessible.
  - Double-check the database connection settings in `application.properties`.
  - If you encounter token-related issues, verify your JWT configuration and expiration times.

- **Logs**: Check the application logs for detailed error messages if any issues arise during runtime.

## Contact
If you have any questions, need support, or wish to provide feedback, feel free to reach out:

- **Name**: Sandeep Kushwaha
- **Email**: [sandeepkush880@gmail.com](mailto:sandeepkush880@gmail.com)
- **Phone**: +91 7024520740

#Feel Free to Contact Me
```
