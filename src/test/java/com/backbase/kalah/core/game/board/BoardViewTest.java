package com.backbase.kalah.core.game.board;

import com.backbase.kalah.core.game.Player;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.TreeMap;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class BoardViewTest {

    @Test
    public void shouldDisplayBoardFromFirstPlayerSide() {
        Player firstPlayer = new Player("first");
        Player secondPlayer = new Player("second");

        BoardItem board = new BoardItem(firstPlayer, secondPlayer);
        BoardView boardView = new BoardView(board);
        board.getKalahs().get(1).extractAllStones();

        assertEquals(firstPlayerView(boardView), boardView.firstPlayerView());
    }

    @Test
    public void shouldDisplayBoardFromSecondPlayerSide() {
        Player firstPlayer = new Player("first");
        Player secondPlayer = new Player("second");

        BoardItem board = new BoardItem(firstPlayer, secondPlayer);
        BoardView boardView = new BoardView(board);
        board.getKalahs().get(1).extractAllStones();
        board.getKalahs().get(13).extractAllStones();

        assertEquals(secondPlayerView(boardView), boardView.secondPlayerView());
    }

    private Map<Integer, String> firstPlayerView(BoardView boardView) {
        Map<Integer, String> expectedView = new TreeMap<>();
        expectedView.put(1, "0");
        IntStream.range(2, 8).forEach(i-> expectedView.put(i, "6"));
        expectedView.put(7, "0");
        IntStream.range(8, 14).forEach(i-> expectedView.put(i, "6"));
        expectedView.put(14, "0");
        return expectedView;
    }

    private Map<Integer, String> secondPlayerView(BoardView boardView) {
        Map<Integer, String> expectedView = new TreeMap<>();
        IntStream.range(1, 6).forEach(i-> expectedView.put(i, "6"));
        expectedView.put(6, "0");
        expectedView.put(7, "0");
        expectedView.put(8, "0");
        IntStream.range(9, 14).forEach(i-> expectedView.put(i, "6"));
        expectedView.put(14, "0");
        return expectedView;
    }
}