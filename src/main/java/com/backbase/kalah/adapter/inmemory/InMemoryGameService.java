package com.backbase.kalah.adapter.inmemory;

import com.backbase.kalah.core.GameService;
import com.backbase.kalah.core.auth.AuthorizationService;
import com.backbase.kalah.core.game.Game;
import com.backbase.kalah.core.game.Player;
import com.backbase.kalah.core.game.event.EventType;
import com.backbase.kalah.core.game.exception.GameNotFound;
import com.backbase.kalah.core.game.exception.UnauthorizedException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class InMemoryGameService implements GameService {

    private final Map<String, Game> games = new ConcurrentHashMap<>();
    private final Map<String, Lock> locks = new ConcurrentHashMap<>();

    private AuthorizationService authorizationService;

    public InMemoryGameService(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @Override
    public String createGame(Player player) {
        Game game = new Game(player);
        games.put(game.getId(), game);
        locks.put(game.getId(), new ReentrantLock());
        return game.getId();
    }

    @Override
    public Map<Integer, String> move(String gameId, Player player, Integer pitIndex) {
        isGameExist(gameId);

        Map<Integer, String> boardView;
        Lock gameLock = locks.get(gameId);
        try {
            gameLock.lock();
            Game game = games.get(gameId);
            isPlayerAuthorizedToMove(player, game);
            boardView = game.move(player, pitIndex);
        } finally {
            gameLock.unlock();
        }
        return boardView;
    }


    @Override
    public boolean join(String gameId, Player second) {
        isGameExist(gameId);
        Lock gameLock = locks.get(gameId);
        try {
            gameLock.lock();
            Game game = games.get(gameId);

            return game.join(second);
        } finally {
            gameLock.unlock();
        }
    }

    @Override
    public List<String> findGamesToJoin() {
        return games.values().stream()
                .filter(g -> EventType.PLAYER_JOINED.equals(g.getCurrentState().canHandle()))
                .map(Game::getId)
                .collect(Collectors.toList());
    }

    private void isGameExist(String id) {
        if (!games.containsKey(id)) {
            throw new GameNotFound(String.format("Can't find game with id: %s", id));
        }
    }

    private void isPlayerAuthorizedToMove(Player player, Game game) {
        if (!authorizationService.isAuthorized(game, player)) {
            throw new UnauthorizedException(String.format("User %s is not authorized to move in the game %s", player.getName(), game.getId()));
        }
    }

}
