1. Completed all the endpoints for CRUD operations on Muzix
2. Used h2-console to view in-memory data
3. Added an endpoint to search trackByName. Understand @Query and parameter passing to@Query
4. Generated API documentation using Swagger 2
5. Created custom exceptions TrackNotFoundException, TrackAlreadyExistsException in acom.stack....exceptions package.
   Performed appropriate exception handling and propagationBack.
6. Running Logic on Startup in Spring. Create seed data to pre-fill the database with trackinformation whenever the application starts.
   Used both approaches:Approach
       1: ApplicationListener<ContextRefreshedEvent>Approach
       2: CommandLineRunner (Find out how it differs from ApplicationRunner)
7.Global exception using Controller advice
8.Removed all hard coded data from the application code to application.propertiesa)
       by using
       a) @Value.
       b) @PropertySourcec)by using ​​ Environment(https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/core/env/Environment.html)
9.Added @Lombok(https://drive.google.com/file/d/1QQpEQZbDD9pmW2qrhYsx5N9XYQ5bJ5dM/view
10.Provide a second implementation of MuzixService.
    Named it MuzixServiceImpl2 and
    used @Primary and
         @Qualifier annotations for the required implementation.
 11.added testcases
