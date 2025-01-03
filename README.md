# Star Wars Characters List By Jetpack-Compose

## Developed by Pouria Rezaie

# My Android Application

This project was developed as part of an interview process to demonstrate my technical skills in Android development. The application showcases modern development practices, clean architecture, and thoughtful design principles.

## Technologies Used

The app leverages the following technologies:

- **Programming Language:** Kotlin
- **UI Framework:** Jetpack Compose
- **Networking:** Retrofit
- **Image Loading:** Coil
- **Local Database:** Room
- **Dependency Injection:** Hilt

## Architecture

The application follows a combination of **MVVM (Model-View-ViewModel)** and **Clean Architecture** patterns to ensure maintainability and scalability. Here’s an overview of the architecture:

1. **Presentation Layer:**
    - Implemented using Jetpack Compose for building a modern, reactive UI.
    - ViewModels (using Hilt for dependency injection) manage the state and business logic.

2. **Domain Layer:**
    - Contains use cases to encapsulate business rules.

3. **Data Layer:**
    - Utilizes Room for local database management, with normalized tables and relationships handled using foreign keys.
    - Retrofit is used for making network requests.

## Features

1. **Data Management:**
    - The app integrates with the [SWAPI API](https://swapi.py4e.com/) to fetch Star Wars-related data, such as films, species, and planets.
    - Utilizes Room to store and manage data locally, with tables structured to reflect relational database principles.

2. **UI/UX Design:**
    - Emphasis on an acceptable and intuitive UI design, ensuring a clean and user-friendly interface built with Jetpack Compose.

3. **State Management:**
    - Uses ViewModels and StateFlow for efficient state handling and reactivity in the UI.

## Challenges Faced

1. **API Error:**
    - Initially planned to use [swapi.dev](https://swapi.dev), but it had server errors. Switched to the alternative API [https://swapi.py4e.com/].

2. **Database Design:**
    - Refactored the Room database to use multiple related tables, ensuring proper handling of foreign keys and relationships.

3. **Async Data Requests:**
    - Implemented asynchronous requests to fetch detailed data (e.g., films, species, and planets) without blocking the main thread.

## Testing
I have written unit tests for the use cases, ensuring that the business logic of the app works correctly and that the repository interactions are properly handled. These tests include scenarios for successful data retrieval, and testing error handling for edge cases.

## Unit Testing Approach
Unit tests are implemented using JUnit, MockK, and Kotlin Coroutines. The key use cases like GetCharactersUseCase and GetCharacterDetailUseCase are tested in isolation using mocks for the repository layer.

**Mocking Dependencies:** I use MockK to mock repository interactions and test the behavior of the use cases.

**Test Coverage:** I have written tests to check for the following scenarios:

**Success:** Verifying that the use case returns the expected data when the repository succeeds.

**Error Handling:** Verifying that the use case correctly handles errors when the repository fails.

## Lessons Learned

- Designing normalized database schemas with Room improves scalability and ensures better data handling.
- Async tasks for detailed data retrieval enhance performance but require careful state management to avoid inconsistencies.
- Building UI with Jetpack Compose significantly simplifies the development process while allowing modern design principles.




