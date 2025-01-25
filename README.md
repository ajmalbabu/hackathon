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
1. run `start-dependencies.sh`
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

# IDE Setup

If you are considering an IDE, you should look at JetBrains offerings
They are free for education and the AI assistance from JetBrains is as of Jan 2025 far superior to Copilot
On a silicone mac pro+, the performance difference is negligeable.

## Intellij Ultimate

### plugins

- JetBrains AI Assistant
- JPA Buddy
- KeyPromoter X

## Visual Code Studio

### plugins:
- Spring Boot Developer Extension Pack - by Developer Soapbox
- Spring Boot Snippets - by Developer Soapbox
- Spring Boot Tools - by VMWare
- Spring Boot Dashboard - by Microsoft
- Spring Initializr Java Support - by Microsoft
- GitHub Copilot - by GitHub
- GitHub Copilot Chat - by GitHub
- Markdown All in One - by You Zhang
- YAML - by Red Hat
- GitHub Pull Requests - by Github
- IntelliJ IDEA Keybindings (if you are an IntelliJ user)
- Rest Client - by Huachao Mao

- Including the spring tool suite maven dependency and VS code plugin so that it would enable live reload server so that for any change, changes server reload the application automatically.

# Various REST end points for postman usage

See executable examples in [endpoints.http](file://demo/endpoints.http)

