#FinalProgressReview — MovieRating

A simple Spring Boot service that manages movies and fetches external ratings from OMDb.
The project demonstrates RESTful API design, persistence with Spring Data JPA, global exception handling, and integration with an external API.

Overview
--------
FinalProgressReview provides:
- CRUD-style REST endpoints for managing movies
- Database access via JPA/Hibernate
- External rating lookup through the OMDb API
- Centralized exception handling
- Environment-based configuration for sensitive values (API keys, credentials)

Repository Layout (Important Files)
----------------------------------
src/main/java/.../FinalProgressReviewApplication.java
    Application bootstrap

src/main/java/.../controllers/MovieRestController.java
    REST endpoints for movie operations

src/main/java/.../externalApi/OmdbExternalRatingImpl.java
    OMDb external API integration

src/main/resources/application.properties
    Runtime configuration

db/dbschema.sql
    Database schema definition

db/db.sql
    Test / seed data

Prerequisites
-------------
- JDK 17
- Gradle (wrapper included)
- MariaDB (or compatible database) running and accessible
- (Optional) OMDb API key

Build and Run
-------------
Build:
./gradlew clean build

Run (Gradle):
./gradlew bootRun

Run (JAR):
java -jar build/libs/FinalProgressReview-0.0.1-SNAPSHOT.jar

Configuration
-------------
Configuration can be done via application.properties or environment variables.

Minimal MariaDB configuration:
spring.datasource.url=jdbc:mariadb://localhost:3306/moviedb
spring.datasource.username=YOUR_DB_USER
spring.datasource.password=YOUR_DB_PASS
spring.jpa.hibernate.ddl-auto=validate

OMDb API key (recommended: environment-based):
omdb.api.key=${OMDB_API_KEY:}

Set environment variable on Windows (PowerShell):
$Env:OMDB_API_KEY = "your_api_key_here"

The OmdbExternalRatingImpl reads the key at runtime. Using environment variables avoids hardcoding secrets.

Dynamically Fetching the OMDb API Key
------------------------------------
Recommended approaches:
- Provide OMDB_API_KEY as an environment variable and inject it using @Value or Environment
- Use a secrets manager and populate OMDB_API_KEY at process startup

This ensures the API key is not stored in Git and Postman only authenticates against your API.

Database: Create Admin and Regular User
---------------------------------------
Example SQL (adjust to match dbschema.sql):

INSERT INTO users (username, password, roles, enabled, created_at)
VALUES ('admin', '$2a$10$REPLACE_WITH_BCRYPT_HASH', 'ROLE_ADMIN', true, NOW());

INSERT INTO users (username, password, roles, enabled, created_at)
VALUES ('user',  '$2a$10$REPLACE_WITH_BCRYPT_HASH', 'ROLE_USER', true, NOW());

Passwords should be stored as BCrypt hashes. Generate hashes using BCryptPasswordEncoder or a small utility.

Sample API Calls (curl)
-----------------------
Assumes base URL http://localhost:8080 and header:
Authorization: Bearer <token>

Get movies:
curl -X GET "http://localhost:8080/movies" -H "Authorization: Bearer <your_token>"

Create movie:
curl -X POST "http://localhost:8080/movies" \
  -H "Authorization: Bearer <your_token>" \
  -H "Content-Type: application/json" \
  -d '{"title":"The Matrix","year":1999,"director":"Wachowski","imdbId":"tt0133093"}'

Fetch OMDb rating (if exposed):
curl -X GET "http://localhost:8080/external/omdb?imdbId=tt0133093" \
  -H "Authorization: Bearer <your_token>"


