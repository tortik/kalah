package com.backbase.kalah.core.game.state;

import com.backbase.kalah.core.game.GameState;
import com.backbase.kalah.core.game.Player;
import com.backbase.kalah.core.game.board.BoardAction;
import com.backbase.kalah.core.game.event.Event;
import com.backbase.kalah.core.game.event.EventType;
import io.vavr.Tuple2;

import java.util.Map;
import java.util.Optional;

import static com.backbase.kalah.core.game.event.EventType.PLAYER_TWO_MOVE;

public class SecondPlayerMoveState implements GameState {

    private BoardAction boardAction;

    public SecondPlayerMoveState(BoardAction boardAction) {
        this.boardAction = boardAction;
    }

    @Override
    public EventType canHandle() {
        return PLAYER_TWO_MOVE;
    }

    @Override
    public GameState next(Event<Integer> event) {
        if (event.getEventType() != PLAYER_TWO_MOVE) return this;

        boolean additionalMove = boardAction.move(event.getData(), event.getPlayer());

        Optional<Tuple2<Player, Integer>> winner = boardAction.getWinner();
        if (winner.isPresent()) {
            return new GameOveState(winner.get()._1(), winner.get()._2(), boardAction);
        }
        if (!additionalMove) return new FirstPlayerMoveState(boardAction);
        return this;
    }

    @Override
    public Map<Integer, String> boardView(Player player) {
        return boardAction.getPlayerBoardView(player);
    }

    @Override
    public String description() {
        return String.format("Second player %s move", boardAction.getTwo().getName());
    }
}
