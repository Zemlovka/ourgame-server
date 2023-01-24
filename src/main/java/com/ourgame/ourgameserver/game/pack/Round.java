package com.ourgame.ourgameserver.game.pack;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

@Getter
public class Round {
    @XmlElementWrapper(name = "themes")
    @XmlElement(name = "theme")
    private List<Theme> Themes;

    @XmlAttribute(name = "name")
    private String name;
}
