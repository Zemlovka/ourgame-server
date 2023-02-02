package com.ourgame.ourgameserver.ws.dto;

import com.ourgame.ourgameserver.game.pack.Atom;
import lombok.Getter;
import org.json.JSONObject;


@Getter
public class AtomDto {
    private final String text;
    private final String type;
    private final long delay;
    public AtomDto(Atom atom, long delay) {
        this.text = atom.getText();
        this.type = atom.getType();
        this.delay = delay;
    }

    public JSONObject toJson() {
        return new JSONObject(this);
    }
}
