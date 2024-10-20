# FilmsDB-MS

FilmsDB-MS is a microservice to my personal database of content multimedia.

this project it's builded in Java Spring, and use jwt authentication

*If you liked this project give me a star*

# ℹ️ Notes 
- less create a refresh token

# 🚀 Getting started

### Clone this repository
``` bash
git clone https://github.com/FelipeGCx/FilmsDB-MS.git
```
### if it's the first time
- uncomment the code fraction [here](src/main/java/com/fg/filmsMS/FilmsMsApplication.java?plain=1#L22-L29) to add roles
- comment from 79 line to 157 [here](src/main/java/com/fg/filmsMS/security/services/Impl/AuthServiceImpl.java?plain=1#L79-L157) and uncomment from 158 to 231 [here](src/main/java/com/fg/filmsMS/security/services/Impl/AuthServiceImpl.java?plain=1#L158-L231) to avalaible create user with role admin, later that, revert all

### Run the app
``` bash
mvn spring-boot:run
```
