package com.ourgame.ourgameserver.game.pack;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

@Getter
public class Theme {
    @XmlElementWrapper(name = "questions")
    @XmlElement(name = "question")
    List<Question> questions;

    @XmlAttribute
    String name;
}
