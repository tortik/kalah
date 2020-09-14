#Design
Application based on hexagon/onion architecture

Game based on state: one of four (FirstPlayerMoveState, GameOveState, SecondPlayerMoveState, WaitingSecondPlayerState)

For a concurrency, I've used Lock per resource (game) pattern

##Local run
Application starts on 8080 port

```java -jar ./target/kalah-0.0.1-SNAPSHOT.jar```

##Examples
In application present postman collection for first user and requests.http for second user  

##Api documentation
```http://localhost:8080/swagger-ui/index.html?configUrl=/application-openapi/swagger-config#/```

