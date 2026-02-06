# Job Hiring Backend

A **robust, secure backend system** built with **Spring Boot 4.0.1** and **Java 21**, designed to manage the **end-to-end recruitment process**. The system supports **role-based actions** for **Admins, Recruiters, and Candidates**, following modern backend and security best practices.

---

## 🚀 Core Features

### 🔐 Secure Authentication

* Stateless **JWT-based authentication** (login & logout)
* Integrated with **Spring Security**

### 🧾 Token Revocation

* Secure logout using a **revoked token repository**
* Prevents reuse of invalidated JWTs

### 📌 Job Post Management

* Full **CRUD operations** for job listings
* **Bulk job post upload** support
* Each job post identified by a unique numeric sequence

### 📊 Application Tracking

* Candidates can apply for jobs
* Tracks application status:

  * `APPLIED`
  * `SHORTLISTED`
  * `REJECTED`

### 👤 User Management

* User registration
* Fetch user details
* Update authenticated user profile

### 🔢 Automated Sequencing

* Custom **MongoDB sequence generator**
* Generates unique numeric IDs (e.g., Job Post Numbers)

---

## 🛠 Tech Stack

* **Framework:** Spring Boot 4.0.1
* **Language:** Java 21
* **Database:** MongoDB
* **Security:** Spring Security, JJWT (io.jsonwebtoken)
* **API Documentation:** SpringDoc OpenAPI (Swagger UI)
* **Containerization:** Docker & Docker Compose
* **Build Tool:** Maven / Maven Wrapper

---

## 📋 API Endpoints

### 🔑 Authentication

| Method | Endpoint  | Description                        |
| ------ | --------- | ---------------------------------- |
| POST   | `/login`  | Authenticates user and returns JWT |
| POST   | `/logout` | Revokes the current JWT            |

---

### 💼 Job Posts

| Method | Endpoint         | Description                           |
| ------ | ---------------- | ------------------------------------- |
| GET    | `/post`          | Retrieves paginated list of job posts |
| POST   | `/post`          | Creates a single job post             |
| POST   | `/post/bulk`     | Creates multiple job posts            |
| DELETE | `/post/{postNo}` | Deletes job post by sequence number   |

---

### 📄 Job Applications

| Method | Endpoint            | Description                 |
| ------ | ------------------- | --------------------------- |
| POST   | `/application/{id}` | Candidate applies for a job |

---

### 👥 User Management

| Method | Endpoint         | Description                        |
| ------ | ---------------- | ---------------------------------- |
| POST   | `/user/register` | Registers a new user               |
| GET    | `/user/{id}`     | Fetches user details               |
| PUT    | `/user/update`   | Updates authenticated user profile |

---

## ⚙️ Setup and Installation

### ✅ Prerequisites

* Java 21
* Maven (or use the provided `mvnw`)
* MongoDB (Local or MongoDB Atlas)
* Docker & Docker Compose (optional)

---

### 🔧 Configuration

Update the following file:

```
src/main/resources/application.properties
```

Example configuration:

```properties
spring.data.mongodb.uri=your_mongodb_connection_string
spring.data.mongodb.database=hiringApplication
```

---

### ▶️ Run Locally (Without Docker)

```bash
./mvnw clean spring-boot:run
```

Application runs at:

```
http://localhost:8081
```

---

### 🐳 Run with Docker

#### 1️⃣ Build the application

```bash
./mvnw clean package
```

#### 2️⃣ Start containers

```bash
docker-compose up
```

Application will be available at:

```
http://localhost:8081
```

---

## 📘 API Documentation

Swagger UI is available at:

```
http://localhost:8081/swagger-ui.html
```

---

## 🔮 Future Enhancements

* Role-based dashboards
* Resume upload & parsing
* Pagination & advanced filtering
* Email notifications
* CI/CD pipeline integration

---

## 👨‍💻 Author

**Ashish**
Backend Developer | Java & Spring Boot

🔗 GitHub: [https://github.com/Ash8389](https://github.com/Ash8389)

---

## ⭐ Why This Project Stands Out

* Uses **latest Java & Spring Boot versions**
* Secure JWT + token revocation implementation
* MongoDB sequence generator (real-world pattern)
* Clean, scalable, production-style architecture

⭐ If you find this project useful, don’t forget to **star the repository**!
