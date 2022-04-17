package com.example.conwaysgameoflifejava.gameCore;

import com.example.conwaysgameoflifejava.cell.Cell;
import com.example.conwaysgameoflifejava.cell.CellNeighbour;
import com.example.conwaysgameoflifejava.cell.CellProperty;
import com.example.conwaysgameoflifejava.cell.CellRule;
import com.example.conwaysgameoflifejava.customComponents.ResizableCanvas;

import java.util.ArrayList;

public class GameState {
    private ResizableCanvas playground;
    private ArrayList<ArrayList<Cell>> cells;
    private boolean allCellsDead = true;

    public GameState(ResizableCanvas playground) {
        this.playground = playground;

        int cellsXCount = (int) (playground.getWidth() / CellProperty.SIZE.getValue());
        int cellsYCount = (int) (playground.getHeight() / CellProperty.SIZE.getValue());

        cells = new ArrayList<>();
        for (int i = 0; i < cellsXCount; i++) {
            cells.add(new ArrayList<>());
            ArrayList<Cell> cellsArr = cells.get(i);
            for (int j = 0; j < cellsYCount; j++) {
                cellsArr.add(new Cell(i, j));
            }
        }

        render();
    }

    public void setAliveCell(double posX, double posY) {
        setCell(posX, posY, true);
        allCellsDead = false;
    }

    public void setDeadCell(double posX, double posY) {
        setCell(posX, posY, false);
        checkAllCellsDead();
    }

    private void setCell(double posX, double posY, boolean isAlive) {
        int cellArrayPosX = (int) (posX / CellProperty.SIZE.getValue());
        int cellArrayPosY = (int) (posY / CellProperty.SIZE.getValue());

        try {
            cells.get(cellArrayPosX).get(cellArrayPosY).setAlive(isAlive);
            render();
        } catch (IndexOutOfBoundsException e) {
            // TODO: seems to be a bug related to clock threading pool - might need to investigate.
            //  For now restraints need to be in place such as minimum 100ms GameClock tick
            //  and minimum 10 pixel cell size
            System.err.println(e.getMessage());
            System.err.println("posX: " + posX);
            System.err.println("posY: " + posY);
            System.err.println("cellArrayPosX: " + cellArrayPosX);
            System.err.println("cellArrayPosY: " + cellArrayPosY);
            System.err.println("cells.lengthX: " + cells.toArray().length);
            System.err.println("cells.lengthY: " + cells.get(0).toArray().length);
            System.out.println("-----------------------");
            render();
        }
    }

    private void checkAllCellsDead() {
        allCellsDead = true;
        for (ArrayList<Cell> cellsRow : cells) {
            for (Cell cell : cellsRow) {
                allCellsDead &= !cell.isAlive();
            }
        }
    }

    public void render() {
        playground.draw(cells);
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
        render();
        checkAllCellsDead();
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

    public void clearCells() {
        for (ArrayList<Cell> cellsRow : cells) {
            for (Cell cell : cellsRow) {
                cell.setAlive(false);
            }
        }
        render();
    }

    public ResizableCanvas getPlayground() {
        return playground;
    }

    public void setPlayground(ResizableCanvas playground) {
        this.playground = playground;
    }

    public boolean isAllCellsDead() {
        return allCellsDead;
    }
}
