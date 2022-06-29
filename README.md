# Shortly
Small backend project based on [Avito Tech interview test project](https://github.com/avito-tech/auto-backend-trainee-assignment).
 
## Build
Clone this repository and run `docker-compose up`.

## API
[Usage examples with Postman](https://www.getpostman.com/collections/220d4f46776967f63021)

- `/add/?longUrl=...` generating short link
- `/add/custom/?userVersion=...&longUrl=...` generating custom short link
- `/...` using short link

## Stack
- Java 11
- Spring Boot
- Lombok
- MongoDB
- Docker
