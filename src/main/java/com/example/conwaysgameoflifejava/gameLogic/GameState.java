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

public void newGeneration(){
        int cellsXcount = (int) (playgroundWidth / CellProperty.SIZE.getValue());
        int cellsYcount = (int) (playgroundHeight / CellProperty.SIZE.getValue());

        CellColor[][] tempLifeTable = new CellColor[cellsXcount][cellsXcount];

        int living_neighbours;
        //Iterate through the whole grid
        for(int x = 0; x < cellsXcount; x++){
            for(int y = 0; y < cellsYcount; y++) {
                living_neighbours = 0;

                // Iterating through the neighbours
                for(int i = -1; i < 2; i++){
                    for(int j = -1; j < 2; j++){
                        // Excluding self-cell
                        if((x+i < cellsXcount && y+j < cellsYcount) && !(i == 0 && j == 0)) {
                            {
                                if (cells[x + i][y + j].isAlive()) {
                                    living_neighbours++;
                                }
                            }
                        }
                    }
                }
                tempLifeTable[x][y] = (cells[x][y].isAlive() ? ((living_neighbours == 3 || living_neighbours == 2) ?
                        CellColor.ALIVE : CellColor.DEAD): living_neighbours == 3 ?
                         CellColor.ALIVE : CellColor.DEAD);
            }
        }
        for(int x = 0; x < cellsXcount; x++){
            for(int y = 0; y < cellsYcount; y++){
                switch (tempLifeTable[x][y]) {
                    case ALIVE -> cells[x][y].setAlive(true);
                    case DEAD -> cells[x][y].setAlive(false);
                }
            }
        }


    }


}
