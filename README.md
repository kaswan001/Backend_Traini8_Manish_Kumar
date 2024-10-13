# Training Center Registry

## Overview

The **Training Center Registry** is a Spring Boot application designed to manage training centers, allowing users to create, read, update, and delete training center records. This project uses PostgreSQL as the database and provides a RESTful API for easy interaction with the application. Swagger is integrated for API documentation.

## Table of Contents

- [Technologies Used](#technologies-used)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Database Setup](#database-setup)
- [Running the Application](#running-the-application)
- [API Documentation](#api-documentation)
- [Sample Data](#sample-data)
- [Testing the Application](#testing-the-application)
- [Contributing](#contributing)
- [License](#license)

## Technologies Used

- **Java**: Version 17
- **Spring Boot**: Version 3.x
- **PostgreSQL**: Database
- **Swagger**: API documentation
- **Maven**: Build and dependency management

## Prerequisites

Before you begin, ensure you have met the following requirements:

- **Java Development Kit (JDK) 17** installed on your machine.
- **Maven** installed for building and running the project.
- **PostgreSQL** installed and running on your local machine.
- An IDE such as **IntelliJ IDEA** or **Eclipse**.

## Installation

1. **Clone the Repository**:

   Open your terminal and run the following command to clone the project:

   ```bash
   git clone https://github.com/kaswan001/Backend_Traini8_Manish_Kumar.git
   ```

2. **Navigate to the Project Directory**:

   ```bash
   cd Backend_Traini8_Manish_Kumar
   ```

3. **Build the Project**:

   Run the following command to build the project using Maven:

   ```bash
   mvn clean install
   ```

## Configuration

1. **Database Configuration**:

   Open `src/main/resources/application.yml` or `src/main/resources/application.properties` (depending on your setup) and update the database connection details:

   ```yaml
   spring:
     datasource:
       url: jdbc:postgresql://localhost:5432/your_database_name
       username: your_database_username
       password: your_database_password
     jpa:
       hibernate:
         ddl-auto: update
       show-sql: true
   ```

   Replace `your_database_name`, `your_database_username`, and `your_database_password` with your PostgreSQL database details.

## Database Setup

1. **Create a Database**:

   Open your PostgreSQL client (e.g., pgAdmin) and create a new database:

   ```sql
   CREATE DATABASE your_database_name;
   ```

   Make sure to replace `your_database_name` with the same name used in the configuration.

## Running the Application

To run the application, use the following command:

```bash
mvn spring-boot:run
```

The application should start on `http://localhost:8080`.


## API Documentation

Swagger provides an interactive API documentation for your convenience. To view details, including validation information for the API requests:

1. Open your web browser and navigate to:

   ```
   http://localhost:8080/swagger-ui.html

   ```

2. In the Swagger UI, you will see a list of all available endpoints. 

3. Scroll down to the **Schemas** section at the bottom of the page. Here, you can expand the following schemas to get detailed information about each:

   - **AddressRequestDTO**
   - **TrainingCenterRequestDTO**
   - **AddressResponseDTO**
   - **TrainingCenterResponseDTO**

4. By expanding each schema, you can view the fields along with their validation rules, data types, and example values, which will guide you on how to structure your API requests properly.

This documentation allows you to understand the requirements for each API request and response.


## Sample Data

To test the API, you can use the following sample data for your requests. Insert the sample training center data provided in the Add sample data section before testing.

```
[
    {
        "center_name": "Bangalore IT Training Center",
        "center_code": "BLR123456789",
        "student_capacity": 100,
        "courses_offered": [
            "Java Development",
            "Cloud Computing",
            "Data Structures"
        ],
        "contact_email": "contact@blrittraining.com",
        "contact_phone": "+919876543210",
        "address": {
            "detailed_address": "1st Floor, Brigade Road",
            "city": "Bangalore",
            "state": "Karnataka",
            "pincode": "560001"
        }
    },
    {
        "center_name": "Pune Software Hub",
        "center_code": "PUN123456789",
        "student_capacity": 80,
        "courses_offered": [
            "Full Stack Development",
            "Machine Learning",
            "Cybersecurity"
        ],
        "contact_email": "infopune@softwarehub.com",
        "contact_phone": "+918765432109",
        "address": {
            "detailed_address": "Level 3, Hinjewadi IT Park",
            "city": "Pune",
            "state": "Maharashtra",
            "pincode": "411057"
        }
    },
    {
        "center_name": "Hyderabad Tech Academy",
        "center_code": "HYD123456789",
        "student_capacity": 70,
        "courses_offered": [
            "Python Programming",
            "Artificial Intelligence",
            "Blockchain"
        ],
        "contact_email": "support@hydtechacademy.com",
        "contact_phone": "+919234567890",
        "address": {
            "detailed_address": "Plot 9, Gachibowli",
            "city": "Hyderabad",
            "state": "Telangana",
            "pincode": "500032"
        }
    },
    {
        "center_name": "Chennai Coding Institute",
        "center_code": "CHE123456789",
        "student_capacity": 90,
        "courses_offered": [
            "JavaScript Development",
            "React and Node.js",
            "Database Management"
        ],
        "contact_email": "contact@chennaicoding.com",
        "contact_phone": "+917654321098",
        "address": {
            "detailed_address": "2nd Avenue, Anna Nagar",
            "city": "Chennai",
            "state": "Tamil Nadu",
            "pincode": "600040"
        }
    },
    {
        "center_name": "Mumbai Data Science Hub",
        "center_code": "MUM123456789",
        "student_capacity": 120,
        "courses_offered": [
            "Data Science",
            "Data Analytics",
            "Big Data"
        ],
        "contact_email": "info@mumdatascience.com",
        "contact_phone": "+918123456789",
        "address": {
            "detailed_address": "4th Floor, Andheri East",
            "city": "Mumbai",
            "state": "Maharashtra",
            "pincode": "400069"
        }
    },
    {
        "center_name": "Kolkata Software Academy",
        "center_code": "KOL123456789",
        "student_capacity": 60,
        "courses_offered": [
            "Web Development",
            "Java Basics",
            "Python Advanced"
        ],
        "contact_email": "support@kolsoftacademy.com",
        "contact_phone": "+919098765432",
        "address": {
            "detailed_address": "Salt Lake Sector V",
            "city": "Kolkata",
            "state": "West Bengal",
            "pincode": "700091"
        }
    },
    {
        "center_name": "Jaipur Coding Academy",
        "center_code": "JAI123456789",
        "student_capacity": 75,
        "courses_offered": [
            "PHP Development",
            "Laravel Framework",
            "Java Spring Boot"
        ],
        "contact_email": "hello@jaipurcodingacademy.com",
        "contact_phone": "+918765409876",
        "address": {
            "detailed_address": "Vaishali Nagar",
            "city": "Jaipur",
            "state": "Rajasthan",
            "pincode": "302021"
        }
    },
    {
        "center_name": "Ahmedabad IT Hub",
        "center_code": "AHM123456789",
        "student_capacity": 85,
        "courses_offered": [
            "C++ Programming",
            "Data Structures",
            "Cloud Fundamentals"
        ],
        "contact_email": "info@ahmedabadithub.com",
        "contact_phone": "+917654320987",
        "address": {
            "detailed_address": "Prahlad Nagar",
            "city": "Ahmedabad",
            "state": "Gujarat",
            "pincode": "380015"
        }
    },
    {
        "center_name": "Lucknow Training Center",
        "center_code": "LKO123456789",
        "student_capacity": 65,
        "courses_offered": [
            "Android Development",
            "Kotlin Basics",
            "Flutter"
        ],
        "contact_email": "training@lucknowcenter.com",
        "contact_phone": "+919876543210",
        "address": {
            "detailed_address": "Hazratganj",
            "city": "Lucknow",
            "state": "Uttar Pradesh",
            "pincode": "226001"
        }
    },
    {
        "center_name": "Indore Development Academy",
        "center_code": "IND123456789",
        "student_capacity": 50,
        "courses_offered": [
            "iOS Development",
            "Swift Programming",
            "React Native"
        ],
        "contact_email": "info@indoreacademy.com",
        "contact_phone": "+918907654321",
        "address": {
            "detailed_address": "Vijay Nagar",
            "city": "Indore",
            "state": "Madhya Pradesh",
            "pincode": "452010"
        }
    }
]

```

## Testing the Application

You can test the RESTful API using tools like **Postman** or directly from the Swagger UI.

### Example Requests

- **Create multiple Training Center**: Send a POST request to `/api/v1/training-centers/save-batch` with a JSON body.
- **Create a Training Center**: Send a POST request to `/api/v1/training-centers/save` with a JSON body.
- **Get All Training Centers**: Send a GET request to `/api/v1/training-centers/getAll`.
- **Filter Training Centers**: Send a GET request to `/api/v1/training-centers/filter` with query parameters.

## Contributing

Contributions are welcome! If you have suggestions for improvements or enhancements, feel free to create a pull request or submit an issue.

## License
