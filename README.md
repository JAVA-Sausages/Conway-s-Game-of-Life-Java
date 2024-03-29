# Conway-s-Game-of-Life-Java

## What is Conway's Game of Life?

[Wikipedia's definition of Conway's Game of Life](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life)

## Project contributors

- [Denis Shevchuk](https://github.com/WashingtonD) - JavaFX, algorithms
- [Maciej Luciński](https://github.com/Vyvr) - UI design/features
- [Szymon Kaszuba-Gałka](https://github.com/Szymonexis) - JavaFX, project design, algorithms

## Project targets

### Main target

Main target is to create an app that implements Conway's Game of Life in a 2-dimension playground in JavaFX. That is:

- user can create a starting state for the game of life by drawing on the playground
- its clock will be fixed
- cellColor color: white, backgroundColor color: black
- rules:
    - any live cellColor with fewer than two live neighbours dies, as if by underpopulation.
    - any live cellColor with two or three live neighbours lives on to the next generation.
    - any live cellColor with more than three live neighbours dies, as if by overpopulation.
    - any dead cellColor with exactly three live neighbours becomes a live cellColor, as if by reproduction.
- only one playground can be active at once
- playground is fixed in size (borders act as no cells squares)

### Secondary targets

Secondary targets:

- let the user expand the game field to whatever proportions desired (or within some high enough constraints)
- let the user change both cellColor color and backgroundColor color
- let user change cell size
- let user change the underlying rules of the game:
    - cell-spawn rule
    - cell-keep rule
- add template loading and saving
- let user change the speed of the game (might not be implemented)
- let the user change the zoom and move the camera around the playground (might not be implemented)
