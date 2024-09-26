
<a name="readme-top"></a>

<div align="center">
  <img src="https://upload.wikimedia.org/wikipedia/commons/7/79/Spring_Boot.svg" alt="Spring Boot Logo" width="100" height="auto" />
  <h3><b>Book Store API</b></h3>
  <p>This project focuses on building a RESTful API that serves as the backend for a bookstore application. The API provides endpoints to manage books, categories, customers, orders, and payments, supporting key functionalities such as searching, filtering, and managing inventory. It is designed to be scalable, secure, and easy to integrate with any frontend client.</p>
</div>

---

<!-- Badges -->
<div align="center">
  
![GitHub stars](https://img.shields.io/github/stars/tutungduong/bookshop-website?style=social)
![GitHub forks](https://img.shields.io/github/forks/tutungduong/bookshop-website?style=social)
![GitHub pull requests](https://img.shields.io/github/issues-pr/tutungduong/bookshop-website)
![GitHub last commit](https://img.shields.io/github/last-commit/tutungduong/bookshop-website)
![GitHub contributors](https://img.shields.io/github/contributors/tutungduong/bookshop-website?color=2b9348)
![GitHub license](https://img.shields.io/github/license/tutungduong/bookshop-website?color=2b9348)
[![LinkedIn Follow](https://img.shields.io/badge/-LinkedIn-blue?style=flat-square&logo=Linkedin&logoColor=white)](https://www.linkedin.com/in/tutungduong/)

</div>

---

## Table of Contents

- [Introduction](#introduction)
- [Technologies Used](#technologies-used)
- [Modules](#modules)
- [Installation & Setup](#installation--setup)
- [Docker Setup](#docker-setup-optional)
- [Contributing](#contributing)
- [License](#license)

---

## Introduction

The **Bookstore RESTful API** is a backend project designed for an online bookstore management application. This API provides essential features for managing book categories, customers, orders, payments, and more. Built with a RESTful architecture, the API ensures scalability, security, and ease of integration with various frontend applications. This project simplifies the development of online book selling applications, supporting efficient searching, filtering, inventory management, and payment processing.

## Technologies Used

The project uses a variety of modern technologies to ensure scalability, efficiency, and security:

- **Backend**: Java, Spring(Boot, MVC, JPA, Security, Mail).
- **Frontend**: ReactJS, Bootstrap (planned implementation).
- **Database**: MySQL(with Hibernate).
- **Build Tool**: Maven, IntelliJ IDEA, Docker (option).
<!-- - **Others**: Lombok, Docker for containerization -->

## Modules

### **Admin Module**

The Admin Module offers a comprehensive set of tools for managing the bookstore's operations efficiently:

- **Order Management**: View, update, and delete orders to maintain accurate records, ensuring smooth processing and fulfillment.
- **Product Management**: Create, update, and delete product listings and variants, while effectively managing inventory levels.
- **Category Management**: Organize and manage product categories to enhance navigation and improve the overall user shopping experience.
- **Promotion Management**: Create and manage promotional campaigns, discounts, and special offers to drive sales.
- **Review Management**: Monitor and manage product reviews, including approval, deletion, and responses to customer feedback.
- **Account Management**: Handle user account details, permissions, and roles, ensuring secure and efficient user management.
- **Order Cancellation Reasons Management**: Define, manage, and update reasons for order cancellations, ensuring clear communication.

### **User Module**

The User Module enhances the user experience with essential features for smooth interaction and account management:

- **Authentication**: 
  - **Register & Login**: Secure user registration and login.
  - **Forgot Password**: Account recovery with secure password reset.
- **Order Management**:
  - View order history, place new orders, and cancel them as needed.
- **Cart Management**: 
  - Add, update, or remove products in the shopping cart.
- **Wishlist Management**: Maintain a list of favorite products for future purchases.
- **Product & Review Viewing**: Browse products, view reviews, and explore categories.
- **User Information Management**: Update personal details such as phone number, email, and password.
- **Payment Options**: Choose between CASH or VNPAY for payment.

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

2. **Set up the `application.properties` file**:

   Create the file `src/main/resources/application.properties` and add the following configurations:

   ```properties
   server.port = 8085

   # Springdoc
   springdoc.show-actuator = false
   springdoc.swagger-ui.path=/api

   # Database Configuration
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
   spring.datasource.username=your_username
   spring.datasource.password=your_password

   # VNPAY Configuration
   # Refer to the VNPAY documentation for detailed setup: 
   # https://sandbox.vnpayment.vn/apis/docs/thanh-toan-pay/pay.html
   vnpay.baseUrl=https://sandbox.vnpayment.vn/paymentv2/vpcpay.html
   vnpay.HashSecret=your_secret_key
   vnpay.TmnCode=your_terminal_code
   vnpay.Version=2.1.0
   vnpay.Command=pay
   vnpay.orderType=order-type
   ```

3. **Build and run the application**:
   ```bash
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```

4. **Access the application**:
   Open [http://localhost:8085/api](http://localhost:8085/api) in your browser.

## Docker Setup (Optional)

To run the application using Docker:

1. **Clone the repository**:
   ```bash
   git clone https://github.com/tutungduong/bookshop-website.git
   cd bookshop-website
   ```

2. **Build and start the container**:
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
