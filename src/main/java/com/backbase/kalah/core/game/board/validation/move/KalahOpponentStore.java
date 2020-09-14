package com.backbase.kalah.core.game.board.validation.move;

import com.backbase.kalah.core.game.Kalah;
import com.backbase.kalah.core.game.KalahType;
import com.backbase.kalah.core.game.Player;
import com.backbase.kalah.core.game.board.validation.BoardPlaceValidation;

public class KalahOpponentStore implements BoardPlaceValidation {

    @Override
    public boolean canPlaceStone(Kalah kalah, Player player) {
        return !(kalah.getKalahType() == KalahType.STORE && !kalah.getOwner().equals(player));
    }
}
