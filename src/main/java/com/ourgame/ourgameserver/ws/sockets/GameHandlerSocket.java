package com.ourgame.ourgameserver.ws.sockets;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIONamespace;
import com.ourgame.ourgameserver.game.Player;
import com.ourgame.ourgameserver.game.ingame.Game;
import com.ourgame.ourgameserver.game.pregame.Lobby;
import com.ourgame.ourgameserver.ws.sockets.dto.PlayerSocDto;


public class GameHandlerSocket {
    private final SocketServer socketServer;
    private final Lobby lobby;
    private final SocketIONamespace namespace;

    public GameHandlerSocket(SocketServer socketServer, Lobby lobby, SocketIONamespace namespace) {
        this.socketServer = socketServer;
        this.lobby = lobby;
        this.namespace = namespace;
        socketSetup();
    }

    public void socketSetup() {
        namespace.getBroadcastOperations().sendEvent("lobby", lobby);
        namespace.addConnectListener((SocketIOClient client) -> {
            namespace.getBroadcastOperations().sendEvent("lobby", lobby);
        });

        namespace.addEventListener("ready", PlayerSocDto.class, (SocketIOClient client, PlayerSocDto data, AckRequest ackRequest) -> {
            lobby.setPlayerReadyStatus(new Player(data), true);
            namespace.getBroadcastOperations().sendEvent("", lobby);
        });

        namespace.addEventListener("start", PlayerSocDto.class, (SocketIOClient client, PlayerSocDto data, AckRequest ackRequest) -> {
            if (lobby.getHost().equals(new Player(data))
                    && lobby.arePlayersReady()) {
                new Game(lobby);
            }
            // add game start logic
            namespace.getBroadcastOperations().sendEvent("lobby", lobby);
        });
    }

    public void endGame() {
        socketServer.deleteLobbyNamespace(lobby);
    }
}
