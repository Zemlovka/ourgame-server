package com.ourgame.ourgameserver.game.pregame;

import com.ourgame.ourgameserver.game.Player;
import com.ourgame.ourgameserver.game.exceptions.LobbyException;
import com.ourgame.ourgameserver.game.pack.Pack;
import com.ourgame.ourgameserver.utils.observer.ObservableImpl;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;


/**
 * A lobby is a pre-game room where players can join and wait for the game to start
 * A lobby is created by a host with name, pack, password and privacy set
 * TODO add description to observer and conversion to game
 */
@Getter
@Setter
public class Lobby extends ObservableImpl {
    private final static int MAX_PLAYERS = 6;
    private final static int MIN_PLAYERS = 3;
    private final int id;
    private String name;
    private Player host;
    private Pack pack;
    private final Map<String, Player> players;
    private int maxPlayers;
    private final String password;
    private boolean isPrivate;
    private LocalDateTime creationDate;
    private List<String> tags;

    public Lobby(int id, String name, Player host, Pack pack, String password, int maxPlayers) {
        players = new HashMap<>();
        players.put(host.getUsername(), host);
        this.id = id;
        this.name = name;
        this.host = host;
        this.pack = pack;
        this.tags = pack.getTags();
        this.password = password;
        if (maxPlayers > MAX_PLAYERS) {
            this.maxPlayers = MAX_PLAYERS;
        } else if (maxPlayers < MIN_PLAYERS) {
            this.maxPlayers = MIN_PLAYERS;
        } else {
            this.maxPlayers = maxPlayers;
        }
        this.creationDate = LocalDateTime.now();
    }

    public void changeHost(Player newHost) {
        if (players.containsValue(newHost)) {
            host = newHost;
        }
    }

    public void deleteHost() {
        if (players.size() == 0) {
            // TODO delete lobby
        }
        players.remove(host.getUsername());
        this.host = players.values().iterator().next();
    }

    public void addPlayer(Player player) {
        if (players.size() >= maxPlayers) {
            throw new LobbyException("Max player limit is reached");
        }
        players.put(player.getUsername(), player);
    }

    public void removePlayer(Player user) {
        players.remove(user);
    }

    public void setPlayerReadyStatus(Player player, boolean isReady) {
        if (players.containsValue(player)) {
            players.get(player.getUsername()).setReady(isReady);
        }
    }

    public boolean arePlayersReady() {
        for (Player player : players.values()) {
            if (!player.isReady()) {
                return false;
            }
        }
        return true;
    }

    //TODO add exceptions
    public boolean isConnectable(Player player) {
//        return players.size() < maxPlayers && !players.containsValue(player);
        return true;
    }
    public boolean isPrivate() {
        return isPrivate;
    }

    public int getPlayerCount() {
        return players.size();
    }

    public Set<Player> getPlayers() {
        return new HashSet<>(players.values());
    }

}
