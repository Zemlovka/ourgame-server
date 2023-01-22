package com.ourgame.ourgameserver.game.pack;

public class Round {
    public Themes getThemes() {
        return this.Themes;
    }

    public void setThemes(Themes themes) {
        this.Themes = themes;
    }

    Themes Themes;

    public String getName() {
        return this.Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    String Name;

    public String getText() {
        return this.Text;
    }

    public void setText(String Text) {
        this.Text = Text;
    }

    String Text;

    public String getType() {
        return this.Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    String Type;
}
