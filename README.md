# springboot-auth

A **Spring Boot authentication service** that provides user registration, login, profile management, JWT-based authentication, OTP verification, and password reset functionality.  
It uses **Spring Security with JWT**, **HttpOnly cookies**, **Thymeleaf templates**, and **Java Mail Sender** for email verification and OTP delivery.

---

## Features
- **User Registration** (`/register`)
- **Login** with JWT stored in **HttpOnly Secure Cookies**
- **Profile Access** (`/profile`) – only for authenticated users
- **Check Authentication Status** (`/is-authenticated`)
- **Logout** (`/logout`)
- **Send OTP** for:
  - Email Verification (`/send-otp`, `/verify-otp`)
  - Password Reset (`/send-reset-otp`, `/reset-password`)
- **OTP Security**
  - 6-digit OTP generated using `SecureRandom`
  - Short expiration window for better security
- **Email Notifications**  
  - User verification emails
  - Password reset OTP emails via **Java Mail Sender**
- **Thymeleaf Templates** for emails & views
- **JWT stored in Cookies**  
  - `HttpOnly`
  - `Secure` (HTTPS only)
  - `SameSite=Strict`

---

## Tech Stack
- **Java 21**
- **Spring Boot 3+**
- **Spring Security + JWT**
- **Spring Web / REST**
- **Spring Data JPA (Hibernate)**
- **Thymeleaf**
- **Java Mail Sender**
- **MySQL (configurable)**
- **Maven**

---

## API Endpoints
| Method | Endpoint            | Description                           | Auth Required |
|--------|---------------------|---------------------------------------|---------------|
| POST   | `/register`         | Register new user                     | No |
| POST   | `/login`            | Login user, set JWT in cookie         | No |
| GET    | `/profile`          | Get user profile                      | Yes|
| GET    | `/is-authenticated` | Check if JWT is valid (cookie check)  | Yes|
| POST   | `/logout`           | Clear authentication cookie           | Yes|
| POST   | `/send-otp`         | Send OTP for account verification     | No |
| POST   | `/verify-otp`       | Verify OTP                            | No |
| POST   | `/send-reset-otp`   | Send OTP for password reset           | No |
| POST   | `/reset-password`   | Reset password with OTP               | No |

---

## Security
- JWT is stored in **HttpOnly, Secure, SameSite=Strict cookies** using `ResponseCookie`.
- Protects against **XSS** and **CSRF**.
- Passwords are hashed before saving to DB.
- OTP codes are generated using **`SecureRandom`**.
