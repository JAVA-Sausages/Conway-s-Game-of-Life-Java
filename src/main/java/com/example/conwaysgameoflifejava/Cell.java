package com.example.conwaysgameoflifejava;

import javafx.scene.paint.Color;

public class Cell {
    private double posX;
    private double posY;
    private boolean alive;
    private Color color;

    public Cell(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
        alive = false;
        color = Color.BLACK;
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
        if (alive) {
            color = CellColor.ALIVE.getColor();
        } else {
            color = CellColor.DEAD.getColor();
        }
        this.alive = alive;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
