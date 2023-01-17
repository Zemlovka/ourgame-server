package com.ourgame.ourgameserver.ws.controllers;

import com.ourgame.ourgameserver.game.LobbyRepository;
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
@RequestMapping("/lobby")
public class LobbyController {
    private final LobbyRepository lobbyRepository;

    public LobbyController(LobbyRepository lobbyRepository) {
        this.lobbyRepository = lobbyRepository;
    }

    @GetMapping("")
    public ResponseEntity<List<LobbyDto>> getLobbys() {
        List<Lobby> lobbys = lobbyRepository.getLobbys();
        return ResponseEntity.ok(LobbyDto.fromLobbys(lobbys));
    }

    @GetMapping("/{lobbyId}")
    public ResponseEntity<LobbyDto> getLobby(@PathVariable int lobbyId) {
        Lobby lobby = lobbyRepository.getLobby(lobbyId);
        if (lobby == null) {
            throw new LobbyNotFoundException("Lobby not found");
        }
        return ResponseEntity.ok(new LobbyDto(lobby));
    }

    @PostMapping("/create")
    public ResponseEntity<String> createLobby(Authentication authentication,
                                              @Valid @RequestBody LobbyDto lobbyDto) {
        lobbyDto.setHost(new Player(authentication.getName()));
        lobbyRepository.createLobby(lobbyDto);
        return new ResponseEntity<>("Lobby created", HttpStatus.CREATED);
    }

    @PostMapping("/delete/{lobbyId}")
    public ResponseEntity<String> deleteLobby(Authentication authentication, @PathVariable int lobbyId) {
        if (lobbyRepository.getLobby(lobbyId).getHost().getUsername()
                .equals(authentication.getName())) {
            lobbyRepository.deleteLobby(lobbyId);
            return ResponseEntity.ok("Lobby deleted");
        }
        return ResponseEntity.badRequest().body("You are not the host of this lobby");
    }
}
