package com.example.conwaysgameoflifejava.Theme;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ThemeSetter {
    private static boolean isLightTheme = false;

    public static void switchTheme() {
        isLightTheme = !isLightTheme;
    }

    public static void setThemeOnNode(Node node) {
        String style = "-fx-background-color: ";
        style += isLightTheme ?
                getColorHexValue(LightThemeColor.BUTTON_FILL.getColor()):
                getColorHexValue(DarkThemeColor.BUTTON_FILL.getColor());
        node.setStyle(style);
    }

    public static void setThemeOnLabel(Label label) {
        String style = "-fx-text-fill: ";
        style += isLightTheme ?
                getColorHexValue(LightThemeColor.LABEL_TEXT.getColor()):
                getColorHexValue(DarkThemeColor.LABEL_TEXT.getColor());
        label.setStyle(style);
    }

    public static void setThemeOnVBox(VBox vBox) {
        String style = "-fx-background-color: ";
        style += isLightTheme ?
                getColorHexValue(LightThemeColor.BACKGROUND.getColor()):
                getColorHexValue(DarkThemeColor.BACKGROUND.getColor());
        vBox.setStyle(style);
    }

    private static String getColorHexValue(Color color) {
        return "#" + color.toString().substring(2, 8);
    }
}
