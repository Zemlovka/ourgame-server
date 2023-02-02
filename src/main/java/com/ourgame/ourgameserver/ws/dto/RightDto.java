package com.ourgame.ourgameserver.ws.dto;

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
}
