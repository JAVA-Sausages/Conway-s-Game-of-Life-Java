package com.example.conwaysgameoflifejava.cell;

import javafx.scene.paint.Color;

public enum CellColor {
    ALIVE(Color.WHITE),
    DEAD(Color.BLACK);

    private Color color;

    CellColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
