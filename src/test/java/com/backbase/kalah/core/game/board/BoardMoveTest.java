package com.backbase.kalah.core.game.board;

import com.backbase.kalah.core.game.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardMoveTest {

    @Test
    public void shouldMove6StonesOnNextKahals() {
        Player firstPlayer = new Player("first");
        Player secondPlayer = new Player("second");

        BoardItem board = new BoardItem(firstPlayer, secondPlayer);
        BoardMove boardMove = new BoardMove(board, new BoardValidation());

        boolean canMakeAdditionalMove = boardMove.move(2, firstPlayer);

        assertFalse(canMakeAdditionalMove);
        assertEquals(6, board.getKalahs().get(1).getStones());
        assertEquals(0, board.getKalahs().get(2).getStones());
        assertEquals(7, board.getKalahs().get(3).getStones());
        assertEquals(7, board.getKalahs().get(4).getStones());
        assertEquals(7, board.getKalahs().get(5).getStones());
        assertEquals(7, board.getKalahs().get(6).getStones());
        assertEquals(1, board.getKalahs().get(7).getStones());
        assertEquals(7, board.getKalahs().get(8).getStones());
    }

    @Test
    public void shouldHaveAdditionalMove() {
        Player firstPlayer = new Player("first");
        Player secondPlayer = new Player("second");

        BoardItem board = new BoardItem(firstPlayer, secondPlayer);
        BoardMove boardMove = new BoardMove(board, new BoardValidation());

        boolean canMakeAdditionalMove = boardMove.move(1, firstPlayer);

        assertTrue(canMakeAdditionalMove);
    }

    @Test
    public void shouldSkipOpponentStoreKahal() {
        Player firstPlayer = new Player("first");
        Player secondPlayer = new Player("second");

        BoardItem board = new BoardItem(firstPlayer, secondPlayer);
        BoardMove boardMove = new BoardMove(board, new BoardValidation());

        boardMove.move(1, firstPlayer);
        boardMove.move(2, firstPlayer);
        boardMove.move(3, firstPlayer);
        boardMove.move(6, firstPlayer);

        //make full cycle, should be 0 + 1
        assertEquals(1, board.getKalahs().get(1).getStones());
        assertEquals(0, board.getKalahs().get(2).getStones());
        assertEquals(0, board.getKalahs().get(3).getStones());
        assertEquals(9, board.getKalahs().get(4).getStones());
        assertEquals(9, board.getKalahs().get(5).getStones());
        assertEquals(0, board.getKalahs().get(6).getStones());
        //usecase when last stone puts in empty own kahal
        int opositeKahalStones = 8;
        assertEquals(4 + opositeKahalStones, board.getKalahs().get(7).getStones());
        assertEquals(9, board.getKalahs().get(8).getStones());
        //opponent store is empty, cause he didn't make any move
        assertEquals(0, board.getKalahs().get(0).getStones());
    }
}