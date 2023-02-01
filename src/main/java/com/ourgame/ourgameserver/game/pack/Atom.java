package com.ourgame.ourgameserver.game.pack;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

@Getter
public class Atom {
    @XmlAttribute
    private String type;

    @XmlValue
    private String text;
}
