
<a name="readme-top"></a>

<div align="center">
  <img src="https://upload.wikimedia.org/wikipedia/commons/7/79/Spring_Boot.svg" alt="logo" width="100" height="auto" />
  <h3><b>Book Store</b></h3>
</div>

<!-- Badges -->
![GitHub stars](https://img.shields.io/github/stars/tutungduong/bookshop-website?style=social)
![GitHub forks](https://img.shields.io/github/forks/tutungduong/bookshop-website?style=social)
![GitHub pull requests](https://img.shields.io/github/issues-pr/tutungduong/bookshop-website)
![GitHub last commit](https://img.shields.io/github/last-commit/tutungduong/bookshop-website)
![GitHub contributors](https://img.shields.io/github/contributors/tutungduong/bookshop-website?color=2b9348)
![GitHub license](https://img.shields.io/github/license/tutungduong/bookshop-website?color=2b9348)
[![LinkedIn Follow](https://img.shields.io/badge/-LinkedIn-blue?style=flat-square&logo=Linkedin&logoColor=white)](https://www.linkedin.com/in/tutungduong/)

---

## Table of Contents

- [Introduction](#introduction)
- [Class Diagram](#class-diagram)
- [Used Technologies](#used-technologies)
- [Modules](#modules)
- [Features](#features)
- [Installation & Setup](#installation--setup)
<!-- - [Contributing](#contributing) -->
- [License](#license)

---

## Introduction

The **Book Store** is an online platform for book lovers to browse, discover, and purchase books across a wide range of categories. It offers a streamlined, secure, and user-friendly experience, complete with personalized recommendations, a shopping cart system, and checkout functionality. This project leverages modern web development technologies to provide a responsive and scalable service for users.

## Class Diagram

Below is the Class Diagram of the **Book Store** system, showing the relationships between entities like `User`, `Product`, `Order`, `Cart`, and `Payment`.

<p align="center" style="background-color: white;">
  <img src="https://github.com/user-attachments/assets/3e76e7f8-2683-401c-ba19-9028ced82d47" alt="Use Case Diagram" width="600" />
  <br>
  <em>Class Diagram</em>
</p>

| (1)                                                                                           | (2)                                                                                           | (3)                                                                                           |
| --------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------- |
| <img src="https://github.com/user-attachments/assets/1be0b1dc-9979-494b-b2d1-dad9e5236245" alt="Class Diagram – Authentication" width="200" /> | <img src="https://github.com/user-attachments/assets/fd1aee9a-e58e-44a8-b005-14b5d3bb50bc" alt="Class Diagram – Product" width="200" /> | <img src="https://github.com/user-attachments/assets/a0988dd5-6ff7-43b2-8c9b-e19ebd765b86" alt="Class Diagram – Cart" width="200" /> |
| <img src="https://github.com/user-attachments/assets/54fe29e8-7799-4367-8e26-06fa1d3237fc" alt="Class Diagram – Order" width="200" /> | <img src="https://github.com/user-attachments/assets/e5d5f11a-e6b4-4f21-86cd-f9853bcb71b7" alt="Class Diagram – Cashbook" width="200" /> | <img src="https://github.com/user-attachments/assets/6a31e449-cfe6-460a-b14a-7ba2c8b3454e" alt="Class Diagram – Miscellaneous" width="200" /> |

## Used Technologies

This project utilizes a variety of modern technologies to ensure efficiency, security, and scalability:

- **Backend**: Spring Boot, Spring Data JPA, Spring Security
- **Frontend**: Bootstrap, CSS, JavaScript (will update)
- **Database**: MySQL
- **Build Tool**: Maven
<!-- - **Others**: Lombok for reducing boilerplate code, Docker for containerization -->

## Modules

The project is organized into several key modules:

1. **User Management**: Handles user registration, authentication, and authorization (JWT-based).
2. **Book Management**: Allows admin users to add, edit, and delete books, while regular users can browse and search.
3. **Shopping Cart**: Users can add/remove books from their cart and proceed to checkout.
4. **Order Management**: Manages order placement, payment processing, and order history.
5. **Wishes**: Allows users to save books to a wishlist for future consideration or purchase.


## Installation & Setup

### Prerequisites

Before you begin, ensure you have met the following requirements:

- Java 17+
- MySQL
- Maven

### Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/tutungduong/bookshop-website.git
   cd bookshop-website
   ```

<!-- 2. **Set up MySQL:**
   - Create a new database named `bookshop`.
   - Update your database credentials in `src/main/resources/application.properties`. -->

2. **Build and run the application:**
   ```bash
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```

3. **Access the application:**
   Open your browser and navigate to [http://localhost:8085/api](http://localhost:8085/api).
<!-- 
### Docker Setup (Optional)

If you prefer to run the application in a Docker container:

1. **Build the Docker image:**
   ```bash
   docker-compose up --build
   ```

2. **Access the application:**
   Go to [http://localhost:8080](http://localhost:8080). -->

<!-- ## Contributing

We welcome contributions! To contribute to this project, follow these steps:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-name`).
3. Commit your changes (`git commit -m 'Add new feature'`).
4. Push to the branch (`git push origin feature-name`).
5. Open a pull request. -->

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
