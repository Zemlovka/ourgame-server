package com.ourgame.ourgameserver.game.pack;

public class Question {
    public Scenario getScenario() {
        return this.Scenario;
    }

    public void setScenario(Scenario scenario) {
        this.Scenario = scenario;
    }

    Scenario Scenario;

    public Right getRight() {
        return this.Right;
    }

    public void setRight(Right right) {
        this.Right = right;
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

    public void setType(Type type) {
        this.Type = type;
    }

    Type Type;

    public Wrong getWrong() {
        return this.Wrong;
    }

    public void setWrong(Wrong wrong) {
        this.Wrong = wrong;
    }

    Wrong Wrong;
}
