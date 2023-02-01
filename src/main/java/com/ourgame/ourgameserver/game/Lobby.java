package com.ourgame.ourgameserver.game;

import com.ourgame.ourgameserver.game.exceptions.LobbyException;
import com.ourgame.ourgameserver.game.pack.Package;
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
    private final static int MAX_PLAYERS_ALLOWED = 6;
    private final static int MIN_PLAYERS_ALLOWED = 3;
    private final int playerLimit;
    private final int id;
    private final String name;
    private Player host;
    private final Map<String, Player> players;
    private final Package pack;
    private final String password;
    private final boolean isPrivate;
    private final LocalDateTime creationDate;
    private final LobbyService lobbyService;

    public Lobby(int id, String name, Player host, Package pack,
                 String password, boolean isPrivate, int playerLimit, LobbyService lobbyService) {
        players = new HashMap<>();
        players.put(host.getUsername(), host);
        this.id = id;
        this.name = name;
        this.host = host;
        this.pack = pack;
        this.password = password;
        this.isPrivate = isPrivate;
        if (playerLimit > MAX_PLAYERS_ALLOWED)
            this.playerLimit = MAX_PLAYERS_ALLOWED;
        else
            this.playerLimit = Math.max(playerLimit, MIN_PLAYERS_ALLOWED);
        this.creationDate = LocalDateTime.now();
        this.lobbyService = lobbyService;
    }

    public void changeHost(Player newHost) {
        if (players.containsValue(newHost)) {
            host = newHost;
        }
    }

    public void deleteHost() {
        if (players.size() == 0) {
            lobbyService.deleteLobby(lobbyService.getLobbyId(this));
        }
        players.remove(host.getUsername());
        this.host = players.values().iterator().next();
    }

    public void addPlayer(Player player) {
        if (players.size() >= playerLimit) {
            throw new LobbyException("Max player limit is reached");
        }
        players.put(player.getUsername(), player);
    }

    public void removePlayer(Player user) {
        if (user.equals(host)) {
            deleteHost();
        }
        else {
            players.remove(user.getUsername());
        }
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

    public List<String> getTags() {
        return pack.getTags();
    }

}
