package cellsociety.model;

import java.util.ArrayList;
import java.util.List;

public class Neighborhood {

  private static final String COMPLETE = "complete";
  private static final String CARDINAL = "cardinal";
  private static final String ORDINAL = "ordinal";




  public List<List<Integer>> neighbors;

  public Neighborhood(int row, int col, GameBoard gameBoard, String policy){
    neighbors = new ArrayList<>();
    switch (policy) {
      case COMPLETE -> createCompleteNeighbors(row, col, gameBoard);
      case CARDINAL -> createAdjacentNeighbors(row, col, gameBoard);
      case ORDINAL -> createOrdinalNeighbors(row, col, gameBoard);
    }
  }

  public Neighborhood(){
    neighbors = new ArrayList<>();
  }

  private void createCompleteNeighbors(int row, int col, GameBoard gameBoard) {
    for (int i = row - 1; i <= row + 1; i++){
      for (int j = col - 1; j <= col + 1; j++){
        if (gameBoard.inBounds(i,j) && !isOriginalCell(i,j,row,col)){
          List<Integer> coordinates = createCoordinates(i, j);
          neighbors.add(coordinates);
        }
      }
    }
  }

  public List<List<Integer>> getNeighbors() {
    return neighbors;
  }

  private void createAdjacentNeighbors(int row, int col, GameBoard gameBoard) {
    for (int i = row - 1; i <= row + 1; i++){
      for (int j = col - 1; j <= col + 1; j++){
        if (gameBoard.inBounds(i,j) && !isOriginalCell(row, col, i, j) && isAdjacentCell(i,j,row,col)){
          List<Integer> coordinates = createCoordinates(i, j);
          neighbors.add(coordinates);
        }
      }
    }
  }

  private void createOrdinalNeighbors(int row, int col, GameBoard gameBoard) {
    for (int i = row - 1; i <= row + 1; i++){
      for (int j = col - 1; j <= col + 1; j++){
        if (gameBoard.inBounds(i,j) && !isOriginalCell(row, col, i, j) && !isAdjacentCell(i,j,row,col)){
          List<Integer> coordinates = createCoordinates(i, j);
          neighbors.add(coordinates);
        }
      }
    }
    System.out.println(neighbors);

  }

  private boolean isAdjacentCell(int x, int y, int row, int col) {
    return x == row || y == col;
  }

  private List<Integer> createCoordinates(int i, int j) {
    List<Integer> coordinates = new ArrayList<>();
    coordinates.add(i);
    coordinates.add(j);
    return coordinates;
  }

  private boolean isOriginalCell(int row, int col, int i, int j) {
    return (i == row && j == col);
  }


}
