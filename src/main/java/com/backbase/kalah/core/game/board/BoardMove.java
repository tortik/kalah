package com.backbase.kalah.core.game.board;

import com.backbase.kalah.core.game.Kalah;
import com.backbase.kalah.core.game.Player;

class BoardMove {

    private BoardItem board;
    private BoardValidation boardValidation;

    public BoardMove(BoardItem board, BoardValidation boardValidation) {
        this.board = board;
        this.boardValidation = boardValidation;
    }

    /**
     * Validates and moves stones by rules
     *
     * @param kalahIndex chosen Kalah index
     * @param current    player who make move
     * @return is player has additional move
     */
    public boolean move(int kalahIndex, Player current) {
        int internalIndex = getInternalIndex(kalahIndex);

        boardValidation.canGetStonesFromKalah(board.getKalahs().get(internalIndex), current);
        Kalah lastKalah = placeStones(current, internalIndex);
        lastStoneLandsInEmptyPlayerKalah(current, lastKalah);

        return lastKalah == board.getFirstStore() || lastKalah == board.getSecondStore();
    }

    private void lastStoneLandsInEmptyPlayerKalah(Player current, Kalah lastKalah) {
        int oppositeKalahStones;
        if (lastKalah.getStones() == 1 && lastKalah.getOwner().equals(current)
                && (oppositeKalahStones = getOppositeKalahStones(lastKalah)) != 0) {

            int stones = lastKalah.extractAllStones() + oppositeKalahStones;

            Kalah store = board.getFirstStore().getOwner().equals(current) ? board.getFirstStore() : board.getSecondStore();
            store.addStones(stones);
        }
    }

    private int getOppositeKalahStones(Kalah lastKalah) {
        return board.getKalahs().get(getOppositeKalah(lastKalah.getIndex())).extractAllStones();
    }

    private int getOppositeKalah(int lastKalahIdx) {
        return 14 - lastKalahIdx;
    }

    private Kalah placeStones(Player current, int internalIndex) {
        Kalah lastKalah = board.getKalahs().get(internalIndex);
        int stones = lastKalah.extractAllStones();
        int nextIndex = internalIndex;
        while (stones > 0) {
            nextIndex = getInternalIndex(++nextIndex);
            Kalah nextKalah = board.getKalahs().get(nextIndex);
            if (boardValidation.canPlaceStone(nextKalah, current)) {
                stones--;
                nextKalah.incrementStone();
                lastKalah = nextKalah;
            }
        }
        return lastKalah;
    }

    private int getInternalIndex(int kalahIndex) {
        return kalahIndex % 14;
    }



}
