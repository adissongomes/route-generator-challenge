# Route API

It consumes messages from the project [route-generator](https://github.com/adissongomes/route-generator) and
stores them in a database.

## Running the application

**Start the postgres container:**
```shell
docker-compose up -d
```

**Start the application:**
```shell
./gradlew bootRun
```

**Requirements**
The [route-generator](https://github.com/adissongomes/route-generator) must be running.

## Endpoints

### GET /routes/{id}
Return a route by id with the list of events (status and event time).

### GET /routes (todo)
Return all completed routes

### POST /routes/{id} (todo)
Force the route to be completed
