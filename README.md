# Conway-s-Game-of-Life-Java

## Project contributors

- [Denis Shevchuk](https://github.com/WashingtonD) - roles
- [Maciej Luciński](https://github.com/Vyvr) - roles
- [Szymon Kaszuba-Gałka](https://github.com/Szymonexis) - roles

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
- let the user to exapnd the gamefield to whatever proportions desired (or within some high enough constraints)
- let the user change both cell color and background color
- let user change the underlying rules of the game:
  - cell-alive state
  - cell-dead state
- let user spawn as many concurrent game playgrounds as desired (multithreading)
