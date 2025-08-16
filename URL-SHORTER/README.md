
<img width="1015" height="425" alt="image" src="https://github.com/user-attachments/assets/db275d0a-264b-4cfc-b3fa-3338ce6e227a" />

<h1 align="center">URL Shortner</h1>
A modern, responsive URL shortening service built with Spring Boot and Mustache templates. Features a beautiful glassmorphism design with real-time URL shortening and comprehensive analytics tracking.

## Features

- **URL Shortening**: Convert long URLs into short, shareable links
- **Real-time Analytics**: Track clicks and view access timestamps
- **Modern UI**: Glassmorphism design with particle background animations
- **Responsive Design**: Works seamlessly on all devices
- **Copy to Clipboard**: Easy sharing functionality
- **Instant Feedback**: Real-time URL shortening with immediate results

## Technology Stack

- **Backend**: Spring Boot (Java)
- **Frontend**: Mustache templates with vanilla JavaScript
- **Database**: MySQL
- **Styling**: Custom CSS with modern design principles
- **Build Tool**: Maven

## Quick Start

1. Clone the repository
2. Configure your database in `application.properties`
3. Run `mvn spring-boot:run`
4. Access the application at `http://localhost:8080`

---

### Stats Page - Analytics Dashboard
<img width="1046" height="466" alt="image" src="https://github.com/user-attachments/assets/11a70e03-1671-4961-a746-3a9a387e4d80" />

*Comprehensive analytics dashboard showing click statistics and access timestamps for each shortened URL.*

**Key Features:**
- Real-time click count tracking
- Detailed timestamp history of all clicks
- Search functionality by short code
- Clean data presentation with organized layout
- Instant stats loading for any shortened URL

**Analytics Capabilities:**
The stats page provides valuable insights into link performance. Users can see exactly when their links were accessed, track engagement over time, and monitor the success of their shortened URLs across different campaigns.

---


## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/linkshortslinks/URL_SHORTER/
│   │       ├── config/
│   │       ├── controller/
│   │       ├── entity/
│   │       ├── repository/
│   │       ├── service/
│   │       └── UrlShorterApplication.java
│   └── resources/
│       ├── static/
│       │   ├── css/
│       │   └── js/
│       └── templates/
└── test/
```

## API Endpoints

- `GET /` - Home page with URL shortening form
- `POST /shorten` - Create a shortened URL
- `GET /{shortCode}` - Redirect to original URL
- `GET /stats` - Analytics dashboard
- `GET /stats/{shortCode}` - Get stats for specific URL

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Submit a pull request

## License

This project is licensed under the MIT License.
