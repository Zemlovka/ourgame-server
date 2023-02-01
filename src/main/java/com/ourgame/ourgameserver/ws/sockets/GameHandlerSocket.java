package com.ourgame.ourgameserver.ws.sockets;

import com.ourgame.ourgameserver.game.Player;
import com.ourgame.ourgameserver.game.ingame.Game;
import com.ourgame.ourgameserver.game.pregame.Lobby;
import com.ourgame.ourgameserver.ws.dto.LobbyDto;
import io.socket.socketio.server.SocketIoNamespace;
import io.socket.socketio.server.SocketIoSocket;
import org.json.JSONObject;


public class GameHandlerSocket {
    private final SocketServer socketServer;
    private final Lobby lobby;
    private final SocketIoNamespace namespace;

    public GameHandlerSocket(SocketServer socketServer, Lobby lobby, SocketIoNamespace namespace) {
        this.socketServer = socketServer;
        this.lobby = lobby;
        this.namespace = namespace;
        socketSetup();
    }

    public void socketSetup() {
        namespace.on("connect", args -> {
            SocketIoSocket socket = (SocketIoSocket) args[0];
            namespace.broadcast(null, "lobby", new LobbyDto(lobby).toJson());

            readyListener(socket);
            startListener(socket);
        });
    }

    private void readyListener(SocketIoSocket socket) {
        socket.on("ready", args -> {
            JSONObject response = (JSONObject) args[0];
            lobby.setPlayerReadyStatus(getPlayer(socket), response.getBoolean("ready"));
            namespace.broadcast(null, "lobby", new LobbyDto(lobby).toJson());
        });
    }

    private void startListener(SocketIoSocket socket) {
        socket.on("start", args -> {
            if (lobby.getHost().equals(getPlayer(socket))
                    && lobby.arePlayersReady()) {
                new Game(lobby);
            }
            namespace.broadcast(null, "game", new LobbyDto(lobby).toJson());
        });
    }

    private Player getPlayer(SocketIoSocket socket) {
        return new Player(socket.getInitialHeaders().get("username").get(0));
    }

//    public void endGame() {
//        socketServer.deleteLobbyNamespace(lobby);
//    }
}
