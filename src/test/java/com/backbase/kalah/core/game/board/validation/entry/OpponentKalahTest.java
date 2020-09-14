package com.backbase.kalah.core.game.board.validation.entry;

import com.backbase.kalah.core.game.Kalah;
import com.backbase.kalah.core.game.KalahType;
import com.backbase.kalah.core.game.Player;
import com.backbase.kalah.core.game.exception.BoardValidationException;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OpponentKalahTest {

    @Test
    public void shouldReturnExceptionIfPlayerSelectsOpponentKalah(){
        Player player = new Player("test");

        Either<BoardValidationException, Boolean> result =  new OpponentKalah().isValid(getKalah(player, KalahType.STORE), new Player("second"));

        assertEquals(BoardValidationException.class, result.getLeft().getClass());
        assertEquals("Opponent Kalah can't be chosen for a move", result.getLeft().getMessage());
    }

    @Test
    public void shouldPassWithOwnKalah(){
        Player player = new Player("test");

        Either<BoardValidationException, Boolean> result =  new OpponentKalah().isValid(getKalah(player, KalahType.HOME), player);

        assertTrue(result.get());
    }

    private Kalah getKalah(Player player, KalahType type) {
        return new Kalah(type, player, 1, 6);
    }
}