package com.ourgame.ourgameserver.game.pack;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.Collection;
import java.util.List;

@Getter
public class Info {
    @XmlElementWrapper(name = "authors")
    @XmlElement(name = "author")
    private Collection<String> authors;

    public Info(List<String> authors) {
        this.authors = authors;
    }

    public Info() {
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        String as = "";
        for (String a : authors) {
            as += " " + a;
        }
        return as;
    }
}
