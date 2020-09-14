package com.backbase.kalah.core.game.board;

import com.backbase.kalah.core.game.Kalah;
import com.backbase.kalah.core.game.Player;
import io.vavr.Tuple2;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class BoardWinnerTest {

    @Test
    public void onStartNoOneWins() {
        Player firstPlayer = new Player("first");
        Player secondPlayer = new Player("second");

        BoardItem board = new BoardItem(firstPlayer, secondPlayer);
        BoardWinner boardWinner = new BoardWinner(board);

        assertFalse(boardWinner.getWinner().isPresent());
    }

    @Test
    public void firstPlayerHasEmptyHomeKahalsAndWins() {
        Player firstPlayer = new Player("first");
        Player secondPlayer = new Player("second");

        BoardItem board = new BoardItem(firstPlayer, secondPlayer);
        BoardWinner boardWinner = new BoardWinner(board);
        prepareBoard(board, 37);

        Tuple2<Player, Integer> winner = boardWinner.getWinner().get();
        assertEquals(firstPlayer, winner._1);
        assertEquals(37, winner._2);
    }

    @Test
    public void firstPlayerHasEmptyHomeKahalsButLoose() {
        Player firstPlayer = new Player("first");
        Player secondPlayer = new Player("second");

        BoardItem board = new BoardItem(firstPlayer, secondPlayer);
        BoardWinner boardWinner = new BoardWinner(board);
        prepareBoard(board, 35);

        Tuple2<Player, Integer> winner = boardWinner.getWinner().get();
        assertEquals(secondPlayer, winner._1);
        assertEquals(37, winner._2);
    }

    private void prepareBoard(BoardItem boardItem, int stones) {
        List<Kalah> kalahs = boardItem.getKalahs();
        IntStream.range(1, 7).forEach(i -> kalahs.get(i).extractAllStones());
        IntStream.range(1, stones + 1).forEach(i -> kalahs.get(7).incrementStone());
    }

}