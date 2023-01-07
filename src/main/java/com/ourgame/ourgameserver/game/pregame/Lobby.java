package com.ourgame.ourgameserver.game.pregame;

import com.ourgame.ourgameserver.game.Player;
import com.ourgame.ourgameserver.game.pack.Pack;
import com.ourgame.ourgameserver.utils.observer.ObservableImpl;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@Getter
@Setter
public class Lobby extends ObservableImpl {
    private final static int MAX_PLAYERS = 6;
    private String name;
    private Player host;
    private Pack pack;
    private Map<Player, Boolean> readyPlayers;
    private String password;
    private LocalDateTime creationDate;
    private List<String> tags;

    public Lobby(String name, Player host, Pack pack, String password) {
        this.name = name;
        this.host = host;
        readyPlayers.put(host, false);
        this.pack = pack;
        this.password = password;
        this.creationDate = LocalDateTime.now();
    }

    public void changeHost(Player newHost) {
        if (readyPlayers.containsKey(newHost)) {
            host = newHost;
        }
    }

    public void deleteHost() {
        if (readyPlayers.keySet().size() == 0) {
            // TODO delete lobby
        }
        readyPlayers.remove(host);
        this.host = readyPlayers.keySet().iterator().next();
    }

    public void addPlayer(Player players) {
        if (readyPlayers.keySet().size() >= MAX_PLAYERS) {
            throw new LobbyException("Max player limit is reached");
        }
        readyPlayers.put(players, false);
    }

    public void removePlayer(Player user) {
        readyPlayers.remove(user);
    }

    public void setPlayerReady(Player user) {
        if (readyPlayers.containsKey(user)) {
            readyPlayers.put(user, true);
        }
    }

}
