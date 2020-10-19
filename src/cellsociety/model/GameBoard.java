package cellsociety.model;

import Exceptions.InvalidCSVFormatException;
import cellsociety.model.cells.Cell;

import cellsociety.model.cells.WaTorCell;
import cellsociety.model.games.Simulation;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import javax.print.attribute.HashAttributeSet;


public class GameBoard{

  private final int width;
  private final int height;
  private final Cell[][] gameBoardCells;
  private String[][] gameBoardStates;

  public GameBoard(int width, int height, String cellType) {
    this.width = width;
    this.height = height;
    this.gameBoardCells = initializeGameBoardCells(width, height, cellType);
    this.gameBoardStates = new String[height][width];
    setGameBoardStates(gameBoardCells);
  }

  public GameBoard(String[][] initialStateConfig, String cellType, String[] possibleStates) {
    this.width = initialStateConfig[0].length;
    this.height = initialStateConfig.length;
    this.gameBoardCells = createCellConfiguration(initialStateConfig, cellType);
    this.gameBoardStates = initialStateConfig;
    validateStates(possibleStates);
  }

  public void validateStates(String[] possibleStates) {
    Set<String> statesSet = new HashSet<>(Arrays.asList(possibleStates));
    for (int i = 0; i < gameBoardStates.length; i++) {
      for (int j = 0; j < gameBoardStates[i].length; j++) {
        if(!statesSet.contains(gameBoardStates[i][j])){
          throw new InvalidCSVFormatException("this will be a message from a resources file");
        }
      }
    }
  }

  public Cell[][] initializeGameBoardCells(int width, int height, String cellType) {
    Cell[][] cellConfig = new Cell[height][width];
    for (int i = 0; i < height; i++){
      for (int j = 0; j < width; j++){
        Class operation;
        try{
          operation = Class.forName("cellsociety.model.cells." + cellType);
          cellConfig[i][j] = (Cell) operation.getConstructor(String.class)
              .newInstance("0");
        }
        catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
          e.printStackTrace();
        }
        cellConfig[i][j] = new WaTorCell(WaTorCell.OCEAN); //TODO: FIX THIS
      }
    }
    return cellConfig;
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

  public List<List<Integer>> getNeighboringPositionsOfCellState(String state, int row, int col){
    List<List<Integer>> cellsList = new ArrayList<>();
    for (int i = row - 1; i <= row + 1; i++) {
      for (int j = col - 1; j <= col + 1; j++) {
        if (inBounds(i, j) && gameBoardStates[i][j].equals(state)) {
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

  private void setGameBoardStates(Cell[][] initialState) {
    for (int i = 0; i < initialState.length; i++) {
      for (int j = 0; j < initialState[0].length; j++) {
        gameBoardStates[i][j] = gameBoardCells[i][j].getState();
      }
    }
  }

  public Cell[][] createCellConfiguration(String[][] stateConfig, String cellType){ //TODO: make this work for all cell types
    Cell[][] cellConfig = new Cell[stateConfig.length][stateConfig[0].length];
    for (int i = 0; i < height; i++){
      for (int j = 0; j < width; j++){
        Class operation;
        try {
          operation = Class.forName("cellsociety.model.cells." + cellType);
          cellConfig[i][j]  = (Cell) operation.getConstructor(String.class)
              .newInstance(stateConfig[i][j]);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
          e.printStackTrace();
        }
      }
    }
    return cellConfig;
  }

  public void apply(TriConsumer<Integer, Integer, String> updateCellState) {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        updateCellState.accept(i, j, gameBoardStates[i][j]);
      }
    }
  }

  public void copyCell(int row, int col, Cell cell){
    gameBoardCells[row][col] = cell;
    gameBoardStates[row][col] = cell.getState();
  }


  public void swapCells(int row1, int col1, int row2, int col2) {
    Cell firstCell = getCell(row1, col1);
    Cell secondCell = getCell(row2, col2);
    gameBoardCells[row1][col1] = secondCell;
    gameBoardCells[row2][col2] = firstCell;
    gameBoardStates[row1][col1] = secondCell.getState();
    gameBoardStates[row2][col2] = firstCell.getState();
  }

}

