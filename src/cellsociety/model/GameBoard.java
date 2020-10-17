package cellsociety.model;

import cellsociety.model.cells.Cell;
import cellsociety.model.cells.ConwayCell;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class GameBoard{

  private final int width;
  private final int height;
  private Cell[][] gameBoardCells;
  private String[][] gameBoardStates;

  public GameBoard(int width, int height) {
    this.width = width;
    this.height = height;
    this.gameBoardCells = initializeGameBoardCells(width, height);
    this.gameBoardStates = new String[height][width];
    setGameBoardStates(gameBoardCells);
  }

  public GameBoard(String[][] initialStateConfig) {
    this.width = initialStateConfig[0].length;
    this.height = initialStateConfig.length;
    this.gameBoardCells = createCellConfiguration(initialStateConfig);
    this.gameBoardStates = initialStateConfig;
  }

  private void setGameBoardStates(Cell[][] initialState){
    for (int i = 0; i < initialState.length; i++){
      for (int j = 0; j < initialState[0].length; j++){
        gameBoardStates[i][j] = gameBoardCells[i][j].getState();
      }
    }
  }

  public Cell[][] initializeGameBoardCells(int width, int height){
    Cell[][] cellConfig = new Cell[height][width];
    for (int i = 0; i < height; i++){
      for (int j = 0; j < width; j++){
        cellConfig[i][j] = new ConwayCell(); //TODO: FIX THIS
      }
    }
    return cellConfig;
  }

  public void clear(){
    for (int i = 0; i < height; i++){
      for (int j = 0; j < width; j++){
        gameBoardCells[i][j] = new ConwayCell();
        gameBoardStates[i][j] = ConwayCell.DEAD;
      }
    }
  }

  public Cell getCell(int row, int col) {
    return gameBoardCells[row][col];
  }

  public String getState(int row, int col) {
    return gameBoardStates[row][col];
  }

  public boolean inBounds(int row, int col) {
    return (row >= 0 && col >= 0) && (row < height && col < width);
  }

  public List<List<Integer>> getPositionsOfCellState(String state){
    List<List<Integer>> cellsList = new ArrayList<>();
    for (int i = 0; i < height; i++){
      for (int j = 0; j < width; j++){
        if (gameBoardStates[i][j].equals(state)){
          List<Integer> coordinates = new ArrayList<>();
          coordinates.add(i);
          coordinates.add(j);
          cellsList.add(coordinates);
        }
      }
    }
    return cellsList;
  }

  public void setPiece(int row, int col, String state) {
    gameBoardCells[row][col].setState(state); //TODO: update existing cell
    gameBoardStates[row][col] = state;
  }

  public int getWidth(){
    return width;
  }

  public int getHeight() {
    return height;
  }

  public String[][] getGameBoardStates() {
    return gameBoardStates;
  }


  public Cell[][] createCellConfiguration(String[][] stateConfig){ //TODO: make this work for all cell types
    Cell[][] cellConfig = new Cell[stateConfig.length][stateConfig[0].length];
    for (int i = 0; i < height; i++){
      for (int j = 0; j < width; j++){
        cellConfig[i][j] = new ConwayCell(stateConfig[i][j]);
      }
    }
    return cellConfig;
  }

  public void apply(TriConsumer<Integer, Integer, String> updateCellState){
    for (int i = 0; i < height; i++){
      for (int j = 0; j < width; j++){
        updateCellState.accept(i,j, gameBoardStates[i][j]);
      }
  }}


  public void swapCells(int row1, int col1, int row2, int col2){
    String firstCellState = getCell(row1, col1).getState();
    String secondCellState = getCell(row2, col2).getState();
    setPiece(row1, col1, secondCellState);
    System.out.println(firstCellState + " " +secondCellState);
    setPiece(row2, col2, firstCellState);
  }

}

