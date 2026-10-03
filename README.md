# 🔗 URL Shortener

A simple and responsive URL Shortener built using **Java, Spring Boot, HTML, CSS, and JavaScript**. It converts long URLs into short links, redirects users to the original URL, and tracks the number of clicks for each shortened link.

## ✨ Features

- **URL Shortening:** Converts long URLs into short, easy-to-share links.
- **URL Redirection:** Redirects users to the original URL when they visit a short link.
- **Click Analytics:** Tracks the total number of clicks for each shortened URL.
- **URL Lookup:** Retrieves the original URL using its short code.
- **QR Code Generation:** Generates a QR code for the shortened URL.
- **Copy to Clipboard:** Allows users to copy shortened URLs easily.
- **Input Validation:** Checks for empty URLs and ensures URLs start with `http://` or `https://`.
- **Persistent Storage:** Stores URL mappings and click counts in an H2 database.
- **Responsive Design:** Provides a user-friendly interface across different screen sizes.

## 🛠️ Technologies Used

### Backend
- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Maven

### Frontend
- HTML
- CSS
- JavaScript

### Database
- H2 Database

### Development Tools
- IntelliJ IDEA / VS Code
- Git and GitHub

## 📁 Project Structure

```text
URL-Shortener/
│
├── .mvn/
│   └── wrapper/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/kirti/urlshortener/
│   │   │       ├── controller/
│   │   │       │   ├── HomeController.java
│   │   │       │   └── UrlController.java
│   │   │       │
│   │   │       ├── dto/
│   │   │       │   └── ShortenRequest.java
│   │   │       │
│   │   │       ├── model/
│   │   │       │   └── UrlMapping.java
│   │   │       │
│   │   │       ├── repository/
│   │   │       │   └── UrlMappingRepository.java
│   │   │       │
│   │   │       ├── service/
│   │   │       │   ├── ShortCodeGenerator.java
│   │   │       │   └── UrlMappingService.java
│   │   │       │
│   │   │       └── UrlshortenerApplication.java
│   │   │
│   │   └── resources/
│   │       ├── static/
│   │       │   ├── index.html
│   │       │   └── style.css
│   │       │
│   │       └── application.properties
│   │
│   ├── test/
│   │
│   └── pom.xml
│
├── .gitignore
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```

## ⚙️ Getting Started

Follow these steps to run the project locally.

### Prerequisites

Make sure you have installed:

- Java Development Kit (JDK)
- Git
- An IDE such as VS Code or IntelliJ IDEA

### 1. Clone the Repository

```bash
git clone https://github.com/joshikirti1/URL-Shortener.git
```

### 2. Navigate to the Project Directory

```bash
cd URL-Shortener
```

### 3. Run the Application

On Windows, use:

```powershell
.\mvnw.cmd spring-boot:run
```

The application will start on:

```text
http://localhost:8080
```

Open this URL in your browser to use the application.

## 🔌 API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/shorten` | Creates a shortened URL |
| GET | `/api/{shortCode}` | Redirects to the original URL and increments the click count |
| GET | `/api/lookup/{shortCode}` | Retrieves the original URL |
| GET | `/api/analytics/{shortCode}` | Displays click analytics for a short URL |

### Example: Shorten a URL

**Request**

```http
POST /api/shorten
Content-Type: application/json
```

**Request Body**

```json
{
  "originalUrl": "https://www.youtube.com"
}
```

**Example Response**

```text
Short URL: http://localhost:8080/api/Ab12Cd
```

*The generated short code will vary.*

### Example: View Analytics

```text
GET http://localhost:8080/api/analytics/Ab12Cd
```

**Example Response**

```text
Short Code: Ab12Cd
Original URL: https://www.youtube.com
Total Clicks: 2
```

## 🗄️ Database Configuration

The project uses an H2 file-based database to store shortened URL mappings.

Database configuration is available in:

```text
src/main/resources/application.properties
```

The database stores:

| Field | Description |
|---|---|
| `shortCode` | Unique code generated for the shortened URL |
| `originalUrl` | Original long URL |
| `clickCount` | Number of times the short URL has been accessed |

The H2 database console is enabled at:

```text
http://localhost:8080/h2-console
```

Use the following connection details:

| Setting | Value |
|---|---|
| JDBC URL | `jdbc:h2:file:./data/urlshortenerdb` |
| Username | `sa` |
| Password | Leave blank |

## 📌 Important Note

The application currently generates short URLs using `localhost:8080`.

These links are intended for local development. They work on the computer running the application. To make links accessible to other users or devices, the application must be configured with a reachable network address or deployed to a public hosting service.

The local H2 database is excluded from Git using `.gitignore`. Therefore, existing local URL mappings are not included when cloning the repository.

## 🚀 Future Enhancements

- Redis caching for frequently accessed URLs
- Custom short URL aliases
- URL expiration
- User authentication and URL management
- Advanced analytics
- Deployment to a cloud platform

## 👩‍💻 Author

**Kirti Joshi**

- GitHub: [joshikirti1](https://github.com/joshikirti1)
- LinkedIn: [Kirti Joshi](https://www.linkedin.com/in/kirti-joshi01/)

---

⭐ If you find this project useful, consider giving the repository a star!
