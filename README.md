# Web-elevator-system
Simple webapp simulating a real-life elevator control centre.

## How to run?
### First run the server...
**If you prefer maven**

Simple `mvn clean spring-boot:run` should do the trick.

**If you prefer docker**

```bash
docker build --tag web-elevator-system .
docker run -p 8080:8080 web-elevator-system 
```

### ...then access the endpoints
You can do it by simply importing the `elevator-system-web.postman_collection.json` file from the main folder to your postman app.

### Quick rundown of endpoints
#### Main
**Sending a request for an elevator**
```http request
POST /elevators/pick-up/{{destination-floor-number}}
```

**Performing the next step of the simulation**
```http request
POST /simulation/next-step
```

**Checking the current status of the simulation**
```http request
GET /simulation/status
```

#### Endpoints working on "patch" profile *(experimental)*
**Changing state of a single elevator**
```http request
GET /elevators/{{elevator-id}}/state
```

**Changing destination of a single elevator**
```http request
GET /elevators/{{elevator-id}}/destination-floor
```

*For more detailed descriptions of request bodies, parameters and responses go to [openapi.yaml](src/main/resources/openapi/openapi.yaml)*

