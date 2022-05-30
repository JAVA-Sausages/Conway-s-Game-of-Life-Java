package com.example.conwaysgameoflifejava.gameCore;

public enum GameProperty {
    TICK(100);

    private final int value;

    GameProperty(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
