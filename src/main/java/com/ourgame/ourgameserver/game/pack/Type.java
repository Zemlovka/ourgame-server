package com.ourgame.ourgameserver.game.pack;

import java.util.List;

public class Type {
    public String getName() {
        return this.Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    String Name;

    public List<Param> getParam() {
        return this.Param;
    }

    public void setParam(List<Param> Param) {
        this.Param = Param;
    }

    List<Param> Param;

    public String getText() {
        return this.Text;
    }

    public void setText(String Text) {
        this.Text = Text;
    }

    String Text;
}
