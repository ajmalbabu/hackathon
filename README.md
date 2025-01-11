# hackathon

# VS code version:
- Version: 1.96.2 (Universal)

# JDK version used:
- openjdk version "23.0.1" 2024-10-15
- OpenJDK Runtime Environment Homebrew (build 23.0.1)
- OpenJDK 64-Bit Server VM Homebrew (build 23.0.1, mixed mode, sharing)

# Spring Initialzr Modules included:
- Spring Web
- Spring Boot Dev Tools
- Spring data JPA
- H2 Database
- PostgresSQL Driver
- Spring Data elastic search (Access+Driver)
- Lombok

# VS Code plugins:
- Spring Boot Developer Extension Pack - by Developer Soapbox
- Spring Boot Snippets for VS Code - by Developer Soapbox
- VS Code Spring Boot Application Development Extension Pack - by VMWare
- Spring Boot Tools - by VMWare
- Spring Boot Dashboard for VS Code - by Microsoft
- Spring Initializr Java Support - by Microsoft
- GitHub Copilot - by GitHub
- GitHub Copilot Chat - by GitHub
- Markdown Preview Enhanced - by Yiyi Wang


- Including the spring tool suite maven dependency and VS code plugin so that it would enable liver reload server so that for any change, changes server reload the application automatically.


# Plan and how we divide the work:
1. Introduction to our problem statement - Ajmal 1 minute
2. Explain how maven, spring and spring boot could be used for back-end, Overview of spring eco-system, modules, Initializr - 5 minutes
3. Flash the versions of dependencies and VS code plugin used - 1 minute
4. We will focus the work based on the TodoList application - 1 minute
5. Data model Todo will have 3 fields: Name, description, date created and status. Status will have 4 Enum types: Created, Updated, Completed, Deleted - 2 minutes
6. REST API and principles will be created for CRUD operation will follow the above Enum status - Ajmal - 5 minutes
	1. Show how to use Postman - Ajmal
	2. Explain REST principles - Ajmal
7. Write unit and integration test and explain test pyramid - Ajmal and or Jacks - 3 minutes 
	1. We only show case running few test cases, won't show case writing tests
8. Persistence layer - Store the above Postgres and explain - Jacks - 5  minutes
9. Persistence layer - Store the above in Elastic search - Jack - 3 minutes 
	1. Jacks to explain why different data stores types
10. GtiHub co-pilot plugin overview - Jack - 1 minute
