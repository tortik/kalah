package com.backbase.kalah.core.game;

import com.backbase.kalah.core.game.event.EventType;
import com.backbase.kalah.core.game.exception.FailedToJoinException;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    public void initGameState() {
        Player one = new Player("one");
        Game game = new Game(one);

        assertNotNull(game.getId());
        assertEquals(one, game.getOne());
        assertNull(game.getTwo());
        assertEquals(EventType.PLAYER_JOINED, game.getCurrentState().canHandle());
    }

    @Test
    public void expectExceptionWhenPlayerJoinsToHimself() {
        Player one = new Player("one");
        Game game = new Game(one);

        assertThrows(FailedToJoinException.class, () -> game.join(one));
    }

    @Test
    public void shouldJoinToTheGame() {
        Player one = new Player("one");
        Game game = new Game(one);

        boolean joined = game.join(new Player("two"));
        assertTrue(joined);
    }

    @Test
    public void shouldFailToJoinOnStartedGame() {
        Game game = new Game(new Player("one"));
        game.join(new Player("two"));

        boolean joined = game.join(new Player("three"));
        assertFalse(joined);
    }

    @Test
    public void boardViewOnInitShouldBeEmpty() {
        Player one = new Player("one");
        Game game = new Game(one);

        assertTrue(game.getCurrentState().boardView(one).isEmpty());
    }

    @Test
    public void boardViewOnStartedGameShouldNotBeEmpty() {
        Player one = new Player("one");
        Game game = new Game(one);
        game.join(new Player("two"));

        assertFalse(game.getCurrentState().boardView(one).isEmpty());
    }

    @Test
    public void afterFirstPLayerMoveShouldChangeStateToAwaitingSecondUserMove() {
        Player one = new Player("one");
        Game game = new Game(one);
        game.join(new Player("two"));
        Map<Integer, String> boardView = game.move(one, 2);

        assertEquals(EventType.PLAYER_TWO_MOVE, game.getCurrentState().canHandle());
        assertFalse(boardView.isEmpty());
    }
}