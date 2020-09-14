package com.backbase.kalah.core.game;

import com.backbase.kalah.core.game.event.Event;
import com.backbase.kalah.core.game.event.EventType;
import com.backbase.kalah.core.game.exception.FailedToJoinException;
import com.backbase.kalah.core.game.state.WaitingSecondPlayerState;

import java.util.Map;
import java.util.UUID;

public class Game {
    private String id;
    private GameState state;
    private Player one;
    private Player two;

    public Game(Player playerOne) {
        id = UUID.randomUUID().toString();
        state = new WaitingSecondPlayerState(playerOne);
        one = playerOne;
    }

    public Map<Integer, String> move(Player current, Integer userSpecificKalahIndex) {
        state = state.next(moveEvent(current, userSpecificKalahIndex));
        //notify that state was changed
        return state.boardView(current);
    }

    public boolean join(Player two) {
        checkPlayWithHimself(two);

        if (state.canHandle() == EventType.PLAYER_JOINED) {
            state = state.next(new Event<>(EventType.PLAYER_JOINED, two));
            this.two = two;
            return true;
            //notify that state was changed
        }
        return false;
    }

    public GameState getCurrentState() {
        return state;
    }

    public String getId() {
        return id;
    }

    public Player getOne() {
        return one;
    }

    public Player getTwo() {
        return two;
    }

    private void checkPlayWithHimself(Player two) {
        if (one.equals(two)) {
            throw new FailedToJoinException("Player can't play with himself");
        }
    }

    private Event<Integer> moveEvent(Player current, Integer userSpecificKalahIndex) {
        if (one.equals(current)) {
            return new Event<>(EventType.PLAYER_ONE_MOVE, userSpecificKalahIndex, current);
        } else {
            return new Event<>(EventType.PLAYER_TWO_MOVE, userSpecificKalahIndex, current);
        }
    }

}
