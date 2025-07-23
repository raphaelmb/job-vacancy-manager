# Job Vacancy Manager

Job Vacancy Manager is a web application designed to streamline the process of managing job vacancies, applications, and user profiles for both companies and candidates. The system provides a robust backend API for handling job postings, candidate applications, authentication, and monitoring, making it suitable for modern recruitment platforms.

## Features

- **Candidate Module:**
  - Candidate registration and authentication
  - Profile management
  - Search and filter available job vacancies
  - Apply for jobs

- **Company Module:**
  - Company registration and authentication
  - Create and manage job postings
  - List jobs by company

- **Authentication & Security:**
  - JWT-based authentication for both candidates and companies
  - Role-based access control using Spring Security

- **API Documentation:**
  - Interactive API documentation with OpenAPI/Swagger

- **Monitoring & Observability:**
  - Integrated with Prometheus and Grafana for metrics and dashboards
  - Health and metrics endpoints exposed for monitoring

- **Quality Assurance:**
  - SonarQube integration for code quality analysis

## Technologies Used

- **Java 21**
- **Spring Boot 3** (Web, Security, Data JPA, Validation)
- **PostgreSQL** (production database)
- **H2** (in-memory database for testing)
- **JWT (JSON Web Token)** for authentication
- **Lombok** for boilerplate code reduction
- **OpenAPI/Swagger** for API documentation
- **Docker Compose** for container orchestration
- **Prometheus** and **Grafana** for monitoring
- **SonarQube** for static code analysis

## Getting Started

1. **Clone the repository:**
   ```bash
   git clone <repository-url>
   cd java-job-vacancy-manager
   ```

2. **Start the infrastructure (PostgreSQL, Prometheus, Grafana, SonarQube):**
   ```bash
   docker compose up -d
   ```

3. **Run the application:**
   ```bash
   ./mvnw spring-boot:run
   ```

4. **Access API documentation:**
   - Swagger UI: `http://localhost:8080/swagger-ui.html` (or `/swagger-ui/index.html`)

5. **Monitoring dashboards:**
   - Prometheus: `http://localhost:9090`
   - Grafana: `http://localhost:3000`
   - SonarQube: `http://localhost:9000`

## Project Structure

- `src/main/java/br/com/raphaelmb/job_vacancy_manager/`
  - `modules/candidate/` — Candidate-related logic (controllers, entities, use cases)
  - `modules/company/` — Company and job-related logic
  - `providers/` — JWT providers
  - `security/` — Security filters and configuration
  - `config/` — Swagger and other configurations
- `src/main/resources/` — Application properties and static resources