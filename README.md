# hackathon

# Setup

## MacOs installation:
### Install homebrew
1. run `/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"`
### Install docker
1. run `brew install --cask docker`
2. start `Docker Desktop`
3. register/login
4. verify docker daemon is runner by running `docker ps`. If there is no error it is running
### Setup elasticache
1. run `docker run -p 9200:9200 -p 9300:9300 --name elasticsearch \                                                                                                                                  ─╯
   -e "discovery.type=single-node" \
   -e "xpack.security.http.ssl.enabled=false" \
   -e "xpack.security.enabled=false" \
   docker.elastic.co/elasticsearch/elasticsearch:8.10.2`
### Install sdkman
1. run `curl -s "https://get.sdkman.io" | bash`
2. run `source "$HOME/.sdkman/bin/sdkman-init.sh"`
### Install JDK
1. run `sdk install java 23-open` 
### Install maven
1. run `sdk install maven`

# Software used

### Java
- OpenJDK 23

### Spring Initialzr Modules included:
- Spring Web
- Spring Boot Dev Tools
- Spring data JPA
- H2 Database
- PostgresSQL Driver
- Spring Data elastic search (Access+Driver)
- Lombok

### VS Code plugins:
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

# Various REST end points for postman usage
1. Get all Todos: http://localhost:8080/api/todos
2. Create a Todo: http://localhost:8080/api/todos
{
    "id": 1,
    "name": "Sample Todo",
    "description": "This is a sample todo item",
    "dateCreated": "2023-10-01",
    "status": "CREATED"
}
3. Get One Todo: http://localhost:8080/api/todos/1
4. Update a Todo: http://localhost:8080/api/todos/1
{
    "id": 1,
    "name": "Sample Todo100",
    "description": "This is a sample todo item",
    "dateCreated": "2023-10-01",
    "status": "CREATED"
}
5. Delete a TODO: http://localhost:8080/api/todos/1

# Plan and how we divide the work - falsh book along the way:
1. Introduction to our problem statement - Ajmal 1 minute
2. Explain how maven, spring and spring boot could be used for back-end, Overview of spring eco-system, modules, Initializr - 5 minutes
3. Flash the versions of dependencies and VS code plugin used - 1 minute
4. We will focus the work based on the TodoList application - 1 minute
5. Data model Todo will have 3 fields: Name, description, date created and status. Status will have 4 Enum types: Created, Updated, Completed, Deleted - 2 minutes
6. REST API and principles will be created for CRUD operation will follow the above Enum status - Ajmal - 5 minutes
    1. Show how to use Postman - Ajmal
    2. Explain REST principles - Ajmal
7. Explain test pyramid and explain one unit test - Ajmal 2 minutes
8. Explaing integration  Jacks - 2 minutes
    1. We only show case running few test cases, won't show case writing tests
9.  Persistence layer - Store the above Postgres and explain - Jacks - 5  minutes
10. Persistence layer - Store the above in Elastic search - Jack - 3 minutes
    1. Jacks to explain why different data stores types
11. GtiHub co-pilot plugin overview - Jack - 1 minute
