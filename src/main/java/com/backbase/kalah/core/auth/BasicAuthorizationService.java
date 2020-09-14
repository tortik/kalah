package com.backbase.kalah.core.auth;

import com.backbase.kalah.core.game.Game;
import com.backbase.kalah.core.game.Player;

public class BasicAuthorizationService implements AuthorizationService {

    @Override
    public boolean isAuthorized(Game game, Player player) {
        return player.equals(game.getOne()) || player.equals(game.getTwo());
    }
}
