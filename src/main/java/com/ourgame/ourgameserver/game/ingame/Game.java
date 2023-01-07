package com.ourgame.ourgameserver.game.ingame;

import com.ourgame.ourgameserver.game.Player;
import com.ourgame.ourgameserver.game.pregame.Lobby;
import com.ourgame.ourgameserver.game.pregame.LobbyException;
import com.ourgame.ourgameserver.utils.observer.ObservableImpl;

import java.util.Set;


public class Game extends ObservableImpl {
    private final static int MAX_PLAYERS = 6;
    private Player host;
    private final Lobby lobby;
    private final Set<Player> players;
    private final Chat chat;
    private int theme;
    private int round;
    private int question;
    public Game(Lobby lobby) {
        this.lobby = lobby;
        this.host = lobby.getHost();
        this.players = lobby.getReadyPlayers().keySet();
        this.chat = new Chat();
        this.theme = 0;
        this.round = 0;
        this.question = 0;
    }

    public void changeHost(Player newHost) {
        if (players.contains(newHost)) {
            host = newHost;
            lobby.changeHost(newHost);
        }
    }

    public void deleteHost() {
        if (players.size() == 0) {
            // TODO delete game
        }
        players.remove(host);
        Player newHost = players.iterator().next();

        host = newHost;
        lobby.changeHost(newHost);
    }

    public void addPlayer(Player player) {
        if (players.size() >= MAX_PLAYERS) {
            throw new LobbyException("Max player limit is reached");
        }
        players.add(player);
        lobby.addPlayer(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
        lobby.removePlayer(player);
    }

}
