package com.ourgame.ourgameserver.game.pack;

public class Theme {
    public Theme.Questions getQuestions() {
        return this.Questions;
    }

    public <Questions> void setQuestions(Questions questions) {
        this.Questions = (Theme.Questions) questions;
    }

    Questions Questions;

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

    public class Questions {
    }
}
