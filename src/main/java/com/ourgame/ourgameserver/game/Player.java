package com.ourgame.ourgameserver.game;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ourgame.ourgameserver.utils.observer.ObservableImpl;
import com.ourgame.ourgameserver.ws.model.user.UserJpaEntity;
import lombok.Getter;
import lombok.Setter;


/**
 * Class for player that is used in game, both in lobby and game
 * token is used to identify player TODO
 * TODO remove setter add custom setters
 * username is used to identify player in game and to link it to user in database
 */
@Getter
@Setter
public class Player extends ObservableImpl {
    private String username;
    @JsonIgnore
    private String token; // TODO add method of authentication either by token or creating new token with password and username and comparing it
    private String avatar;
    @JsonIgnore
    private int avgReactionTime;
    private boolean isReady;

    public Player(UserJpaEntity user) {
        this.username = user.getUsername();
        this.avatar = user.getAvatar();
        this.isReady = false;
    }

    public Player(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        return getUsername().equals(player.getUsername());
    }

    @Override
    public int hashCode() {
        return getUsername().hashCode();
    }
}
