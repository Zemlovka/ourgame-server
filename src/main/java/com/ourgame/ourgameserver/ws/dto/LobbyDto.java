package com.ourgame.ourgameserver.ws.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.ourgame.ourgameserver.game.Player;
import com.ourgame.ourgameserver.game.pack.Package;
import com.ourgame.ourgameserver.game.pregame.Lobby;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Set;


/**
 * A DTO representing a user, name and password are the only mandatories
 * TODO make pack mandatory (require its name or something)
 * TODO add other players
 */
@Validated
public class LobbyDto {
    @Getter @Setter private int id;
    @NotBlank
    @Getter @Setter private String name;
    @Getter private Player host;
    @Getter private Set<Player> players;

    @Getter @Setter private Package aPackage;
    @Getter private int playersCount;
    @Min(3)
    @Max(10)
    @NotNull
    @Getter @Setter private int maxPlayers;
    @NotBlank
    private String password;
    @NotNull
    private boolean isPrivate;

    public LobbyDto() {
        this.aPackage = new Package();
    }

    public LobbyDto(Lobby lobby) {
        this.id = lobby.getId();
        this.name = lobby.getName();
        this.host = lobby.getHost();
        this.aPackage = lobby.getPack();
        this.players = lobby.getPlayers();
        this.isPrivate = lobby.isPrivate();
        this.playersCount = lobby.getPlayerCount();
        this.maxPlayers = lobby.getMaxPlayers();
    }

    public static List<LobbyDto> fromLobbys(List<Lobby> lobbys) {
        return lobbys.stream().map(LobbyDto::new).toList();
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonSetter("password")
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonAlias("isPrivate")
    public boolean isPrivate() {
        return isPrivate;
    }

    @JsonAlias("isPrivate")
    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public JSONObject toJson() {
        return new JSONObject(this);
    }
}
