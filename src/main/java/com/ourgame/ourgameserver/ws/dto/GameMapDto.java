package com.ourgame.ourgameserver.ws.dto;


import com.ourgame.ourgameserver.game.pack.PackParser;
import com.ourgame.ourgameserver.game.pack.Round;
import lombok.Getter;
import org.json.JSONObject;
import com.ourgame.ourgameserver.game.pack.Package;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;

@Getter
public class GameMapDto {
    private final List<RoundDto> rounds;
    public GameMapDto(Package pack) throws JAXBException {
        rounds = new ArrayList<>();
        for (Round round : pack.getRounds()) {
            rounds.add(new RoundDto(round));
        }
    }

    public static void main(String[] args) throws JAXBException {
        GameMapDto gameMapDto = new GameMapDto(PackParser.getPackage("testPack"));
        System.out.println(gameMapDto.toJson());
    }



    public JSONObject toJson() {
        return new JSONObject(this);
    }
}
