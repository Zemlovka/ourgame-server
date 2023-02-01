package com.ourgame.ourgameserver.game.pack;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

@Getter
public class Theme {
    @XmlElementWrapper(name = "questions")
    @XmlElement(name = "question")
    private List<Question> questions;

    @XmlAttribute
    private String name;

    public Theme() {
    }

    public Theme(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Theme theme = (Theme) o;

        return getName().equals(theme.getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }
}
