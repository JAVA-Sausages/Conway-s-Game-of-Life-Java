package com.example.conwaysgameoflifejava.gameLogic;

import com.example.conwaysgameoflifejava.cell.Cell;
import com.example.conwaysgameoflifejava.cell.CellProperty;
import com.example.conwaysgameoflifejava.customComponents.ResizableCanvas;

public class GameState {
    private double playgroundWidth;
    private double playgroundHeight;
    private Cell[][] cells;

    public enum CellRule{
        SPAWN(3),
        KEEP(2,3),
        OVERPOPULATION(4,-1),
        HUNGER(0,1);

         CellRule(int neighbours){
            this.lower_edge = neighbours;
            this.higher_edge = neighbours;
        }
        CellRule(int lower,int higher) {
            this.lower_edge = lower;
            this.higher_edge = higher;
        }

        private final int lower_edge;
        private final int higher_edge;
    }



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

public void newGeneration(){
        int cellsXcount = (int) (playgroundWidth / CellProperty.SIZE.getValue());
        int cellsYcount = (int) (playgroundHeight / CellProperty.SIZE.getValue());
        Cell[][]tempLife = cells;


        int living_neighbours;
        //Iterate through the whole grid
        for(int x = 0; x < cellsXcount; x++){
            for(int y = 0; y < cellsYcount; y++) {
                living_neighbours = 0;

                // Iterating through the neighbours
                for(int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        // Excluding self-cell
                        if ((x + i < cellsXcount && y + j < cellsYcount) && !(i == 0 && j == 0)) {
                            {
                                if (cells[x + i][y + j].isAlive()) {
                                    living_neighbours++;
                                }
                            }
                        }
                    }
                }
                tempLife[x][y].setAlive((cells[x][y].isAlive() ?
                         (living_neighbours == CellRule.KEEP.lower_edge || living_neighbours == CellRule.KEEP.higher_edge)
                        : living_neighbours == CellRule.SPAWN.higher_edge));
            }
        }
        cells = tempLife;
    }
}
