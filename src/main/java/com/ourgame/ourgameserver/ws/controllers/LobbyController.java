package com.ourgame.ourgameserver.ws.controllers;

import com.ourgame.ourgameserver.game.LobbyService;
import com.ourgame.ourgameserver.game.Player;
import com.ourgame.ourgameserver.game.exceptions.LobbyNotFoundException;
import com.ourgame.ourgameserver.game.pregame.Lobby;
import com.ourgame.ourgameserver.ws.controllers.dto.LobbyDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/api/lobby")
public class LobbyController {
    private final LobbyService lobbyService;

    public LobbyController(LobbyService lobbyService) {
        this.lobbyService = lobbyService;
    }

    @GetMapping("")
    public ResponseEntity<List<LobbyDto>> getLobbys() {
        List<Lobby> lobbys = lobbyService.getLobbys();
        return ResponseEntity.ok(LobbyDto.fromLobbys(lobbys));
    }

    @GetMapping("/{lobbyId}")
    public ResponseEntity<LobbyDto> getLobby(@PathVariable int lobbyId) {
        Lobby lobby = lobbyService.getLobby(lobbyId);
        if (lobby == null) {
            throw new LobbyNotFoundException("Lobby not found");
        }
        return ResponseEntity.ok(new LobbyDto(lobby));
    }

    @PostMapping("/create")
    public ResponseEntity<String> createLobby(Authentication authentication,
                                              @Valid @RequestBody LobbyDto lobbyDto) {
        lobbyService.createLobby(lobbyDto, authentication.getName());
        return new ResponseEntity<>("Lobby created", HttpStatus.CREATED);
    }

    @PostMapping("/createList")
    @Deprecated //TODO remove
    public ResponseEntity<String> createMoreLobby(Authentication authentication,
                                              @Valid @RequestBody List<LobbyDto> lobbyDto) {
        for (LobbyDto lobby : lobbyDto) {
            lobbyService.createLobby(lobby, authentication.getName());
        }
        return new ResponseEntity<>("Lobbies were created", HttpStatus.CREATED);
    }

    @PostMapping("/join/{lobbyId}")
    public ResponseEntity<String> joinLobby(Authentication authentication,
                                            @PathVariable int lobbyId) {
        lobbyService.addPlayerToLobby(lobbyId, authentication.getName());
        return new ResponseEntity<>("Joined lobby", HttpStatus.OK);
    }

    @PostMapping("/delete/{lobbyId}")
    public ResponseEntity<String> deleteLobby(Authentication authentication, @PathVariable int lobbyId) {
        if (lobbyService.getLobby(lobbyId).getHost().getUsername()
                .equals(authentication.getName())) {
            lobbyService.deleteLobby(lobbyId);
            return ResponseEntity.ok("Lobby deleted");
        }
        return ResponseEntity.badRequest().body("You are not the host of this lobby");
    }
}
