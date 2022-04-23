package com.example.conwaysgameoflifejava.Theme;

import javafx.scene.paint.Color;

public enum LightThemeColor {
    BACKGROUND(Color.color(212 / 256.0, 212 / 256.0, 212 / 256.0)),
    LABEL_TEXT(Color.BLACK),
    BUTTON_FILL(Color.color(48 / 256.0, 48 / 256.0, 48 / 256.0));

    private final Color color;

    LightThemeColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}