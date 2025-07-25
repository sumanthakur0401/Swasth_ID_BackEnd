# 🏥 Swasth_ID_BackEnd

A Spring Boot-based backend system for managing electronic health records. The platform allows doctors to scan patient cards (QR code-based) to view medical history, prescribe treatments, and manage follow-ups.

---

## 📌 Features

- ✅ Role-based authentication and authorization
- ✅ Doctor and Patient registration with profile management
- ✅ Patient QR card generation and scanning
- ✅ Medical history tracking with treatment records
- ✅ Follow-up scheduling and tracking
- ✅ REST API-ready
- ✅ Entity auditing (createdAt, updatedAt)
- ✅ Dockerized

---

## ⚙️ Technologies Used

- Java 17+
- Spring Boot 3
- Spring Security + JWT + OAuth2
- Spring Data JPA
- MySQL / PostgreSQL
- Lombok
- Jakarta Persistence (JPA)
- Docker

---

## 🚀 Setup Instructions

### 🔧 Step 1: Clone the Repository

```bash
git clone https://github.com/sumanthakur0401/Swasth_ID_BackEnd.git
cd Swasth_ID_BackEnd
```

### ⚙️ Create .env file or set environment variables
  
Configure the following environment variables used in application.properties:

SERVER_PORT=8080

DATABASE_URL=jdbc:mysql://localhost:3306/swasthdb
DATABASE_USERNAME=root
DATABASE_PASSWORD=yourpassword

JWT_SECRET=your_jwt_secret_key
TOKEN_EXPIRATION_TIME=3600000
REFRESH_TOKEN_EXPIRATION_TIME=86400000

MAIL_USERNAME=your_email@gmail.com
MAIL_PASSWORD=your_email_app_password

GOOGLE_CLIENT_ID=your_google_client_id
GOOGLE_CLIENT_SECRET=your_google_client_secret
GOOGLE_REDIRECT_URI=http://localhost:3000/oauth2/redirect

GITHUB_CLIENT_ID=your_github_client_id
GITHUB_CLIENT_SECRET=your_github_client_secret
GITHUB_REDIRECT_URI=http://localhost:3000/oauth2/redirect

FRONTEND_URL=http://localhost:3000

ou can use .env with Spring Boot Docker support or tools like Docker Compose.

### 🐍 Step 2: Create .env or application.yml (Optional for customization)

### 📦 Step 3: Install Dependencies

./mvnw clean install

### 🧪 Step 4: Run the Application

./mvnw spring-boot:run

Or build JAR and run:

./mvnw clean package
java -jar target/swasth-id-backend.jar

📁 Folder Structure

src/main/java/com/org/swasth_id_backend/
│
├── config/            → Security & app configs
├── controller/        → REST Controllers
├── dto/               → DTOs for transferring data
├── entity/            → JPA Entities (User, Doctor, Patient, Treatment)
├── mapper/            → MapStruct Mappers
├── repository/        → Spring Data Repositories
├── service/           → Business Logic Layer
├── util/              → QR generation & helpers
└── SwasthIdBackendApplication.java


🐳 Docker Setup

docker build -t swasth-backend .
docker run -p 8080:8080 --env-file .env swasth-backend

📬 Contact
Developed by Tapan Kumar, Suman Thakur, Deepanshu 
🧑‍💻 Java Full Stack Developer | Spring Boot | React | Docker

