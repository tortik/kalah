package com.backbase.kalah.core.game.board.validation.entry;

import com.backbase.kalah.core.game.Kalah;
import com.backbase.kalah.core.game.Player;
import com.backbase.kalah.core.game.board.validation.BoardGetStonesValidation;
import com.backbase.kalah.core.game.exception.BoardValidationException;
import io.vavr.control.Either;

public class EmptyKalah implements BoardGetStonesValidation {
    @Override
    public Either<BoardValidationException, Boolean> isValid(Kalah kalah, Player player) {
        return kalah.getStones() == 0 ? Either.left(new BoardValidationException("Empty Kalah can't be chosen for a move"))
                : Either.right(true);
    }
}
