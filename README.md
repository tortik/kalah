## Design
Application based on hexagon/onion architecture

Game based on state: one of four (FirstPlayerMoveState, GameOveState, SecondPlayerMoveState, WaitingSecondPlayerState)

For a concurrency, I've used Lock per resource (game) pattern

## Local run
Application starts on 8080 port

```java -jar ./target/kalah-0.0.1-SNAPSHOT.jar``` 

## Api documentation and Try It
```http://localhost:8080/swagger-ui/index.html?configUrl=/openapi/swagger-config#/```

