# Copilot Instructions for spring-ai-rag

## Project Overview
This is a Spring Boot application for AI-powered Retrieval-Augmented Generation (RAG). The codebase is organized using standard Spring conventions, with clear separation between controllers, services, and configuration. The main entry point is `SpringAiRagApplication.java`.

## Architecture & Key Components
- **Controllers**: Place REST endpoints in `controller/` (e.g., `ControllerClass.java`).
- **Services**: Business logic and integration with data sources in `service/` (e.g., `DocumentInjectionService.java`).
- **Configuration**: Application properties in `src/main/resources/application.properties`.
- **Entry Point**: `SpringAiRagApplication.java` bootstraps the app.

## Developer Workflows
- **Build**: Use Maven wrapper: `./mvnw clean install` (Linux/macOS) or `mvnw.cmd clean install` (Windows).
- **Run**: `./mvnw spring-boot:run` or run `SpringAiRagApplication` from your IDE.
- **Test**: `./mvnw test` or run tests in `src/test/java`.
- **Debug**: Standard Spring Boot debugging applies. Use IDE breakpoints or add `-Dspring-boot.run.fork=false` for easier debugging.

## Patterns & Conventions
- **Dependency Injection**: Use `@Service`, `@Autowired`, and `@Controller` as appropriate.
- **Database Access**: Use `JdbcTemplate` for direct SQL, injected via `@Autowired`.
- **Configuration**: Store secrets and config in `application.properties`.
- **Class Naming**: Suffix service classes with `Service`, controllers with `Controller`.

## Integration Points
- **Spring Boot**: All major components are Spring-managed beans.
- **JDBC**: For database operations, see `DocumentInjectionService.java`.
- **REST**: Expose endpoints via controller classes in `controller/`.

## Examples
- See `DocumentInjectionService.java` for service patterns and dependency injection.
- See `ControllerClass.java` for REST endpoint structure.

## Tips for AI Agents
- Follow existing naming and package conventions.
- When adding new features, mirror the structure of existing services and controllers.
- Use Maven for dependency management and builds.
- Place new configuration in `application.properties`.

---
If any conventions or workflows are unclear, please ask for clarification or examples from the codebase.
