# 👤 User Management Service

This service is responsible for handling user registration, login, authentication (JWT), and profile management. Built using Java Spring Boot and PostgreSQL, with Redis for caching.

---

## 🚀 Features

- User registration
- User login with JWT token generation
- Token verification
- Profile management (get/update current user)
- CRUD user data (admin-only)
- Caching with Redis

---

## 🛠️ Tech Stack

- ☕ Java 17+
- 🌱 Spring Boot
- 🐘 PostgreSQL
- 🔐 JWT (JSON Web Token)
- 🧊 Redis
- 🐳 Docker

---

## 📘 API Endpoints (Overview)

| Method | Endpoint             | Description                 | Auth |
|--------|----------------------|-----------------------------|------|
| POST   | `/api/auth/register` | Register a new user         | ❌   |
| POST   | `/api/auth/login`    | Login and get JWT token     | ❌   |
| GET    | `/api/users/:id`     | Get current user profile    | ✅   |
| PUT    | `/api/users`         | Update current user profile | ✅   |
| GET    | `/api/users`         | Get all users (admin only)  | ✅   |

---

## ▶️ Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/username/user-service-java.git
cd user-service-java
```

### 2. Run with Docker
```bash
docker build -t user-service .
docker run -p 8080:8080 --env-file .env user-service
```

---

## ⚙️ Environment Variables
Create a .env file or set the following variables manually:
```env
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/users_db
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=postgres
JWT_SECRET=your_jwt_secret
REDIS_HOST=localhost
REDIS_PORT=6379
```

---

## 🧪 Running Tests
```bash
./mvnw test
```

---

## 📝 Notes
- Make sure PostgreSQL and Redis are running.
- JWT token should be included in the Authorization header as:
`Authorization: Bearer <your_token_here>`