package com.ourgame.ourgameserver.game;

import com.ourgame.ourgameserver.game.exceptions.LobbyException;
import com.ourgame.ourgameserver.game.exceptions.LobbyNotFoundException;
import com.ourgame.ourgameserver.game.pregame.Lobby;
import com.ourgame.ourgameserver.utils.observer.Observer;
import com.ourgame.ourgameserver.ws.controllers.dto.LobbyDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LobbyService implements Observer {
    private final List<Lobby> lobbys;
    private final PlayerService playerService;

    public LobbyService(PlayerService playerService) {
        lobbys = new ArrayList<>();
        this.playerService = playerService;
    }

    public Lobby getLobby(int lobbyId) {
        return lobbys.get(lobbyId);
    }

    public List<Lobby> getLobbys() {
        return lobbys;
    }

    public int getLobbyId(Lobby lobby) {
        return lobbys.indexOf(lobby);
    }

    public void createLobby(LobbyDto lobbyDto, String hostUsername) {
        Lobby lobby = new Lobby(
                lobbys.size(),
                lobbyDto.getName(),
                playerService.createPlayer(hostUsername),
                lobbyDto.getAPackage(),
                lobbyDto.getPassword(),
                lobbyDto.getMaxPlayers());
//        if (lobbys.contains(lobby)) { TODO
//            throw new LobbyException("Lobby already exists");
//        }
        if (!lobbys.contains(lobby)) {
            lobbys.add(lobby);
        }
    }

    public void addPlayerToLobby(int lobbyId, String username) {
        Lobby lobby = lobbys.get(lobbyId);
        Player player = playerService.createPlayer(username);
        if (lobby == null) {
            throw new LobbyNotFoundException("Lobby not found");
        }
        if (lobby.getPlayers().contains(player)) {
            throw new LobbyException("Player already in lobby");
        }
        lobby.addPlayer(player);
    }

    public void deleteLobby(int lobbyId) {
        lobbys.remove(lobbyId);
    }


    @Override
    public void update() {
        //TODO
    }
}
