package com.backbase.kalah.core.game.board;

import com.backbase.kalah.core.game.Kalah;
import com.backbase.kalah.core.game.Player;
import io.vavr.Tuple2;

import java.util.Optional;

class BoardWinner {

    private BoardItem board;

    public BoardWinner(BoardItem board) {
        this.board = board;
    }

    public Optional<Tuple2<Player, Integer>> getWinner() {
        Kalah emptyHomes = firstPlayerFinished() ? board.getFirstStore() :
                secondPlayerFinished() ? board.getSecondStore() : null;
        if (emptyHomes == null) return Optional.empty();

        Kalah secondPlayerStore = emptyHomes == board.getFirstStore() ? board.getSecondStore() : board.getFirstStore();

        return emptyHomes.getStones() > 36 ? Optional.of(new Tuple2<>(emptyHomes.getOwner(), emptyHomes.getStones()))
                : Optional.of(new Tuple2<>(secondPlayerStore.getOwner(), 72 - emptyHomes.getStones()));
    }

    private boolean firstPlayerFinished() {
        return playerFinished(board, 1);
    }

    private boolean secondPlayerFinished() {
        return playerFinished(board, 7);
    }

    private boolean playerFinished(BoardItem board, int firstElement) {
        return board.getKalahs().stream().skip(firstElement).limit(6).allMatch(k -> k.getStones() == 0);
    }

}
