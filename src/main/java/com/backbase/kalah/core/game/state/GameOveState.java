package com.backbase.kalah.core.game.state;

import com.backbase.kalah.core.game.GameState;
import com.backbase.kalah.core.game.Player;
import com.backbase.kalah.core.game.board.BoardAction;
import com.backbase.kalah.core.game.event.Event;
import com.backbase.kalah.core.game.event.EventType;

import java.util.Map;

import static com.backbase.kalah.core.game.event.EventType.GAME_OVER;

public class GameOveState implements GameState {

    public static final String WINNER_DESCRIPTION = "Game Over! Player %s wins with %d stones";
    private Player winner;
    private int stones;
    private BoardAction boardAction;

    public GameOveState(Player winner, int stones, BoardAction boardAction) {
        this.winner = winner;
        this.stones = stones;
        this.boardAction = boardAction;
    }

    @Override
    public EventType canHandle() {
        return GAME_OVER;
    }

    @Override
    public GameState next(Event<Integer> event) {
        return this;
    }

    @Override
    public Map<Integer, String> boardView(Player player) {
        return boardAction.getPlayerBoardView(player);
    }

    @Override
    public String description() {
        return String.format(WINNER_DESCRIPTION, winner.getName(), stones);
    }
}
