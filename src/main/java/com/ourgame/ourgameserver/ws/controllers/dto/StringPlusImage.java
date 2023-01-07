package com.ourgame.ourgameserver.ws.controllers.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class StringPlusImage {
    private String string;
    private String image;

    public StringPlusImage(String string, String image) {
        this.string = string;
        this.image = image;
    }

    public StringPlusImage() {
    }
}
