package com.ourgame.ourgameserver.game;

import lombok.Getter;
import lombok.Setter;

import java.io.File;


/**
 * Class for player that is used in game, both in lobby and game
 * token is used to identify player (TODO)
 * TODO remove setter add custom setters
 * username is used to identify player in game and to link it to user in database
 */
@Getter
@Setter
public class Player {
    private String username;
    private String token;
    private String avatar;
    private int avgReactionTime;

    public Player(String username) {
        this.username = username;
    }
}
