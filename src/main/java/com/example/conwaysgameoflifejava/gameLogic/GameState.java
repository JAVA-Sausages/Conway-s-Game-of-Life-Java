package com.example.conwaysgameoflifejava.gameLogic;

import com.example.conwaysgameoflifejava.cell.Cell;
import com.example.conwaysgameoflifejava.cell.CellProperty;
import com.example.conwaysgameoflifejava.customComponents.ResizableCanvas;

public class GameState {
    private double playgroundWidth;
    private double playgroundHeight;
    private final Cell[][] cells;

    public GameState(double width, double height) {
        this.playgroundWidth = width;
        this.playgroundHeight = height;

        int cellsXCount = (int) (playgroundWidth / CellProperty.SIZE.getValue());
        int cellsYCount = (int) (playgroundHeight / CellProperty.SIZE.getValue());

        cells = new Cell[cellsXCount][cellsYCount];

        for (int i = 0; i < cellsXCount; i++) {
            for (int j = 0; j < cellsYCount; j++) {
                cells[i][j] = new Cell(i * CellProperty.SIZE.getValue(),
                        j * CellProperty.SIZE.getValue());
            }
        }
    }

    public void setAliveCell(double posX, double posY) {
        int x = (int) posX / CellProperty.SIZE.getValue();
        int y = (int) posY / CellProperty.SIZE.getValue();

        cells[x][y].setAlive(true);
        cells[x][y].setPosX(posX);
        cells[x][y].setPosY(posY);
    }

    public Cell[][] getCells() {
        return cells;
    }

    public double getPlaygroundWidth() {
        return playgroundWidth;
    }

    public void setPlaygroundWidth(double width) {
        playgroundWidth = width;
    }

    public double getPlaygroundHeight() {
        return playgroundHeight;
    }

    public void setPlaygroundHeight(double height) {
        playgroundHeight = height;
    }
}
