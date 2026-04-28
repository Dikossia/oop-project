University Management System
A Java console application demonstrating core Object-Oriented Programming principles through a university domain model.

Overview
This project simulates a university environment with users, courses, research activities, and notifications. It covers authentication, role-based access, academic records, research management, and persistent data storage.

Features
- Authentication — login system for all user roles
- Course management — registration with credit limit (≤ 21 credits), multiple instructors per course
- Mark system — structured grades with attestation 1, attestation 2, and final exam scores
- Research module — papers, projects, researcher validation, h-index check for 4th-year supervisors
- News notifications — broadcast system using the Observer pattern
- Data persistence — serialization-based storage via DataStore

How to Run
Requirements: Java 17+
```bash
git clone https://github.com/Dikossia/oop-project.git
cd oop-project
mkdir -p out
javac -d out $(find src -name "*.java")
java -cp out university.Main
```
Key Business Rules
- Students cannot register for more than 21 credits per semester
- A 4th-year student supervisor must have an h-index ≥ 3 (throws LowHIndexException otherwise)
- Only users with researcher status can join research projects (throws NotResearcherException otherwise)
- Research papers can be sorted by citations, date, or pages
- 
Technology
- Language: Java 17
- Storage: Java Object Serialization (.ser file)
- Build: plain javac — no build tool required



Project Structure:
oop-project/

core/
	University
	Log
	ConsoleUIController
	AuthService
	DataLoader
users/
	User
	Admin
	Employee
	Manager
	Student
	Teacher
research/
	Researcher
	ResearchProject
	ResearchPaper
academic/
	Course
	Enrollment
	Lesson
	Mark
	News
	Report
	Request
	StudyMaterial
enums/
	LessonType
	ManagerType
	ReportType
	RequestStatus
	RequestType
	TeacherType
exceptions/
	CreditLimitExceededException
	LowHIndexException
	NonResearcherException









What to do Next:
Add user Schedule System.
Better DataLoader.
The program has a bug, every time you start it new null users are created in users.txt.
File System between users and teachers.
Implementing Exceptions and boundery values.
News System.
I need a System where teacher can create files inside studyFiles(there is an explanation.txt of structure).



