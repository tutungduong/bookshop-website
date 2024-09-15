
<a name="readme-top"></a>

<div align="center">
  <img src="https://upload.wikimedia.org/wikipedia/commons/7/79/Spring_Boot.svg" alt="Spring Boot Logo" width="100" height="auto" />
  <h3><b>Book Store</b></h3>
  <p>An online platform to explore, discover, and purchase your favorite books.</p>
</div>

---

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
- [Technologies Used](#technologies-used)
- [Modules](#modules)
- [Features](#features)
- [Installation & Setup](#installation--setup)
- [Docker Setup](#docker-setup-optional)
- [Contributing](#contributing)
- [License](#license)

---

## Introduction

The **Book Store** project is an online platform for book enthusiasts, offering an intuitive way to browse, discover, and purchase books across various categories. The platform features personalized recommendations, a secure shopping cart system, and streamlined checkout processes. Built using modern technologies, this project ensures a responsive, scalable, and secure service for users.

## Class Diagram

The class diagram below represents the core entities of the **Book Store** system, including `User`, `Product`, `Order`, `Cart`, and `Payment`.

<p align="center">
  <img src="https://github.com/user-attachments/assets/3e76e7f8-2683-401c-ba19-9028ced82d47" alt="Class Diagram" width="600" />
  <br>
  <em>Class Diagram</em>
</p>

| Authentication | Product | Cart |
| -------------- | ------- | ---- |
| <img src="https://github.com/user-attachments/assets/1be0b1dc-9979-494b-b2d1-dad9e5236245" alt="Authentication Class Diagram" width="200" /> | <img src="https://github.com/user-attachments/assets/fd1aee9a-e58e-44a8-b005-14b5d3bb50bc" alt="Product Class Diagram" width="200" /> | <img src="https://github.com/user-attachments/assets/a0988dd5-6ff7-43b2-8c9b-e19ebd765b86" alt="Cart Class Diagram" width="200" /> |

| Order          | Cashbook | General |
| -------------- | -------- | -------------- |
| <img src="https://github.com/user-attachments/assets/54fe29e8-7799-4367-8e26-06fa1d3237fc" alt="Order Class Diagram" width="200" /> | <img src="https://github.com/user-attachments/assets/e5d5f11a-e6b4-4f21-86cd-f9853bcb71b7" alt="Cashbook Class Diagram" width="200" /> | <img src="https://github.com/user-attachments/assets/6a31e449-cfe6-460a-b14a-7ba2c8b3454e" alt="General Class Diagram" width="200" /> |

## Technologies Used

The project uses a variety of modern technologies to ensure scalability, efficiency, and security:

- **Backend**: Spring Boot, Spring Data JPA, Spring Security
- **Frontend**: Bootstrap, CSS, JavaScript
- **Database**: MySQL
- **Build Tool**: Maven
<!-- - **Others**: Lombok, Docker for containerization -->

## Modules

1. **User Management**: Handles registration, login, and role-based authorization (JWT).
2. **Book Management**: Admins can manage books (CRUD), while users can browse and search books.
3. **Shopping Cart**: Users can add/remove items to/from their cart and checkout.
4. **Order Management**: Processes orders, payments, and maintains order history.
5. **Wishlist**: Users can add books to their wishlist for future purchases.

## Installation & Setup

### Prerequisites

- Java 17+
- MySQL
- Maven

### Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/tutungduong/bookshop-website.git
   cd bookshop-website
   ```

2. **Set up MySQL**:
   - Create a new database named `bookshop`.
   - Update your MySQL credentials in `src/main/resources/application.properties`.

3. **Build and run the application**:
   ```bash
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```

4. **Access the application**:
   Open [http://localhost:8085/api](http://localhost:8085/api) in your browser.

## Docker Setup (Optional)

To run the application using Docker:

1. **Build and start the container**:
   ```bash
   docker-compose up --build
   ```

2. **Access the application**:
   Open [http://localhost:8085/api](http://localhost:8085/api).

## Contributing

We welcome contributions! Here's how you can contribute:

1. Fork the repository.
2. Create a feature branch (`git checkout -b feature-name`).
3. Commit your changes (`git commit -m 'Add feature'`).
4. Push to the branch (`git push origin feature-name`).
5. Open a pull request.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---
