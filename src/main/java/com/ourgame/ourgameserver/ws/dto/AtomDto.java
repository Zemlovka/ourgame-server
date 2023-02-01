package com.ourgame.ourgameserver.ws.dto;

import com.ourgame.ourgameserver.game.pack.Atom;
import lombok.Getter;
import org.json.JSONObject;


@Getter
public class AtomDto {
    private final String text;
    private final String type;
    public AtomDto(Atom atom) {
        this.text = atom.getText();
        this.type = atom.getType();
    }

    public JSONObject toJson() {
        return new JSONObject(this);
    }
}
