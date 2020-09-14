package com.backbase.kalah.core.game.event;

import com.backbase.kalah.core.game.Player;

public class Event<T> {

    private EventType eventType;
    private T data;
    private Player player;

    public Event(EventType eventType, T data, Player player) {
        this.eventType = eventType;
        this.data = data;
        this.player = player;
    }
    public Event(EventType eventType, Player player) {
        this.eventType = eventType;
        this.player = player;
    }

    public EventType getEventType() {
        return eventType;
    }

    public T getData() {
        return data;
    }

    public Player getPlayer() {
        return player;
    }
}
