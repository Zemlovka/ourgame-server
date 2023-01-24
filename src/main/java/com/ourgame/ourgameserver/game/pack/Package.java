package com.ourgame.ourgameserver.game.pack;

import lombok.Getter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;


@XmlRootElement(name = "package")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
public class Package {
    @XmlElement(name = "info", required = true)
    private Info info;

    @XmlElementWrapper(name = "rounds")
    @XmlElement(name = "round")
    private List<Round> rounds;

    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "version")
    private int version;

    @XmlAttribute(name = "id")
    private String id;

    @XmlAttribute(name = "date")
    private String date;

    @XmlAttribute(name = "difficulty")
    private int difficulty;

    private List<String> tags;

    public Package(Info info, String name, int version, String id) {
        this.info = info;
        this.name = name;
        this.version = version;
        this.id = id;
    }

    public Package() {
    }

    @Override
    public String toString() {
        return "Package{" +
                "\ninfo: " + info +
                "\nrounds: " + rounds +
                "\nname: '" + name + '\'' +
                "\nversion: " + version +
                "\nid: "  + id + "\n}";
    }
}

