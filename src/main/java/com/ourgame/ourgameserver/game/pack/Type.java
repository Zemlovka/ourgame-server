package com.ourgame.ourgameserver.game.pack;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@Getter
public class Type {
    @XmlAttribute
    private String name;

    @XmlElement(name = "param")
    private List<Param> params;
}
