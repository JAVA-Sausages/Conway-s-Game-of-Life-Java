package com.example.conwaysgameoflifejava.cell;

import javafx.scene.paint.Color;

public class Cell {
    private double posX;
    private double posY;
    private boolean alive;

    public Cell(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
        alive = false;
    }

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
