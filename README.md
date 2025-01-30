# Backoffice web JAVA #

#### Written in Java 21 and Spring Boot 3.4.1 ####

Full setup : Spring MVC, Spring Data and PostgreSQL database, Spring Security with JJWT api (Token JWT used for connection) and OAuth2 with Github SSO (yet to be implemented), Lombok, MapStruct, Junit, Mockito and H2 database for integration testing

### Auth APIs ###
> ***/auth/login*** : __POST__ route to log in with { email, password }   
> ***/auth/logout*** : __POST__ route to log out  
> ***/auth/register*** : __POST__ route to register with { email, password }

### Movies APIs ###
> Base URL : ***/api/movies***
>> __GET__ : get all movies  
>> __POST__ : create new movie { MovieDTO }  
>
> ***/api/movies/{genre}*** : __GET__ : get all movies by movie genre passed in path param
>
> ***/api/movies/genres*** __GET__ : get all movie genres to populate select for example
