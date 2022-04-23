package com.example.conwaysgameoflifejava.Theme;

import javafx.scene.paint.Color;

public enum DarkThemeColor {
    BACKGROUND(Color.color(48 / 256.0, 48 / 256.0, 48 / 256.0)),
    LABEL_TEXT(Color.WHITE),
    BUTTON_FILL(Color.color(65 / 256.0, 125 / 256.0, 249 / 256.0));

    private final Color color;

    DarkThemeColor(Color color) {
        this.color = color;
    }

    public Color getColor() { return color; }
}
