package com.backbase.kalah.core;

import com.backbase.kalah.core.game.Player;

import java.util.List;
import java.util.Map;

public interface GameService {

    String createGame(Player player);

    Map<Integer, String> move(String id, Player player, Integer pitIndex);

    boolean join(String id, Player second);

    List<String> findGamesToJoin();

}
