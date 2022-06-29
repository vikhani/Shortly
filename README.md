# Shortly
Small backend project based on [Avito Tech interview test project](https://github.com/avito-tech/auto-backend-trainee-assignment).
Generates a short link representation with random symbols or with your own custom string for the provided URLs. The generated representations can redirect you to your original link.
 
## Build
Clone this repository and run `docker-compose up`.

## API
[Usage examples with Postman](https://www.getpostman.com/collections/220d4f46776967f63021)
(copy link and use Postman Import to import Shortly Collection)

- `PUT /add` generating short link
  - Arguments
    - longUrl - the link you'd like to get shortened
    
- `PUT /add/custom/?userVersion=...&longUrl=...` generating custom short link
  - Arguments
    - userVersion - short string for the query you'd like to see in shortened link
    - longUrl - the link you'd like to get shortened
    
- `GET /...` using short link

## Stack
- Java 11
- Spring Boot
- Lombok
- MongoDB
- Docker
