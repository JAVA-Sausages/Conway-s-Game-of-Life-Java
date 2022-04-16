package com.example.conwaysgameoflifejava.cell;

import javafx.scene.paint.Color;

public class Cell {
    private double posX;
    private double posY;
    private boolean alive;
    private int aliveNeighbours;

    public Cell(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
        alive = false;
        aliveNeighbours = 0;
    }

    public int getAliveNeighbours(){ return aliveNeighbours; }

    public void setAliveNeighbours(int count) { this.aliveNeighbours = count; }

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
}
