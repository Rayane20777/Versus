# VERSUS

## Project Description
This application is developed for managing e-sport tournaments, providing tools to organize players, teams, and tournaments effectively. It allows for player registration, team management, and tournament scheduling, all streamlined for an optimal e-sport event management experience.

## General Objective
To simplify and centralize the management of e-sport tournaments, including comprehensive support for player, team, and tournament workflows.

## Technologies Used
- **Java 8**: Core language.
- **Spring Core (XML configuration)**: Dependency injection and inversion of control.
- **Hibernate/JPA**: ORM and data persistence.
- **H2 Database**: Lightweight in-memory database for testing and ease of setup.
- **JUnit and Mockito**: Unit and integration testing.
- **JaCoCo**: Code coverage tool.
- **SLF4J**: Logging library.

## Project Structure
- **Model Layer**: JPA entity classes for representing application data.
- **Repository Layer**: Manages data access and database interactions.
- **Service Layer**: Business logic for managing players, teams, and tournaments.
- **Console Interface**: Menu-based console interface for user interactions.

## Architecture Overview
The project follows a layered architecture:
1. **Model**: Contains the entity classes representing database tables.
2. **Repository**: Accesses the database using JPA/Hibernate.
3. **Service**: Contains the main application logic for tasks such as calculating tournament durations and managing teams.
4. **Console Interface**: Provides a simple console-based interface for user interaction.

## Installation and Usage Instructions
```bash

# Clone the repository:
git clone https://github.com/Rayane20777/versus.git

# Move into the project directory:
cd versus

# Install dependencies:
mvn clean install

```

## Prerequisites
- **Java 8**: Required for running the application.
- **Maven**: Required for managing dependencies and building the project.



# Ensure H2 configurations are properly set in persistence.xml.

# Launch the H2 console by running:
java -jar h2-x.x.x.jar


## Future Improvements
- **Web-Based Interface**: Add a front-end interface for broader accessibility.
- **Extended Tournament Analytics**: Advanced statistics tracking for players and teams.
- **Real-Time Updates**: Real-time tournament progress tracking using WebSockets.



