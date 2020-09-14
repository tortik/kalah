package com.backbase.kalah.core.game.state;

import com.backbase.kalah.core.game.GameState;
import com.backbase.kalah.core.game.Player;
import com.backbase.kalah.core.game.event.Event;
import com.backbase.kalah.core.game.event.EventType;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class WaitingSecondPlayerStateTest {

    @Test
    public void shouldReturnNewState() {
        Player one = new Player("one");
        GameState waitingState = new WaitingSecondPlayerState(one);
        assertEquals(EventType.PLAYER_JOINED, waitingState.canHandle());

        Map<Integer, String> emptyView = waitingState.boardView(one);
        assertEquals(0, emptyView.size());

        GameState newState = waitingState.next(new Event<>(EventType.PLAYER_JOINED, new Player("two")));

        assertEquals(EventType.PLAYER_ONE_MOVE, newState.canHandle());
        assertNotEquals(waitingState, newState);
    }

    @Test
    public void shouldReturnTheSameState() {
        Player one = new Player("one");
        GameState waitingState = new WaitingSecondPlayerState(one);
        GameState newState = waitingState.next(new Event<>(EventType.PLAYER_ONE_MOVE, new Player("two")));

        assertEquals(waitingState, newState);
    }

}