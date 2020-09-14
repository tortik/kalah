package com.backbase.kalah.core.game.board.validation.move;

import com.backbase.kalah.core.game.Kalah;
import com.backbase.kalah.core.game.KalahType;
import com.backbase.kalah.core.game.Player;
import com.backbase.kalah.core.game.board.validation.entry.OpponentKalah;
import com.backbase.kalah.core.game.exception.BoardValidationException;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KalahOpponentStoreTest {

    @Test
    public void shouldReturnFalseOnOpponentStoreKalah(){
        Player player = new Player("test");

        boolean result =  new KalahOpponentStore().canPlaceStone(getKalah(player, KalahType.STORE), new Player("second"));

        assertFalse(result);
    }

    @Test
    public void shouldPassWithOwnStoreKalah(){
        Player player = new Player("test");

        boolean result =  new KalahOpponentStore().canPlaceStone(getKalah(player, KalahType.STORE), player);

        assertTrue(result);
    }

    private Kalah getKalah(Player player, KalahType type) {
        return new Kalah(type, player, 1, 6);
    }

}