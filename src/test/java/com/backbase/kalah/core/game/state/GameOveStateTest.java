package com.backbase.kalah.core.game.state;

import com.backbase.kalah.core.game.GameState;
import com.backbase.kalah.core.game.Player;
import com.backbase.kalah.core.game.board.BoardAction;
import com.backbase.kalah.core.game.event.Event;
import com.backbase.kalah.core.game.event.EventType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameOveStateTest {

    @Test
    public void gameOverIsLastStateShouldNotChangeOnAnyEvent(){
        Player one = new Player("one");
        Player two = new Player("two");
        GameState secondPlayerMoveState = new GameOveState(one, 37, new BoardAction(one, two));
        GameState newState = secondPlayerMoveState.next(new Event<>(EventType.GAME_OVER, 1, one));

        assertEquals(secondPlayerMoveState, newState);
    }

}