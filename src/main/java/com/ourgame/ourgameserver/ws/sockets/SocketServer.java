package com.ourgame.ourgameserver.ws.sockets;


import com.corundumstudio.socketio.*;
import com.ourgame.ourgameserver.game.pregame.Lobby;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class SocketServer {
    private final SocketIOServer server;
    private final static String PATH = "/lobby";
    private final static int PORT = 9092;

    private final Map<Lobby, GameHandlerSocket> lobbySocketsMap;

    public SocketServer() {
        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(PORT);
        server = new SocketIOServer(config);
        server.startAsync();
        lobbySocketsMap = new HashMap<>();
    }

    public String createLobbyNamespace(Lobby lobby) {
        String namespacePath = ":" + PORT + PATH + "/" + lobby.getId();
        SocketIONamespace namespace = server.addNamespace(namespacePath);
        GameHandlerSocket lobbySocket = new GameHandlerSocket(this, lobby, namespace);
        lobbySocketsMap.put(lobby, lobbySocket);
        return namespacePath;
    }

    public String getLobbyNamespace(Lobby lobby) {
        if (lobbySocketsMap.containsKey(lobby)); {
             return ":" + PORT + PATH + "/" + lobby.getId();
        }
    }

    public void deleteLobbyNamespace(Lobby lobby) {
        server.removeNamespace("/lobby/" + lobby.getId());
        lobbySocketsMap.remove(lobby);
    }


}
