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

---
##
<img width="1919" height="1079" alt="Screenshot 2025-08-17 202230" src="https://github.com/user-attachments/assets/66df772b-7f7f-4f59-917f-edb8bcafb081" />

##
<img width="1919" height="1079" alt="Screenshot 2025-08-17 202256" src="https://github.com/user-attachments/assets/8aa16b85-a0d2-4022-8ac8-805a0c03aa34" />

##
<img width="1919" height="1079" alt="Screenshot 2025-08-17 202305" src="https://github.com/user-attachments/assets/cce5b368-6d64-4902-95de-41928fadc217" />

##
<img width="1919" height="913" alt="Screenshot 2025-08-17 202417" src="https://github.com/user-attachments/assets/73f80325-fc64-4d0c-8be0-0ecb79937a92" />

##
<img width="1919" height="1073" alt="Screenshot 2025-08-17 202541" src="https://github.com/user-attachments/assets/8c8c38a0-6d02-4123-ab88-670f39ef80b1" />

##
<img width="1913" height="912" alt="Screenshot 2025-08-17 202702" src="https://github.com/user-attachments/assets/f79a96ac-7629-4d1a-94ff-e1c21e45fcd5" />

##
<img width="1919" height="1076" alt="Screenshot 2025-08-17 202736" src="https://github.com/user-attachments/assets/4c8f10ef-1b63-4569-bdaf-4a316ffa2f45" />
<br />

<img width="1910" height="1015" alt="Screenshot 2025-08-17 202807" src="https://github.com/user-attachments/assets/70bdb577-715a-48b3-b605-24283b90b69d" />
<br />
