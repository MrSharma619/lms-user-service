# LearnUp - learning management system  

## Overview ##

The Learning Management System (LMS) is a web-based application designed to facilitate the educational process by providing an online platform for teachers and students. The application supports two main roles:

* Teacher: Teachers can post assignments, review student submissions, and accept or reject these submissions.

* Student: Students can view and submit assignments for evaluation by their teachers.
  
In addition, the application includes essential functionalities like user registration and login.

## Technologies Used ##

### Backend ###

* Java: Chosen for its robustness, performance, and wide adoption in enterprise-level applications.
  
* Spring Boot: Utilized for its powerful, flexible, and easy-to-use framework for building microservices and web applications. It simplifies the development process with its extensive ecosystem and strong support for RESTful APIs.

* JWT (JSON Web Tokens): Used for secure authentication. JWT allows stateless authentication by generating tokens that clients can use for subsequent requests without the need for server-side sessions.
  
### Database ###

Relational Database (PostgreSQL): Used for storing user information. Relational databases are preferred for their strong ACID compliance, which ensures data integrity and reliability.

## Features ##

* User Authentication: Secure login and registration functionality for both teachers and students using JWT token.

* Role Management: Different functionalities and interfaces for teachers and students.

* View profile part included as well.


## How to Run ##
* Clone the repository.
* Open the project in IntelliJ or any other IDE.
* Create a database **lms_user_service** in postgres.
* Create a .env file in **resources** folder and add configuration like this and update details accordingly  
  
*DB_DATABASE="lms_user_service"*  
*DB_USER=""*  
*DB_PASSWORD=""*  
  
* Run this microservice and make sure gateway is also up and running.
* Access the application through the provided URL.
  
```
http://localhost:6500
```


### Note ### 
If you face issue in connecting via gateway, try disabling firewall of your antivirus.

