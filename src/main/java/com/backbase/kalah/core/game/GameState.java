package com.backbase.kalah.core.game;

import com.backbase.kalah.core.game.event.Event;
import com.backbase.kalah.core.game.event.EventType;

import java.util.Map;

public interface GameState {

    EventType canHandle();

    GameState next(Event<Integer> event);

    Map<Integer, String> boardView(Player player);

    String description();

}

