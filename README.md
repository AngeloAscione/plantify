# 🌿 Plantify – E-Commerce Platform for Plants

**Plantify** is a full-stack e-commerce platform designed to facilitate the buying and selling of plants. Developed using Java, this application provides a seamless experience for both customers and administrators, ensuring efficient management of plant inventories and orders.

---

## 📦 Features

- **User Authentication**: Secure registration and login functionalities for users.
- **Product Management**: Add, update, and delete plant listings with detailed descriptions and images.
- **Shopping Cart**: Users can add plants to their cart and manage quantities before checkout.
- **Order Processing**: Streamlined order placement and tracking system.
- **Admin Dashboard**: Administrative interface for managing products, orders, and users.
- **Responsive Design**: Optimized for various devices to ensure a consistent user experience.

---

## 🛠️ Technologies Used

- **Backend**:
  - Java 17
  - Tomcat
  - Hibernate
  - MySQL
- **Frontend**:
  - Thymeleaf
  - HTML5, CSS3
  - JavaScript
- **Build Tool**:
  - Maven

---

## 🚀 Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6+
- MySQL Server

### Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/AngeloAscione/plantify.git
   cd plantify
   ```

2. **Configure the database**:
   - Create a MySQL database named `plantify`.
   - Update the `application.properties` file with your MySQL credentials:
     ```
     spring.datasource.url=jdbc:mysql://localhost:3306/plantify
     spring.datasource.username=your_username
     spring.datasource.password=your_password
     ```

3. **Build and run the application**:
   ```bash
   ./mvnw spring-boot:run
   ```

4. **Access the application**:
   - User Interface: `http://localhost:8080/`
   - Admin Dashboard: `http://localhost:8080/admin`

---

## 🧪 Testing

- **Unit Tests**: Implemented using JUnit 5.
- **Integration Tests**: Ensuring the proper interaction between different components.
- **Manual Testing**: Comprehensive manual testing for UI and user experience.

---

## 📄 License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

## 👥 Contributors

- [Angelo Ascione](https://github.com/AngeloAscione)
- [FedericaG03](https://github.com/FedericaG03)

---

Feel free to contribute to this project by submitting issues or pull requests.
