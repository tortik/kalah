package com.backbase.kalah.core.game.board;

import com.backbase.kalah.core.game.Kalah;
import com.backbase.kalah.core.game.Player;
import com.backbase.kalah.core.game.board.validation.BoardGetStonesValidation;
import com.backbase.kalah.core.game.board.validation.BoardPlaceValidation;
import com.backbase.kalah.core.game.board.validation.entry.EmptyKalah;
import com.backbase.kalah.core.game.board.validation.entry.KalahStore;
import com.backbase.kalah.core.game.board.validation.entry.OpponentKalah;
import com.backbase.kalah.core.game.board.validation.move.KalahOpponentStore;
import com.backbase.kalah.core.game.exception.BoardValidationException;
import io.vavr.control.Either;

import java.util.List;

class BoardValidation {

    private final List<BoardGetStonesValidation> entryValidations;
    private final BoardPlaceValidation moveValidations;

    BoardValidation() {
        this.entryValidations = List.of(new EmptyKalah(), new KalahStore(), new OpponentKalah());
        this.moveValidations = new KalahOpponentStore();
    }

    void canGetStonesFromKalah(Kalah currentKalah, Player current) {
        Either<BoardValidationException, Boolean> validEntry = entryValidations.stream()
                .map(it -> it.isValid(currentKalah, current))
                .filter(Either::isLeft).findFirst()
                .orElse(Either.right(true));
        validEntry.getOrElseThrow(validEntry::getLeft);
    }

    boolean canPlaceStone(Kalah nextKalah, Player current) {
        return moveValidations.canPlaceStone(nextKalah, current);
    }

}
