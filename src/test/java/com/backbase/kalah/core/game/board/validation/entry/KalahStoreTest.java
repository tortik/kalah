package com.backbase.kalah.core.game.board.validation.entry;

import com.backbase.kalah.core.game.Kalah;
import com.backbase.kalah.core.game.KalahType;
import com.backbase.kalah.core.game.Player;
import com.backbase.kalah.core.game.exception.BoardValidationException;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KalahStoreTest {

    @Test
    public void shouldReturnExceptionIfPlayerSelectsStoreKalah(){
        Player player = new Player("test");

        Either<BoardValidationException, Boolean> result =  new KalahStore().isValid(getKalah(player, KalahType.STORE), player);

        assertEquals(BoardValidationException.class, result.getLeft().getClass());
        assertEquals("Store Kalah can't be chosen for a move", result.getLeft().getMessage());
    }

    @Test
    public void shouldPassWithNonStoreKalah(){
        Player player = new Player("test");

        Either<BoardValidationException, Boolean> result =  new KalahStore().isValid(getKalah(player, KalahType.HOME), player);

        assertTrue(result.get());
    }

    private Kalah getKalah(Player player, KalahType type) {
        return new Kalah(type, player, 1, 6);
    }

}