package com.backbase.kalah.core.game.board.validation;

import com.backbase.kalah.core.game.Kalah;
import com.backbase.kalah.core.game.Player;
import com.backbase.kalah.core.game.exception.BoardValidationException;
import io.vavr.control.Either;

public interface BoardGetStonesValidation {

    Either<BoardValidationException, Boolean> isValid(Kalah kalah, Player player);
}
