package com.example.conwaysgameoflifejava;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class ResizableCanvas extends Canvas {
    public ResizableCanvas() {
        // Redraw canvas when size changes.
        widthProperty().addListener(evt -> draw());
        heightProperty().addListener(evt -> draw());
    }

    private void draw() {
        GraphicsContext gc = getGraphicsContext2D();
        gc.setFill(CellColor.DEAD.getColor());
        gc.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    @Override
    public boolean isResizable() {
        return true;
    }

    @Override
    public double prefWidth(double height) {
        return getWidth();
    }

    @Override
    public double prefHeight(double width) {
        return getHeight();
    }
}