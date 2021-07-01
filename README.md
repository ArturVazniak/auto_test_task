# Car sales RESTfull service.
## An example of selling cars has been implemented. Example: https://www.abw.by/
---
### Technologies:
1. Java 11
2. Spring Boot
3. Spring MVC (REST)
4. Swagger
5. Spring Data\Spring JDBC
6. Spring Security
7. PostgreSQL
8. Flyway
9. Maven
10. Mockito framework
11. Hibernate ORM
---
- Architecture - 3 levels with division into layers
- Authorization and 2 types of users: administrator
and a simple user. Admin sees all ads and everyone
users
- Pattern DTO
- Unit tests for services are written
- Following SOLID principles
---
### Pages:
1. List of cars available for purchase
2. It is necessary to have filtering by criteria, sorting, page-by-page
 view (Pagination)
3. Server-side pagination
4. Detailed view of the sale ad
5. Adding / editing a sale advertisement
6. Availability of validation of input data
7. The api response contains information why the request is not valid
8. Deleting objects is done through "soft delete"
 and all not deleted are pulled to the client
9. Initial data is specified as migrations
Flyway.
10. User can edit only their own ads and edit only their profile
---
### To enter the application:

- As an administrator: Enter login: Anton (password 100) or Maxim (password 200)

- As user: Enter login: Oleg (password 100) or Denis (password 200)
