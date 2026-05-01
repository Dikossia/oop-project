# University Management System
A Java console application demonstrating core Object-Oriented Programming principles through a university domain model.<br>

## Overview
This project simulates a university environment with users, courses, research activities, and notifications. It covers authentication, role-based access, academic records, research management, and persistent data storage.<br>

## Features
- Authentication — login system for all user roles
- Course management — registration with credit limit (≤ 21 credits), multiple instructors per course
- Mark system — structured grades with attestation 1, attestation 2, and final exam scores
- Research module — papers, projects, researcher validation, h-index check for 4th-year supervisors
- News notifications — broadcast system using the Observer pattern
- Data persistence — serialization-based storage via DataStore

## How to Run
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
Technology
- Language: Java 17
- Storage: Java Object Serialization (.ser file)
- Build: plain javac — no build tool required



## Project Structure:

oop-project/<br>
core/<br>
	University<br>
	Log<br>
	ConsoleUIController<br>
	AuthService<br>
	DataLoader<br>
users/<br>
	User<br>
	Admin<br>
	Employee<br>
	Manager<br>
	Student<br>
	Teacher<br>
research/<br>
	Researcher<br>
	ResearchProject<br>
	ResearchPaper<br>
academic/<br>
	Course<br>
	Enrollment<br>
	Lesson<br>
	Mark<br>
	News<br>
	Report<br>
	Request<br>
	StudyMaterial<br>
enums/<br>
	LessonType<br>
	ManagerType<br>
	ReportType<br>
	RequestStatus<br>
	RequestType<br>
	TeacherType<br>
exceptions/<br>
	CreditLimitExceededException<br>
	LowHIndexException<br>
 	NonResearcherException<br>









## What to do Next:
1)File System between users and teachers.<br>
2)News System.<br>
3)I need a System where teacher can create files inside studyFiles(there is an explanation.txt of structure).<br>
4)Structre the project.<br>



