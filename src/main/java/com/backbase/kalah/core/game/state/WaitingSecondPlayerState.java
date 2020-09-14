package com.backbase.kalah.core.game.state;

import com.backbase.kalah.core.game.GameState;
import com.backbase.kalah.core.game.Player;
import com.backbase.kalah.core.game.board.BoardAction;
import com.backbase.kalah.core.game.event.Event;
import com.backbase.kalah.core.game.event.EventType;

import java.util.Collections;
import java.util.Map;

import static com.backbase.kalah.core.game.event.EventType.PLAYER_JOINED;

public class WaitingSecondPlayerState implements GameState {

    private Player one;

    public WaitingSecondPlayerState(Player one) {
        this.one = one;
    }

    @Override
    public EventType canHandle() {
        return PLAYER_JOINED;
    }

    @Override
    public GameState next(Event<Integer> event) {
        if (event.getEventType() == PLAYER_JOINED) {
            return new FirstPlayerMoveState(new BoardAction(one, event.getPlayer()));
        }
        return this;
    }

    @Override
    public Map<Integer, String> boardView(Player player) {
        return Collections.emptyMap();
    }

    @Override
    public String description() {
        return "Waiting for second player to join";
    }
}
