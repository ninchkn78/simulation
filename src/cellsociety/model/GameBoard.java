package cellsociety.model;

import cellsociety.model.cells.Cell;
import exceptions.InvalidCSVFormatException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The GameBoard class is a representation of the simulation grid. It contains a 2D array of cells
 * and states.
 *
 * @author Franklin Wu
 */
public class GameBoard {

  private final int width;
  private final int height;
  private final Cell[][] gameBoardCells;
  private final String cellType;
  private final String neighborPolicy;
  private final String edgePolicy;
  private final String[][] gameBoardStates;

  /**
   * Creates a GameBoard based on its dimensions, the cell type, the neighbor policy, and the edge policy
   * @param width
   * @param height
   * @param cellType
   * @param neighborPolicy
   * @param edgePolicy
   */
  public GameBoard(int width, int height, String cellType, String neighborPolicy,
      String edgePolicy) {
    this.width = width;
    this.height = height;
    this.cellType = cellType;
    this.neighborPolicy = neighborPolicy;
    this.edgePolicy = edgePolicy;
    this.gameBoardCells = initializeGameBoardCells(width, height);
    this.gameBoardStates = new String[height][width];
    setGameBoardStates(gameBoardCells);
  }

  /**
   * Creates a GameBoard based on a pre-existing initial configuration, the cell type, the neighbor policy, and the edge policy
   * @param initialStateConfig
   * @param cellType
   * @param neighborPolicy
   * @param edgePolicy
   * @param possibleStates
   * @throws InvalidCSVFormatException
   */
  public GameBoard(String[][] initialStateConfig, String cellType, String neighborPolicy,
      String edgePolicy, String[] possibleStates) throws InvalidCSVFormatException {
    this.width = initialStateConfig[0].length;
    this.height = initialStateConfig.length;
    this.cellType = cellType;
    this.neighborPolicy = neighborPolicy;
    this.edgePolicy = edgePolicy;
    this.gameBoardCells = createCellConfiguration(initialStateConfig);
    this.gameBoardStates = initialStateConfig;
    validateStates(possibleStates);
  }

  private void validateStates(String[] possibleStates) throws InvalidCSVFormatException {
    Set<String> statesSet = new HashSet<>(Arrays.asList(possibleStates));
    for (int i = 0; i < gameBoardStates.length; i++) {
      for (int j = 0; j < gameBoardStates[i].length; j++) {
        if (!statesSet.contains(gameBoardStates[i][j])) {
          throw new InvalidCSVFormatException("the CSV file has invalid states :((");
        }
      }
    }
  }

  private Cell[][] initializeGameBoardCells(int width, int height) {
    Cell[][] cellConfig = new Cell[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        cellConfig[i][j] = createCell("0", i, j);
      }
    }
    return cellConfig;
  }

  private Neighborhood createNeighborhood(int row, int col) {
    return new Neighborhood(row, col, this, neighborPolicy, edgePolicy);
  }

  /**
   * Returns the cell at row, col
   * @param row
   * @param col
   * @return
   */
  public Cell getCell(int row, int col) {
    return gameBoardCells[row][col];
  }

  /**
   * Returns the state of the cell at row,col
   * @param row
   * @param col
   * @return
   */
  public String getState(int row, int col) {
    return gameBoardStates[row][col];
  }

  /**
   * Returns true if the cell is in the grid
   * @param row
   * @param col
   * @return
   */
  public boolean inBounds(int row, int col) {
    return (row >= 0 && col >= 0) && (row < height && col < width);
  }

  /**
   * Returns a list of the coordinates of all cells with the given state in the game board
   * @param state
   * @return
   */
  public List<List<Integer>> getAllPositionsOfCellState(String state) {
    List<List<Integer>> cellsList = new ArrayList<>();
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (gameBoardStates[i][j].equals(state)) {
          cellsList.add(createCoordinates(i, j));
        }
      }
    }
    return cellsList;
  }


  private List<Integer> createCoordinates(int row, int col) {
    List<Integer> coordinates = new ArrayList<>();
    coordinates.add(row);
    coordinates.add(col);
    return coordinates;
  }

  /**
   * Returns a list of the coordinates of all neighboring cells with the given state in the game board
   * @param state
   * @return
   */
  public List<List<Integer>> getNeighboringPositionsOfCellState(String state, int row, int col) {
    List<List<Integer>> cellsList = new ArrayList<>();
    List<List<Integer>> neighbors = getCell(row, col).getNeighborhood().getNeighbors();
    for (List<Integer> neighbor : neighbors) {
      if (gameBoardStates[neighbor.get(0)][neighbor.get(1)].equals(state)) {
        cellsList.add(createCoordinates(neighbor.get(0), neighbor.get(1)));
      }
    }
    return cellsList;
  }

  /**
   * Sets the cell at row,col to the given state
   * @param row
   * @param col
   * @param state
   */
  public void setPiece(int row, int col, String state) {
    gameBoardCells[row][col].setState(state);
    gameBoardStates[row][col] = state;
  }

  /**
   * Returns the width of the game board
   * @return
   */
  public int getWidth() {
    return width;
  }

  /**
   * Returns the height of the game board
   * @return
   */
  public int getHeight() {
    return height;
  }

  /**
   * Returns a 2D String array of the current game board states
   * @return
   */
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

  private Cell[][] createCellConfiguration(String[][] stateConfig) {
    Cell[][] cellConfig = new Cell[stateConfig.length][stateConfig[0].length];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        cellConfig[i][j] = createCell(stateConfig[i][j], i, j);
      }
    }
    return cellConfig;
  }

  private Cell createCell(String state, int row, int col) {
    Class operation;
    try {
      operation = Class.forName("cellsociety.model.cells." + cellType);
      return (Cell) operation.getConstructor(String.class, Neighborhood.class)
          .newInstance(state, createNeighborhood(row, col));
    } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Given a tri consumer, enacts this function on all states in the gameboard
   * @param updateCellState
   */
  public void enactFunctionOnStates(TriConsumer<Integer, Integer, String> updateCellState) {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        updateCellState.accept(i, j, gameBoardStates[i][j]);
      }
    }
  }

  /**
   * Sets the coordinate row,col to the given cell
   * @param row
   * @param col
   * @param cell
   */
  public void copyCell(int row, int col, Cell cell) {
    gameBoardCells[row][col] = cell;
    gameBoardStates[row][col] = cell.getState();
  }

  /**
   * Swaps two cells between row1,col1 and row2,col2
   * @param row1
   * @param col1
   * @param row2
   * @param col2
   */
  public void swapCells(int row1, int col1, int row2, int col2) {
    Cell firstCell = getCell(row1, col1);
    Cell secondCell = getCell(row2, col2);
    firstCell.setNeighbors(createNeighborhood(row2, col2));
    secondCell.setNeighbors(createNeighborhood(row1, col1));
    gameBoardCells[row1][col1] = secondCell;
    gameBoardCells[row2][col2] = firstCell;
    gameBoardStates[row1][col1] = secondCell.getState();
    gameBoardStates[row2][col2] = firstCell.getState();
  }

}

