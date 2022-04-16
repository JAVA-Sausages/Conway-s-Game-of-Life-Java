package com.example.conwaysgameoflifejava.gameLogic;

import com.example.conwaysgameoflifejava.cell.Cell;
import com.example.conwaysgameoflifejava.cell.CellProperty;
import com.example.conwaysgameoflifejava.cell.CellRule;
import com.example.conwaysgameoflifejava.customComponents.ResizableCanvas;

public class GameState {
    private double playgroundWidth;
    private double playgroundHeight;
    private Cell[][] cells;



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
        Cell[][]tempCells = cells;


        //int living_neighbours;
        //Iterate through the whole grid
    /*
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

                if(cells[x][y].isAlive())
                    if(living_neighbours <= CellRule.HUNGER.getNeighbours() ||
                            living_neighbours >= CellRule.OVERPOPULATION.getNeighbours())
                        tempCells[x][y].setAlive(false);
                else
                    if(living_neighbours == CellRule.SPAWN.getNeighbours())
                        tempCells[x][y].setAlive(true);

            }
        }
        cells = tempCells;*/

        for(int posXinArray = 0; posXinArray < cellsXcount; posXinArray++)
            for(int posYinArray = 0; posYinArray < cellsYcount; posYinArray++){
                if(!cells[posXinArray][posYinArray].isAlive() && cells[posXinArray][posYinArray].getAliveNeighbours() < CellRule.HUNGER.getNeighbours())
                    continue;
                if(cells[posXinArray][posYinArray].isAlive())
                    if(cells[posXinArray][posYinArray].getAliveNeighbours() <= CellRule.HUNGER.getNeighbours() ||
                            cells[posXinArray][posYinArray].getAliveNeighbours() >= CellRule.OVERPOPULATION.getNeighbours()) {
                        tempCells[posXinArray][posYinArray].setAlive(false);
                        updateLiveNeighboursCount(cells[posXinArray][posYinArray],posXinArray,posYinArray,false);
                    }
                    else
                        if(cells[posXinArray][posYinArray].getAliveNeighbours() == CellRule.SPAWN.getNeighbours()) {
                            tempCells[posXinArray][posYinArray].setAlive(true);
                            updateLiveNeighboursCount(cells[posXinArray][posYinArray],posXinArray,posYinArray,true);
                        }
            }
       cells = tempCells;
    }


    public void updateLiveNeighboursCount(Cell cell, int positionInArrayX, int positionIntArrayY, boolean increment){
        int cellsXcount = (int) (playgroundWidth / CellProperty.SIZE.getValue());
        int cellsYcount = (int) (playgroundHeight / CellProperty.SIZE.getValue());


        if(positionInArrayX == -4 || positionIntArrayY == -4){
         for(int i = 0; i < cellsXcount; i++)
             for(int j = 0; j< cellsYcount; j++)
                if(cells[i][j] == cell){
                    positionInArrayX = i;
                    positionIntArrayY = j;
                }
        }

        for(int offsetPosX = -1; offsetPosX < 2; offsetPosX++)
            for(int offsetPosY = -1; offsetPosY < 2; offsetPosY++) {
                if ((positionInArrayX + offsetPosX < cellsXcount && positionInArrayX + offsetPosX > 0) &&
                        (positionIntArrayY + offsetPosY < cellsYcount && positionIntArrayY + offsetPosY > 0)) {
                        if(increment)
                            cells[positionInArrayX + offsetPosX][positionIntArrayY + offsetPosY].incrementAliveNeighbours();
                        else
                            cells[positionInArrayX + offsetPosX][positionIntArrayY + offsetPosY].decrementAliveNeighbours();
                }
            }
    }
}
