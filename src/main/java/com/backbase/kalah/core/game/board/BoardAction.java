package com.backbase.kalah.core.game.board;

import com.backbase.kalah.core.game.Player;
import io.vavr.Tuple2;

import java.util.Map;
import java.util.Optional;

public class BoardAction {
    private BoardItem board;
    private BoardMove move;
    private BoardWinner winner;
    private BoardView view;
    private Player one;
    private Player two;

    public BoardAction(Player one, Player two) {
        this.one = one;
        this.two = two;
        this.board = new BoardItem(one, two);
        this.winner = new BoardWinner(board);
        this.view = new BoardView(board);
        this.move = new BoardMove(board, new BoardValidation());
    }

    public Optional<Tuple2<Player, Integer>> getWinner() {
        return winner.getWinner();
    }

    public boolean move(int kalahIndex, Player current) {
        return move.move(kalahIndex, current);
    }

    public Map<Integer, String> getPlayerBoardView(Player player) {
        return player.equals(one) ? view.firstPlayerView() : view.secondPlayerView();
    }

    public Player getOne() {
        return one;
    }

    public Player getTwo() {
        return two;
    }

}
