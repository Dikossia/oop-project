# OOP Project Requirements Coverage

## Core Functional Requirements

- Authentication for all roles: `core/AuthService.java` + `core/ConsoleUIController.java`.
- Course registration with constraints (21 credits, duplicate checks, failed courses checks): `users/Student.java`.
- Multiple instructors per course: `academic/Course.java`.
- Mark model (att1, att2, final) and teacher grading flow through enrollment: `academic/Mark.java`, `users/Teacher.java`, `academic/Enrollment.java`.
- Manager reports for academic performance: `users/Manager.java`.
- Student schedule and learning files/tasks with submission: `users/Student.java`, `academic/StudyMaterial.java`.

## Research Requirements

- `Researcher` abstraction: `research/Researcher.java` (interface).
- Research project with participant validation and custom exception: `research/ResearchProject.java`, `exceptions/NonResearcherException.java`.
- Research paper model with key fields + `Comparable`, `Comparator`s: `research/ResearchPaper.java`.
- `PrintPapers(Comparator c)` for researcher: `users/Teacher.java`.
- University-level printing of all papers sorted by chosen strategy: `core/University.java`, manager menu in `core/ConsoleUIController.java`.
- Top cited researcher support: `core/University.java`.
- Supervisor validation for 4th year with `LowHIndexException`: `users/Student.java`, `exceptions/LowHIndexException.java`.

## Persistence and OOP Requirements

- Serialization-based storage implemented via snapshot: `core/DataStore.java`.
- Serialized load/save wired in app lifecycle: `core/ConsoleUIController.java`.
- Collections, enums, custom exceptions, inheritance, interfaces are used across domain packages.

## 4 Design Patterns Demonstrated

1. **Singleton**: `core/University.java`.
2. **Strategy**: sorting research papers with selectable comparators in `research/ResearchPaper.java` + UI usage in `core/ConsoleUIController.java`.
3. **Factory Method / Simple Factory**: `users/UserFactory.java` for centralized user creation (`ADMIN/STUDENT/TEACHER/MANAGER`), used in `users/Admin.java`, `core/DataLoader.java`.
4. **Observer**: manager publishes news to all users via university broadcast (`users/Manager.java`, `core/University.java`, `users/User.java` inbox updates).

## Note for UML/Report

- Reflect these changes in class diagram (reverse engineering step): new relations around `Enrollment`, `StudyMaterial`, `DataStore`, `UserFactory`, and `University.publishNews`.
- Include this checklist in report appendix with screenshots of console demo flows.
