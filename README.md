# MovieRecom Service

movierecom is a Java application that allows you to get the 20 most recommended movies according to any group of genres you prefer.  

## Setup

Add your [TMDB Api Key](https://www.themoviedb.org/settings/api) in the text file:
   
   - **etc/secrets/tmdb.apikey**

If you want to run the project from maven locally, add the Api Key in the text file:
    
   - **src/main/resources/application-test.yml** 


## Run in Docker locally

From `movierecom` directory:

```bash
docker-compose up -d
```

This will build the service and start it up in the background.
For it to work it is important to have an ApiKey placed in the **tmdb.apikey** file mentioned above.

Then you can access the service and see the different endpoits [here](http://localhost:9020/api/swagger-ui.html).

## Run in Maven locally

From `movierecom` directory:

```bash
./mvnw spring-boot:run -Dspring.profiles.active=test
```

Then you can access the service and see the different endpoits [here](http://localhost:9020/api/swagger-ui.html).

## Tests

To run the different unit tests you just need to write on the console:

```bash
./mvnw test
```

## Potential improvements

- Even if it is not migrated to a microservices architecture, it would be advisable to use memory caching at least to avoid repeated calls to external services.

- It could be implemented that random movies are taken from the response obtained by the external service, so that you always have different recommendations, you will never run out of movies!