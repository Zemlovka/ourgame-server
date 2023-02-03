package com.ourgame.ourgameserver.ws.dto;

import org.json.JSONObject;


public class RightDto {
    private final String right;
    private final int price;

    public RightDto(String right, int price) {
        this.right = right;
        this.price = price;
    }

    public String getRight() {
        return right;
    }

    public int getPrice() {
        return price;
    }

    public JSONObject toJson() {
        return new JSONObject(this);
    }
}
