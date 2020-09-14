package com.backbase.kalah.core.game.board.validation;

import com.backbase.kalah.core.game.Kalah;
import com.backbase.kalah.core.game.Player;

public interface BoardPlaceValidation {

    boolean canPlaceStone(Kalah kalah, Player player);

}
