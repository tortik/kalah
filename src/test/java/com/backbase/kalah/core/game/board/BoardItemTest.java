package com.backbase.kalah.core.game.board;

import com.backbase.kalah.core.game.Kalah;
import com.backbase.kalah.core.game.KalahType;
import com.backbase.kalah.core.game.Player;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BoardItemTest {

    @Test
    public void checkBoardInitStructure() {
        Player one = new Player("one");
        Player two = new Player("two");
        BoardItem board = new BoardItem(one, two);

        List<Kalah> kalahs = board.getKalahs();
        boolean firstPlayerKalahsMatch = IntStream.range(1, 7).boxed()
                .allMatch(i -> kalahs.get(i).equals(new Kalah(KalahType.HOME, one, i)));
        boolean secondPlayerKalahsMatch = IntStream.range(8, 14).boxed()
                .allMatch(i -> kalahs.get(i).equals(new Kalah(KalahType.HOME, two, i)));


        assertTrue(firstPlayerKalahsMatch);
        assertTrue(secondPlayerKalahsMatch);
        assertEquals(new Kalah(KalahType.STORE, one, 7, 0), board.getFirstStore());
        assertEquals(new Kalah(KalahType.STORE, two, 0, 0), board.getSecondStore());
    }
}