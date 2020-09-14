package com.backbase.kalah.core.auth;

import com.backbase.kalah.core.game.Game;
import com.backbase.kalah.core.game.Player;

public interface AuthorizationService {

    boolean isAuthorized(Game game, Player player);

}
