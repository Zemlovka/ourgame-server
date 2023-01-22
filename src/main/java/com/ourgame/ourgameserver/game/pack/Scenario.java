package com.ourgame.ourgameserver.game.pack;

import java.util.List;

public class Scenario {
    public List<Atom> getAtom() {
        return this.Atom;
    }

    public void setAtom(List<Atom> atom) {
        this.Atom = atom;
    }

    List<Atom> Atom;
}
