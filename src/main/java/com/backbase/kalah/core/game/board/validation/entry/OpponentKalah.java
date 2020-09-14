package com.backbase.kalah.core.game.board.validation.entry;

import com.backbase.kalah.core.game.Kalah;
import com.backbase.kalah.core.game.Player;
import com.backbase.kalah.core.game.board.validation.BoardGetStonesValidation;
import com.backbase.kalah.core.game.exception.BoardValidationException;
import io.vavr.control.Either;

public class OpponentKalah implements BoardGetStonesValidation {

    @Override
    public Either<BoardValidationException, Boolean> isValid(Kalah kalah, Player player) {
        return kalah.getOwner().equals(player) ? Either.right(true) :
                Either.left(new BoardValidationException("Opponent Kalah can't be chosen for a move"));
    }
}
