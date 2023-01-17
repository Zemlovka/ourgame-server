package com.ourgame.ourgameserver.ws.controllers.dto;

import com.ourgame.ourgameserver.game.Player;
import com.ourgame.ourgameserver.game.pack.Pack;
import com.ourgame.ourgameserver.game.pregame.Lobby;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.util.List;


/**
 * A DTO representing a user, name and password are the only mandatories
 * TODO make pack mandatory (require its name or something)
 * TODO add other players
 */
@Getter
@Setter
@Validated
public class LobbyDto {
    private int id;
    @NotBlank private String name;
    private Player host;
    private Pack pack;
    @NotBlank private  String password;
    @NotNull private boolean isPrivate;

    public LobbyDto() {
        this.pack = new Pack();
    }

    public LobbyDto(Lobby lobby) {
        this.id = lobby.getId();
        this.name = lobby.getName();
        this.host = lobby.getHost();
        this.pack = lobby.getPack();
        this.isPrivate = lobby.isPrivate();
    }

    public static List<LobbyDto> fromLobbys(List<Lobby> lobbys) {
        return lobbys.stream().map(LobbyDto::new).toList();
    }
}
