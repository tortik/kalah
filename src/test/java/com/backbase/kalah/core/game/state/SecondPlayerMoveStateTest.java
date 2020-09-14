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
class SecondPlayerMoveStateTest {

    @Mock
    private BoardAction boardAction;

    @Test
    public void shouldChangeStateToFirstUserMove(){
        when(boardAction.move(anyInt(), any(Player.class))).thenReturn(false);

        GameState secondPlayerMoveState = new SecondPlayerMoveState(boardAction);
        GameState newState = secondPlayerMoveState.next(new Event<>(EventType.PLAYER_TWO_MOVE, 1, new Player("one")));

        assertEquals(EventType.PLAYER_ONE_MOVE, newState.canHandle());
    }

    @Test
    public void shouldReturnSameStateOnNotHandledEventType(){
        GameState secondPlayerMoveState = new SecondPlayerMoveState(boardAction);
        GameState newState = secondPlayerMoveState.next(new Event<>(EventType.GAME_OVER, 1, new Player("one")));

        assertEquals(secondPlayerMoveState, newState);
    }

    @Test
    public void shouldNotChangeStateToFirstUserMoveDueToAdditionalMove(){
        when(boardAction.move(anyInt(), any(Player.class))).thenReturn(true);

        GameState secondPlayerMoveState = new SecondPlayerMoveState(boardAction);
        GameState newState = secondPlayerMoveState.next(new Event<>(EventType.PLAYER_TWO_MOVE, 1, new Player("second")));

        assertEquals(EventType.PLAYER_TWO_MOVE, newState.canHandle());
    }

    @Test
    public void shouldChangeStateToGameOver(){
        when(boardAction.move(anyInt(), any(Player.class))).thenReturn(true);
        when(boardAction.getWinner()).thenReturn(Optional.of(Tuple.of(new Player("one"), 37)));

        GameState secondPlayerMoveState = new SecondPlayerMoveState(boardAction);
        GameState newState = secondPlayerMoveState.next(new Event<>(EventType.PLAYER_TWO_MOVE, 1, new Player("one")));

        assertEquals(EventType.GAME_OVER, newState.canHandle());
    }

}