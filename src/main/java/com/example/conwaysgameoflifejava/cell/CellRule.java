package com.example.conwaysgameoflifejava.cell;


public enum CellRule{
    SPAWN(3),
    KEEP(2);

    private int aliveNeighbours;

    CellRule(int aliveNeighbours){
        this.aliveNeighbours = aliveNeighbours;
    }

    public int getNeighbours(){ return this.aliveNeighbours; }
    public void setNeighbours(int aliveNeighbours) { this.aliveNeighbours = aliveNeighbours; }

}




