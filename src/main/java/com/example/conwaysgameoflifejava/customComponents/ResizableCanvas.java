package com.example.conwaysgameoflifejava.customComponents;

import com.example.conwaysgameoflifejava.cell.Cell;
import com.example.conwaysgameoflifejava.cell.CellColor;
import com.example.conwaysgameoflifejava.cell.CellProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class ResizableCanvas extends Canvas {
    public ResizableCanvas() {
        // Redraw canvas when size changes.
        widthProperty().addListener(evt -> draw());
        heightProperty().addListener(evt -> draw());
    }

    private void draw() {
        GraphicsContext gc = getGraphicsContext2D();
    }

    public void drawPlayground(ArrayList<ArrayList<Cell>> cells) {
        GraphicsContext gc = getGraphicsContext2D();
        for (ArrayList<Cell> cellRow: cells) {
            for ( Cell cell : cellRow) {
                gc.setFill(cell.isAlive() ?
                        CellColor.ALIVE.getColor() : CellColor.DEAD.getColor());
                gc.fillRect(cell.getPosX(), cell.getPosY(),
                        CellProperty.SIZE.getValue(), CellProperty.SIZE.getValue());
            }
        }
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