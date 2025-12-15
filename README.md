FinalProgressReview — MovieRating

Overview
A simple Spring Boot service to manage movies and fetch external ratings from OMDb. REST API using Spring Data JPA, a minimal authentication helper that reads the raw Authorization header, global exception handling, and an OMDb integration.

Important paths
- `src/main/java/com/example/finalprogressreview` — application code
- `src/main/resources/application.properties` — runtime configuration
- `db/dbschema.sql` and `db/db.sql` — database schema and example data
- `swagger/` — API docs and UI assets

Prerequisites
- JDK 17
- Gradle (wrapper included)
- MariaDB (or compatible) running and reachable
- (Optional) OMDb API key

Build and run
From project root:
    ./gradlew clean build
    ./gradlew bootRun
Or run the jar:
    java -jar build/libs/FinalProgressReview-0.0.1-SNAPSHOT.jar

Configuration
Edit `src/main/resources/application.properties` or set environment variables. Relevant keys:
    database.url=jdbc:mariadb://localhost:3306/movie_schema
    database.username=root
    database.password=root
    omdb.api.key=YOUR_OMDB_KEY
    omdb.api.url=http://www.omdbapi.com/

Notes:
- `HibernateConfig` reads `database.url`, `database.username`, `database.password`.
- `OmdbExternalRatingImpl` reads `omdb.api.key` and `omdb.api.url`.

Database: example SQL to create test users
The project already includes `db/db.sql` that inserts plaintext credentials. Example matching the current schema:
    INSERT INTO users (username, password, is_admin)
    VALUES ('admin', 'adminpass', 1);

    INSERT INTO users (username, password, is_admin)
    VALUES ('user', 'userpass', 0);

Authentication convention
This project uses a simple header-based convention. Controllers accept `@RequestHeader HttpHeaders headers` and `AuthenticationHelper` expects the `Authorization` header value in the form:
    <username><space><password>

Examples:
- Admin header value: `admin adminpass`
- User header value: `user userpass`

Sample API calls (curl)
Get all movies:
    curl -X GET "http://localhost:8080/movies" -H "Authorization: admin adminpass"

Create movie:
    curl -X POST "http://localhost:8080/movies" \
      -H "Authorization: admin adminpass" \
      -H "Content-Type: application/json" \
      -d '{"title":"The Matrix","releaseYear":"1999-03-31","director":"Wachowski","rating":9}'

Fetch OMDb rating via app endpoint (if available):
    curl -X GET "http://localhost:8080/external/omdb?imdbId=tt0133093" \
      -H "Authorization: admin adminpass"

Postman notes
- Use an `Authorization` header (Header type: Key=Authorization, Value=`admin adminpass`).
- Do not use Bearer tokens unless you implement token-based auth.

Other notes
- Asynchronous tasks are enabled via `config/AsyncConfig` (`@EnableAsync`).
- Global exception mapping is in `exceptions/GlobalExceptionHandler`.

