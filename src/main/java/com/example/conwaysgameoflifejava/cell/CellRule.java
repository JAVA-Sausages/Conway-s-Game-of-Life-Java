package com.example.conwaysgameoflifejava.cell;


public enum CellRule{
    SPAWN(3),
    KEEP(3), //2-3
    OVERPOPULATION(4), //4 - inf
    HUNGER(1); // 0-1

    CellRule(int neighbours){
        this.live_neighbours = neighbours;
    }

    public int getNeighbours(){ return this.live_neighbours; }
    public void setNeighbours(int live_neighbours) { this.live_neighbours = live_neighbours; }


    private int live_neighbours;
}




