package com.example.conwaysgameoflifejava.gameLogic;

import com.example.conwaysgameoflifejava.cell.Cell;
import com.example.conwaysgameoflifejava.cell.CellNeighbour;
import com.example.conwaysgameoflifejava.cell.CellProperty;
import com.example.conwaysgameoflifejava.cell.CellRule;

import java.util.ArrayList;

public class GameState {
    private double playgroundWidth;
    private double playgroundHeight;
    private final int cellsXCount;
    private final int cellsYCount;
    private ArrayList<ArrayList<Cell>> cells;

    public GameState(double width, double height) {
        this.playgroundWidth = width;
        this.playgroundHeight = height;

        cellsXCount = (int) (playgroundWidth / CellProperty.SIZE.getValue());
        cellsYCount = (int) (playgroundHeight / CellProperty.SIZE.getValue());

        cells = new ArrayList<>();

        for (int i = 0; i < cellsXCount; i++) {
            cells.add(new ArrayList<>());
            ArrayList<Cell> cellsArr = cells.get(i);
            for (int j = 0; j < cellsYCount; j++) {
                cellsArr.add(new Cell(i * CellProperty.SIZE.getValue(),
                        j * CellProperty.SIZE.getValue()));
            }
        }
    }

    public void setAliveCell(double posX, double posY) {
        int x = (int) posX / CellProperty.SIZE.getValue();
        int y = (int) posY / CellProperty.SIZE.getValue();

        Cell cell = cells.get(x).get(y);

        cell.setAlive(true);
        cell.setPosX(posX);
        cell.setPosY(posY);
    }

    public ArrayList<ArrayList<Cell>> getCells() {
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

    public void nextGeneration() {
        ArrayList<ArrayList<Cell>> tempCells = cells;

        updateAliveNeighboursCount();

        for (ArrayList<Cell> cellsRow : cells) {
            for (Cell lastGenCell : cellsRow) {
                int posX = lastGenCell.getArrayPosX();
                int poxY = lastGenCell.getArrayPosY();
                Cell nextGenCell = tempCells.get(posX).get(poxY);

                if (!lastGenCell.isAlive()) {
                    nextGenCell.setAlive(lastGenCell.getAliveNeighbours() == CellRule.SPAWN.getNeighbours());
                } else {
                    nextGenCell.setAlive(lastGenCell.getAliveNeighbours() == CellRule.KEEP.getNeighbours()
                            || lastGenCell.getAliveNeighbours() == CellRule.SPAWN.getNeighbours());
                }
            }
        }
        cells = tempCells;
    }


    private void updateAliveNeighboursCount() {
        for (ArrayList<Cell> cellsRow : cells) {
            for (Cell cell : cellsRow) {
                CellNeighbour[] cellNeighbourTypes = CellNeighbour.values();
                ArrayList<Cell> neighbourCells = new ArrayList<>(cellNeighbourTypes.length);

                for (CellNeighbour neighbourType : cellNeighbourTypes) {
                    neighbourCells.add(getNeighbour(cell.getArrayPosX(), cell.getArrayPosY(), neighbourType));
                }

                int aliveNeighboursCount = 0;
                for (Cell neighbourCell : neighbourCells) {
                    if (neighbourCell != null && neighbourCell.isAlive()) {
                        aliveNeighboursCount += 1;
                    }
                }
                cell.setAliveNeighbours(aliveNeighboursCount);
            }
        }
    }

    private Cell getNeighbour(int cellPosX, int cellPosY, CellNeighbour cellNeighbour) {
        int posOffset = 1;

        switch (cellNeighbour) {
            case TOP -> {
                try {
                    return cells.get(cellPosX).get(cellPosY - posOffset);
                } catch (IndexOutOfBoundsException e) {
                    return null;
                }
            }
            case BOTTOM -> {
                try {
                    return cells.get(cellPosX).get(cellPosY + posOffset);
                } catch (IndexOutOfBoundsException e) {
                    return null;
                }
            }
            case LEFT -> {
                try {
                    return cells.get(cellPosX - posOffset).get(cellPosY);
                } catch (IndexOutOfBoundsException e) {
                    return null;
                }
            }
            case RIGHT -> {
                try {
                    return cells.get(cellPosX + posOffset).get(cellPosY);
                } catch (IndexOutOfBoundsException e) {
                    return null;
                }
            }
            case TOP_LEFT -> {
                try {
                    return cells.get(cellPosX - posOffset).get(cellPosY - posOffset);
                } catch (IndexOutOfBoundsException e) {
                    return null;
                }
            }
            case TOP_RIGHT -> {
                try {
                    return cells.get(cellPosX + posOffset).get(cellPosY - posOffset);
                } catch (IndexOutOfBoundsException e) {
                    return null;
                }
            }
            case BOTTOM_LEFT -> {
                try {
                    return cells.get(cellPosX - posOffset).get(cellPosY + posOffset);
                } catch (IndexOutOfBoundsException e) {
                    return null;
                }
            }
            case BOTTOM_RIGHT -> {
                try {
                    return cells.get(cellPosX + posOffset).get(cellPosY + posOffset);
                } catch (IndexOutOfBoundsException e) {
                    return null;
                }
            }
        }
        return null;
    }
}
