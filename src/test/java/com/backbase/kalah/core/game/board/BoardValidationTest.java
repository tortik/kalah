package com.backbase.kalah.core.game.board;

import com.backbase.kalah.core.game.Kalah;
import com.backbase.kalah.core.game.KalahType;
import com.backbase.kalah.core.game.Player;
import com.backbase.kalah.core.game.exception.BoardValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class BoardValidationTest {

    @Test
    public void shouldThrowExceptionIfPlayerSelectsEmptyKalah() {
        Player player = new Player("test");

        assertThrows(BoardValidationException.class, () -> new BoardValidation()
                .canGetStonesFromKalah(new Kalah(KalahType.HOME, player, 1, 0), player));
    }

    @Test
    public void shouldThrowExceptionIfPlayerSelectsStoreKalah() {
        Player player = new Player("test");

        assertThrows(BoardValidationException.class, () -> new BoardValidation()
                .canGetStonesFromKalah(new Kalah(KalahType.STORE, player, 1, 1), player));
    }

    @Test
    public void shouldThrowExceptionIfPlayerSelectsOpponentKalah() {
        Player player = new Player("test");

        assertThrows(BoardValidationException.class, () -> new BoardValidation()
                .canGetStonesFromKalah(new Kalah(KalahType.HOME, new Player("second"), 1, 1), player));
    }

    @Test
    public void shouldPass() {
        Player player = new Player("test");

        new BoardValidation()
                .canGetStonesFromKalah(new Kalah(KalahType.HOME, player, 1, 1), player);
    }

}