package com.ourgame.ourgameserver.game.pack;

public class Theme {
    public Questions getQuestions() {
        return this.Questions;
    }

    public void setQuestions(Questions Questions) {
        this.Questions = Questions;
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
}
