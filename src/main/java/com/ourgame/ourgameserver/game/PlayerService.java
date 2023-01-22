package com.ourgame.ourgameserver.game;

import com.ourgame.ourgameserver.ws.model.user.UserService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class PlayerService {
    private final UserService userService;
    private final Set<Player> playerSet;

    public PlayerService(UserService userService) {
        this.userService = userService;
        this.playerSet = new HashSet<>();
    }

    public Player getPlayer(String username) {
        Player player = new Player(userService.loadUserByUsername(username));
        if (playerSet.contains(player)) {
            return player;
        }

        return new Player(userService.loadUserByUsername(username));
    }
}
