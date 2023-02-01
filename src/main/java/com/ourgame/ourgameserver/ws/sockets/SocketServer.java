package com.ourgame.ourgameserver.ws.sockets;


import com.ourgame.ourgameserver.game.pregame.Lobby;
import io.socket.socketio.server.SocketIoServer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class SocketServer {
    private final SocketIoServer server;
    private final static String PATH = "/lobby";
    private final static int PORT = 9093;

    private final ServerWrapper serverWrapper = new ServerWrapper("0.0.0.0", PORT, null); // null means "allow all" as stated in https://github.com/socketio/engine.io-server-java/blob/f8cd8fc96f5ee1a027d9b8d9748523e2f9a14d2a/engine.io-server/src/main/java/io/socket/engineio/server/EngineIoServerOptions.java#L26

    private final Map<Lobby, GameHandlerSocket> lobbySocketsMap;

    public SocketServer() {
        try {
            serverWrapper.startServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
        server = serverWrapper.getSocketIoServer();
        lobbySocketsMap = new HashMap<>();
    }

    public String createLobbyNamespace(Lobby lobby) {
        String namespacePath = PATH + "/" + lobby.getId();
        GameHandlerSocket lobbySocket = new GameHandlerSocket(this, lobby, serverWrapper.getSocketIoServer().namespace(namespacePath));
        lobbySocketsMap.put(lobby, lobbySocket);
        return namespacePath;
    }

    public String getLobbyNamespace(Lobby lobby) {
        if (lobbySocketsMap.containsKey(lobby)) {
            return ":" + PORT + PATH + "/" + lobby.getId();
        }
        return null;
    }

//    public void deleteLobbyNamespace(Lobby lobby) {
//        server.namespace() removeNamespace("/lobby/" + lobby.getId());
//        lobbySocketsMap.remove(lobby);
//    }


}
