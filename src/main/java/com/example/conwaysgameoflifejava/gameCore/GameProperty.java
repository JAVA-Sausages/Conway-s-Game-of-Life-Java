package com.example.conwaysgameoflifejava.gameCore;

public enum GameProperty {
    TICK(100);

    private int value;

    GameProperty(int value) {
        this.value = Math.max(value, 100);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = Math.max(value, 10);
    }
}
