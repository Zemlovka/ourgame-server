package com.ourgame.ourgameserver.game;

import com.ourgame.ourgameserver.game.exceptions.LobbyException;
import com.ourgame.ourgameserver.game.exceptions.LobbyNotFoundException;
import com.ourgame.ourgameserver.game.exceptions.PackageException;
import com.ourgame.ourgameserver.game.pack.PackParser;
import com.ourgame.ourgameserver.utils.observer.Observer;
import com.ourgame.ourgameserver.ws.dto.LobbyDto;
import com.ourgame.ourgameserver.ws.sockets.SocketServer;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;

@Service
public class LobbyService implements Observer {
    private final List<Lobby> lobbys;
    private final PlayerService playerService;

    public LobbyService(PlayerService playerService, /*TODO: remove this*/SocketServer socketServer) throws JAXBException {
        lobbys = new ArrayList<>();
        this.playerService = playerService;
        //TODO: remove this
        lobbys.add(new Lobby(0, "Lobby 1", playerService.getPlayer("user"),
                PackParser.getPackage("testPack"), "234",false,6, this));
        socketServer.createLobbyNamespace(lobbys.get(0));
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

    public Lobby createLobby(LobbyDto lobbyDto, String hostUsername) {
        Lobby lobby;
        try {
            lobby = new Lobby(
                    lobbys.size(),
                    lobbyDto.getName(),
                    playerService.getPlayer(hostUsername),
                    PackParser.getPackage(lobbyDto.getPackageName()),
                    lobbyDto.getPassword(),
                    lobbyDto.isPrivate(),
                    lobbyDto.getMaxPlayers(),
                    this);
            if (lobbys.contains(lobby)) {
                throw new LobbyException("Lobby already exists");
            }
        } catch (JAXBException e) {
            throw new PackageException("Package not found or corrupted");
        }
        lobbys.add(lobby);
        return lobby;
    }

    //TODO move to lobby
    public boolean isLobbyConnectable(int lobbyId, String username) {
        Lobby lobby = lobbys.get(lobbyId);
        Player player = playerService.getPlayer(username);
        if (lobby == null) {
            throw new LobbyNotFoundException("Lobby not found");
        }
        if (lobby.getPlayers().contains(player)) {
            throw new LobbyException("Player already in lobby");
        }
        return true;
    }

    public void deleteLobby(int lobbyId) {
        lobbys.remove(lobbyId);
    }


    @Override
    public void update() {
        //TODO
    }
}
