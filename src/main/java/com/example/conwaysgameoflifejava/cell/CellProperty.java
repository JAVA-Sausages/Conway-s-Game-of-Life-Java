package com.example.conwaysgameoflifejava.cell;

public enum CellProperty {
    SIZE(10);

    private int value;

    CellProperty(int value) {
        this.value = Math.max(value, 10);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = Math.max(value, 10);
    }
}
