package com.example.conwaysgameoflifejava.cell;


public enum CellRule{
    SPAWN(3),
    KEEP(2);

    private int value;

    CellRule(int value){
        this.value = value;
    }

    public int getValue() { return this.value; }
    public void setValue(int aliveNeighbours) { this.value = aliveNeighbours; }

}




