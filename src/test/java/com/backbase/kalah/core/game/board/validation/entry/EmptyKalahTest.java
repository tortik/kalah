package com.backbase.kalah.core.game.board.validation.entry;

import com.backbase.kalah.core.game.Kalah;
import com.backbase.kalah.core.game.KalahType;
import com.backbase.kalah.core.game.Player;
import com.backbase.kalah.core.game.exception.BoardValidationException;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmptyKalahTest {

    @Test
    public void shouldReturnExceptionIfPlayerSelectsEmptyKalah(){
        Player player = new Player("test");

        Either<BoardValidationException, Boolean> result =  new EmptyKalah().isValid(getKalah(player, 0), player);

        assertEquals(BoardValidationException.class, result.getLeft().getClass());
        assertEquals("Empty Kalah can't be chosen for a move", result.getLeft().getMessage());
    }
    @Test
    public void shouldPassWithNonEmptyKalah(){
        Player player = new Player("test");

        Either<BoardValidationException, Boolean> result =  new EmptyKalah().isValid(getKalah(player, 1), player);

        assertTrue(result.get());
    }

    private Kalah getKalah(Player player, int stones) {
        return new Kalah(KalahType.HOME, player, 1, stones);
    }

}