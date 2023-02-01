package com.ourgame.ourgameserver.ws.dto;

import com.ourgame.ourgameserver.game.pack.Theme;
import lombok.Getter;


@Getter
public class ThemeDto {
    private final String theme;
    private final int[] questions;

    public ThemeDto(Theme theme) {
        this.theme = theme.getName();
        this.questions = new int[theme.getQuestions().size()];
        for (int i = 0; i < theme.getQuestions().size(); i++) {
            questions[i] = theme.getQuestions().get(i).getPrice();
        }
    }
}
