package com.backbase.kalah.core.game.state;

import com.backbase.kalah.core.game.GameState;
import com.backbase.kalah.core.game.Player;
import com.backbase.kalah.core.game.board.BoardAction;
import com.backbase.kalah.core.game.event.Event;
import com.backbase.kalah.core.game.event.EventType;
import io.vavr.Tuple;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FirstPlayerMoveStateTest {

    @Mock
    private BoardAction boardAction;

    @Test
    public void shouldChangeStateToSecondUserMove(){
        when(boardAction.move(anyInt(), any(Player.class))).thenReturn(false);

        GameState firstPlayerMoveState = new FirstPlayerMoveState(boardAction);
        GameState newState = firstPlayerMoveState.next(new Event<>(EventType.PLAYER_ONE_MOVE, 1, new Player("second")));

        assertEquals(EventType.PLAYER_TWO_MOVE, newState.canHandle());
    }

    @Test
    public void shouldNotChangeStateToSecondUserMoveDueToAdditionalMove(){
        when(boardAction.move(anyInt(), any(Player.class))).thenReturn(true);

        GameState firstPlayerMoveState = new FirstPlayerMoveState(boardAction);
        GameState newState = firstPlayerMoveState.next(new Event<>(EventType.PLAYER_ONE_MOVE, 1, new Player("second")));

        assertEquals(EventType.PLAYER_ONE_MOVE, newState.canHandle());
    }

    @Test
    public void shouldChangeStateToGameOver(){
        when(boardAction.move(anyInt(), any(Player.class))).thenReturn(true);
        when(boardAction.getWinner()).thenReturn(Optional.of(Tuple.of(new Player("one"), 37)));

        GameState firstPlayerMoveState = new FirstPlayerMoveState(boardAction);
        GameState newState = firstPlayerMoveState.next(new Event<>(EventType.PLAYER_ONE_MOVE, 1, new Player("second")));

        assertEquals(EventType.GAME_OVER, newState.canHandle());
    }

    @Test
    public void shouldReturnSameStateOnNotHandledEventType(){
        GameState firstPlayerMoveState = new SecondPlayerMoveState(boardAction);
        GameState newState = firstPlayerMoveState.next(new Event<>(EventType.GAME_OVER, 1, new Player("one")));

        assertEquals(firstPlayerMoveState, newState);
    }

}