package com.ourgame.ourgameserver.game.pack;

import lombok.Getter;

import java.util.List;

@Getter
public class Pack {
    private String author;
    private List<String> tags;
}

public class Pack {
    public Info getInfo() {
        return this.Info; }
    public void setInfo(Info Info) {
        this.Info = Info; }
    Info Info;
    public Rounds getRounds() {
        return this.Rounds; }
    public void setRounds(Rounds Rounds) {
        this.Rounds = Rounds; }
    Rounds Rounds;
    public String getName() {
        return this.Name; }
    public void setName(String Name) {
        this.Name = Name; }
    String Name;
    public int getVersion() {
        return this.Version; }
    public void setVersion(int Version) {
        this.Version = Version; }
    int Version;
    public String getId() {
        return this.Id; }
    public void setId(String Id) {
        this.Id = Id; }
    String Id;
    public String getDate() {
        return this.Date; }
    public void setDate(String Date) {
        this.Date = Date; }
    String Date;
    public int getDifficulty() {
        return this.Difficulty; }
    public void setDifficulty(int Difficulty) {
        this.Difficulty = Difficulty; }
    int Difficulty;
    public String getXmlns() {
        return this.Xmlns; }
    public void setXmlns(String Xmlns) {
        this.Xmlns = Xmlns; }
    String Xmlns;
    public String getText() {
        return this.Text; }
    public void setText(String Text) {
        this.Text = Text; }
    String Text;
}

