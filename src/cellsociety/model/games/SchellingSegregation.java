package cellsociety.model.games;

import cellsociety.model.GameBoard;
import cellsociety.model.cells.SchellingCell;
import java.util.List;
import java.util.Random;

/**
 * Game class for Schelling's Model of Segregation. This class extends Simulation.java and contains the rules
 * for the game.
 *
 * @author Franklin Wu
 */

public class SchellingSegregation extends Simulation {


  private final double THRESHOLD = 0.5;
  private final Random rand;

  /**
   * Creates an instance of the SchellingSegregation class based on the given parameters
   * @param csvConfig
   * @param cellType
   * @param neighborPolicy
   * @param edgePolicy
   * @param possibleStates
   */

  public SchellingSegregation(String csvConfig, String cellType, String neighborPolicy,
      String edgePolicy, String[] possibleStates) {
    super(csvConfig, cellType, neighborPolicy, edgePolicy, possibleStates);
    rand = new Random();
  }

  /**
   * Updates the cell at row, col and sets it in the given gameboard.
   * If the cell will move in the next generation, it will be move to a random vacant spot.
   * Otherwise, it will maintain its current state and stay put.
   * @param gameBoard
   * @param row
   * @param col
   */
  @Override
  public void updateCell(GameBoard gameBoard, int row, int col) {
    if (!isVacant(row, col)) {
      gameBoard.setPiece(row, col, getGameBoard().getState(row, col));
    }
    if (willMove(row, col)) {
      List<List<Integer>> vacantCells = getGameBoard()
          .getAllPositionsOfCellState(SchellingCell.VACANT);
      int vacantIndex = rand.nextInt(vacantCells.size());
      List<Integer> vacantCoordinates = vacantCells.get(vacantIndex);
      gameBoard.swapCells(row, col, vacantCoordinates.get(0), vacantCoordinates.get(1));
    }
  }

  private int countNeighbors(int row, int col) {
    int neighborCount = 0;
    List<List<Integer>> neighbors = getGameBoard().getCell(row, col).getNeighborhood()
        .getNeighbors();
    for (List<Integer> neighbor : neighbors) {
      if (!isVacant(neighbor.get(0), neighbor.get(1))) {
        neighborCount++;
      }
    }
    return neighborCount;
  }

  private boolean isOppositeAgent(int x, int y, int row, int col) { //TODO: Move to cell
    return !getState(x, y).equals(SchellingCell.VACANT) && !getState(x, y)
        .equals(getState(row, col));
  }

  private boolean isVacant(int row, int col) { //TODO: Move to cell
    return getState(row, col).equals(SchellingCell.VACANT);
  }

  private String getState(int row, int col) {
    return getGameBoard().getCell(row, col).getState();
  }

  private int countOppositeAgent(int row, int col) {
    int oppositeAgentCount = 0;
    List<List<Integer>> neighbors = getGameBoard().getCell(row, col).getNeighborhood()
        .getNeighbors();
    for (List<Integer> neighbor : neighbors) {
      if (isOppositeAgent(neighbor.get(0), neighbor.get(1), row, col)) {
        oppositeAgentCount++;
      }
    }
    return oppositeAgentCount;
  }

  private boolean willMove(int row, int col) {
    if (isVacant(row, col)) {
      return false;
    }
    int neighborCount = countNeighbors(row, col);
    int oppositeAgentCount = countOppositeAgent(row, col);
    return neighborCount - oppositeAgentCount < neighborCount * THRESHOLD;
  }

  /**
   * Sets a seed for the random variable. Used for testing to obtain consistent results
   * @param seed
   */
  public void setSeed(long seed) {
    rand.setSeed(seed);
  }

}
