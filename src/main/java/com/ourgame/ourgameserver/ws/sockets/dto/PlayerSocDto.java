package com.ourgame.ourgameserver.ws.sockets.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PlayerSocDto {
    private String username;
    private String token;
    private boolean isReady;
}
