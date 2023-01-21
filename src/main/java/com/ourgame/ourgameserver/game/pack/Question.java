package com.ourgame.ourgameserver.game.pack;

public class Question {
    public Scenario getScenario() {
        return this.Scenario;
    }

    public void setScenario(Scenario Scenario) {
        this.Scenario = Scenario;
    }

    Scenario Scenario;

    public Right getRight() {
        return this.Right;
    }

    public void setRight(Right Right) {
        this.Right = Right;
    }

    Right Right;

    public int getPrice() {
        return this.Price;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }

    int Price;

    public String getText() {
        return this.Text;
    }

    public void setText(String Text) {
        this.Text = Text;
    }

    String Text;

    public Type getType() {
        return this.Type;
    }

    public void setType(Type Type) {
        this.Type = Type;
    }

    Type Type;

    public Wrong getWrong() {
        return this.Wrong;
    }

    public void setWrong(Wrong Wrong) {
        this.Wrong = Wrong;
    }

    Wrong Wrong;
}
