package com.example.conwaysgameoflifejava.cell;

public class Cell {
    private double posX;
    private double posY;
    private int arrayPosX;
    private int arrayPosY;
    private boolean alive;
    private int aliveNeighbours;

    public Cell(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
        arrayPosX = (int) (posX / CellProperty.SIZE.getValue());
        arrayPosY = (int) (posY / CellProperty.SIZE.getValue());
        alive = false;
        aliveNeighbours = 0;
    }

    public int getAliveNeighbours() { return aliveNeighbours; }

    public void setAliveNeighbours(int aliveNeighbours) { this.aliveNeighbours = aliveNeighbours; }

    public void incrementAliveNeighbours() { this.aliveNeighbours++; }

    public void decrementAliveNeighbours() { this.aliveNeighbours--; }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getArrayPosX() {
        return arrayPosX;
    }

    public void setArrayPosX(int arrayPosX) {
        this.arrayPosX = arrayPosX;
    }

    public int getArrayPosY() {
        return arrayPosY;
    }

    public void setArrayPosY(int arrayPosY) {
        this.arrayPosY = arrayPosY;
    }
}
