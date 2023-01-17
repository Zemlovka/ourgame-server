package com.ourgame.ourgameserver.game;

import com.ourgame.ourgameserver.game.pregame.Lobby;
import com.ourgame.ourgameserver.utils.observer.Observer;
import com.ourgame.ourgameserver.ws.controllers.dto.LobbyDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LobbyRepository implements Observer {
    private final List<Lobby> lobbys;

    public LobbyRepository() {
        lobbys = new ArrayList<>();
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

    public void createLobby(LobbyDto lobbyDto) {
        Lobby lobby = new Lobby(
                lobbys.size(),
                lobbyDto.getName(),
                lobbyDto.getHost(),
                lobbyDto.getPack(),
                lobbyDto.getPassword());
        lobbys.add(lobby);
    }

    public void deleteLobby(int lobbyId) {
        lobbys.remove(lobbyId);
    }


    @Override
    public void update() {

    }
}
