package com.backbase.kalah.adapter.http;

import com.backbase.kalah.core.GameService;
import com.backbase.kalah.core.game.Player;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/games")
@SecurityRequirement(name = "api")
public class GameController {

    private GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> findAvailableGames() {

        List<Map<String, String>> responseEntity = gameService.findGamesToJoin().stream()
                .map(id -> Map.of("id", id, "uri", getAbsoluteUri(id)))
                .collect(Collectors.toList());

        return ResponseEntity.ok(Map.of("games", responseEntity));
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createGame(Principal principal) {
        Player player = new Player(principal.getName());

        String gameId = gameService.createGame(player);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("id", gameId, "uri", getAbsoluteUri(gameId)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> join(@PathVariable("id") String id, Principal principal) {
        Player player = new Player(principal.getName());

        boolean result = gameService.join(id, player);

        return ResponseEntity.ok(Map.of("success", result));
    }

    @PutMapping("/{gameId}/pits/{pitId}")
    public ResponseEntity<Map<String, Object>> move(@PathVariable("gameId") String gameId,
                                                    @PathVariable("pitId") Integer pitIndex, Principal principal) {
        Player player = new Player(principal.getName());

        Map<Integer, String> boardView = gameService.move(gameId, player, pitIndex);

        return ResponseEntity.ok(Map.of("id", gameId, "uri", getAbsoluteUri(gameId), "status", boardView));
    }

    private String getAbsoluteUri(String gameId) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/games/{id}").build(gameId).toString();
    }

}
