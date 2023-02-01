package com.ourgame.ourgameserver.ws.dto;

import com.ourgame.ourgameserver.game.pack.Round;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Getter
public class RoundDto {
    private final List<ThemeDto> themes;
    private final String name;

    public RoundDto(Round round) {
        this.name = round.getName();
        this.themes = new ArrayList<>();
        for (int i = 0; i < round.getThemes().size(); i++) {
            themes.add(new ThemeDto(round.getThemes().get(i)));
        }
    }

}
