package com.ourgame.ourgameserver.ws.sockets;

import com.ourgame.ourgameserver.game.Player;
import com.ourgame.ourgameserver.game.Lobby;
import com.ourgame.ourgameserver.game.PlayerService;
import com.ourgame.ourgameserver.game.pack.Atom;
import com.ourgame.ourgameserver.ws.dto.GameMapDto;
import com.ourgame.ourgameserver.ws.dto.LobbyDto;
import io.socket.socketio.server.SocketIoNamespace;
import io.socket.socketio.server.SocketIoSocket;
import org.json.JSONObject;

import javax.xml.bind.JAXBException;


public class GameHandlerSocket {
    private final SocketServer socketServer;
    private final Lobby lobby;
    private final SocketIoNamespace namespace;
    private final PlayerService playerService;

    public GameHandlerSocket(SocketServer socketServer, Lobby lobby, SocketIoNamespace namespace, PlayerService playerService) {
        this.socketServer = socketServer;
        this.lobby = lobby;
        this.namespace = namespace;
        this.playerService = playerService;
        socketSetup();
    }

    public void socketSetup() {
        namespace.on("connect", args -> {
            SocketIoSocket socket = (SocketIoSocket) args[0];
            lobby.addPlayer(getPlayer(socket));
            namespace.broadcast(null, "lobby", new LobbyDto(lobby).toJson());

            disconnectListener(socket);
            readyListener(socket);
            startGame(socket);
        });
    }

    private void disconnectListener(SocketIoSocket socket) {
        socket.on("disconnect", args -> {
            lobby.removePlayer(getPlayer(socket));
            namespace.broadcast(null, "lobby", new LobbyDto(lobby).toJson());
        });
    }

    private void readyListener(SocketIoSocket socket) {
        socket.on("ready", args -> {
            JSONObject response = (JSONObject) args[0];
            lobby.setPlayerReadyStatus(getPlayer(socket), response.getBoolean("ready"));
            namespace.broadcast(null, "lobby", new LobbyDto(lobby).toJson());
        });
    }

    private void startGame(SocketIoSocket socket) {
        socket.on("start", args1 -> {
            try {
                GameMapDto map = new GameMapDto(lobby.getPack());
                if (lobby.getHost().equals(getPlayer(socket)) && lobby.arePlayersReady()) {
                    namespace.broadcast(null, "map", map.toJson());
                    lobby.startGame();
                }
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        });
    }

    private void selectQuestion(SocketIoSocket socket) {
        socket.on("selectQuestion", args -> {
            JSONObject request = (JSONObject) args[0];
            String theme = request.getString("theme");
            int questionIndex = request.getInt("question");
            Atom questionAtom = lobby.getQuestion(theme, questionIndex).getAtoms().get(0);
            namespace.broadcast(null, "lobby", new JSONObject(questionAtom));
        });
    }

    private Player getPlayer(SocketIoSocket socket) {
        return playerService.getPlayer(socket.getInitialHeaders().get("username").get(0));
    }

//    public void endGame() {
//        socketServer.deleteLobbyNamespace(lobby);
//    }
}
