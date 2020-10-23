package cellsociety.model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * The Neighborhood class creates and stores a list of all of the neighbors of a given cell.
 *
 * @author Franklin Wu
 */
public class Neighborhood {

  public List<List<Integer>> neighbors;
  public String neighborPolicy;
  public String edgePolicy;

  /**
   * Creates an instance of the Neighborhood class based on the given parameters
   * @param row
   * @param col
   * @param gameBoard
   * @param neighborPolicy
   * @param edgePolicy
   */
  public Neighborhood(int row, int col, GameBoard gameBoard, String neighborPolicy,
      String edgePolicy) {
    neighbors = new ArrayList<>();
    this.neighborPolicy = neighborPolicy;
    this.edgePolicy = edgePolicy;
    createNeighborhood(row, col, gameBoard);
  }

  /**
   * Default contructor for Neighborhood class
   */
  public Neighborhood() {
    neighbors = new ArrayList<>();
  }

  private void createNeighborhood(int row, int col, GameBoard gameBoard) {
    try {
      Method method = this.getClass()
          .getDeclaredMethod("create" + neighborPolicy + "Neighbors", int.class, int.class,
              GameBoard.class);
      method.invoke(this, row, col, gameBoard);
    } catch (SecurityException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
      e.printStackTrace();
    }
  }

  private void createCompleteNeighbors(int row, int col, GameBoard gameBoard) {
    for (int i = row - 1; i <= row + 1; i++) {
      for (int j = col - 1; j <= col + 1; j++) {
        if (!gameBoard.inBounds(i, j)) {
          handleOutOfBounds(i, j, gameBoard);
        } else if (!isOriginalCell(i, j, row, col)) {
          List<Integer> coordinates = createCoordinates(i, j);
          neighbors.add(coordinates);
        }
      }
    }
  }

  private void createCardinalNeighbors(int row, int col, GameBoard gameBoard) {
    for (int i = row - 1; i <= row + 1; i++) {
      for (int j = col - 1; j <= col + 1; j++) {
        if (!gameBoard.inBounds(i, j)) {
          handleOutOfBounds(i, j, gameBoard);
        } else if (!isOriginalCell(i, j, row, col) && isAdjacentCell(i, j, row, col)) {
          List<Integer> coordinates = createCoordinates(i, j);
          neighbors.add(coordinates);
        }
      }
    }
  }

  private void createOrdinalNeighbors(int row, int col, GameBoard gameBoard) {
    for (int i = row - 1; i <= row + 1; i++) {
      for (int j = col - 1; j <= col + 1; j++) {
        if (gameBoard.inBounds(i, j) && !isOriginalCell(row, col, i, j) && !isAdjacentCell(i, j,
            row, col)) {
          List<Integer> coordinates = createCoordinates(i, j);
          neighbors.add(coordinates);
        }
      }
    }
  }

  private void handleOutOfBounds(int row, int col, GameBoard gameBoard) {
    try {
      Method method = this.getClass()
          .getDeclaredMethod("handle" + edgePolicy + "OutOfBounds", int.class, int.class,
              GameBoard.class);
      method.invoke(this, row, col, gameBoard);
    } catch (SecurityException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
      e.printStackTrace();
    }
  }


  private boolean isCornerNeighbor(int row, int col, GameBoard gameBoard) {
    if (row == -1 || row == gameBoard.getHeight()) {
      return col == -1 || col == gameBoard.getWidth();
    }
    return false;
  }

  private void handleFiniteOutOfBounds(int row, int col, GameBoard gameBoard) {
    if (gameBoard.inBounds(row, col)) {
      neighbors.add(createCoordinates(row, col));
    }
  }

  private void handleToroidalOutOfBounds(int row, int col, GameBoard gameBoard) {
    if (row < 0 || row >= gameBoard.getHeight()) {
      row = (row + gameBoard.getHeight()) % gameBoard.getHeight();
    }
    if (col < 0 || col >= gameBoard.getWidth()) {
      col = (col + gameBoard.getWidth()) % gameBoard.getWidth();
    }
    neighbors.add(createCoordinates(row, col));
  }

  private void handleCrossSurfaceOutOfBounds(int row, int col, GameBoard gameBoard) {
    if (isCornerNeighbor(row, col, gameBoard)) {
      return;
    }
    if (row < 0 || row >= gameBoard.getHeight()) {
      row = gameBoard.getHeight() - 1;
      col = gameBoard.getWidth() - col - 1;
      neighbors.add(createCoordinates(row, col));
    } else if (col < 0 || col >= gameBoard.getWidth()) {
      row = gameBoard.getHeight() - row - 1;
      col = gameBoard.getWidth() - 1;
      neighbors.add(createCoordinates(row, col));
    }
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

  private boolean isAdjacentCell(int x, int y, int row, int col) {
    return x == row || y == col;
  }

  /**
   * Returns the list of coordinates of the neighbors
   * @return
   */
  public List<List<Integer>> getNeighbors() {
    return neighbors;
  }

}
