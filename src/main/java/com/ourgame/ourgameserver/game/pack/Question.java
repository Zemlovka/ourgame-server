package com.ourgame.ourgameserver.game.pack;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

@Getter
public class Question {
    @XmlElementWrapper(name = "scenario")
    @XmlElement(name = "atom")
    private List<Atom> atoms;

    @XmlElementWrapper(name = "right")
    @XmlElement(name = "answer")
    private List<String> answers;

    @XmlAttribute
    private int price;

    private Type type;
}
