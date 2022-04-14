# Conway-s-Game-of-Life-Java

## What is Conway's Game of Life?

[Wikipedia's definition of Conway's Game of Life](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life)

## Project contributors

- [Denis Shevchuk](https://github.com/WashingtonD) - JavaFX design
- [Maciej Luciński](https://github.com/Vyvr) - UI design
- [Szymon Kaszuba-Gałka](https://github.com/Szymonexis) - algorythms and github management

## Project targets

### Main target

Main tagret is to create an app that implements Conway's Game of Life in a 2-dimansion playground in JavaFX. That is:

- user can create a starting state for the game of life by drawing on the playground
- its clock will be fixed
- cell color: white, background color: black
- rules:
    - any live cell with fewer than two live neighbours dies, as if by underpopulation.
    - any live cell with two or three live neighbours lives on to the next generation.
    - any live cell with more than three live neighbours dies, as if by overpopulation.
    - any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
- only one playgroud can be active at once
- playgroud is fixed in size (borders act as no cells squares)

### Secondary targets

Secondary targets:

- let the user to expand the gamefield to whatever proportions desired (or within some high enough constraints)
- let the user change both cell color and background color
- let user change cell size
- let user change the underlying rules of the game:
    - cell-alive state
    - cell-dead state
- let user spawn as many concurrent game playgrounds as desired (multithreading)
- let user change the speed of the game (might not be implemented)
- let the user change the zoom and move the camera around the playground (might not be implemented)
