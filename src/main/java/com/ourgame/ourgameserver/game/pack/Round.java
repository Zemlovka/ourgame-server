package com.ourgame.ourgameserver.game.pack;

import java.util.List;

public class Round {


    public class Themes {


    public List<Theme> getTheme() {
        return this.Theme;
    }

    public void setTheme(List<Theme> themes) {
        this.Theme = themes;
    }

    List<Theme> Theme;

    public class Questions {
        private List<com.ourgame.ourgameserver.game.pack.Question> Question;

        public List<Question> getQuestion() {
            return this.Question;
        }

        public void setQuestion(List<Question> question) {
            this.Question = question;

            List<Question> Question;
        }
    }

}

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
