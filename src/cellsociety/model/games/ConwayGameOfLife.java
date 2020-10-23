package cellsociety.model.games;


import cellsociety.model.GameBoard;
import cellsociety.model.cells.ConwayCell;
import java.util.List;

/**
 * Game class for Conway's Game of Life. This class extends Simulation.java and contains the rules
 * for the game.
 *
 * @author Franklin Wu
 */

public class ConwayGameOfLife extends Simulation {

  public final int ALIVE_THRESHOLD = 3;
  public final int DEAD_THRESHOLD = 2;

  /**
   * Creates a ConwayGameOfLife object
   * @param csvConfig
   * @param cellType
   * @param neighborPolicy
   * @param edgePolicy
   * @param possibleStates
   */
  public ConwayGameOfLife(String csvConfig, String cellType, String neighborPolicy,
      String edgePolicy, String[] possibleStates) {
    super(csvConfig, cellType, neighborPolicy, edgePolicy, possibleStates);
  }

  /**
   * Returns true if the cell at row, col will be alive in the next generation
   * @param row
   * @param col
   * @return
   */
  public boolean aliveNextGen(int row, int col) {
    if (isAlive(row, col)) {
      return countLivingNeighbors(row, col) >= DEAD_THRESHOLD
          && countLivingNeighbors(row, col) <= ALIVE_THRESHOLD;
    }
    return countLivingNeighbors(row, col) == ALIVE_THRESHOLD;
  }

  /**
   * Returns true if the cell at row, col is alive
   * @param row
   * @param col
   * @return
   */
  public boolean isAlive(int row, int col) {
    return getGameBoard().getCell(row, col).getState().equals(ConwayCell.ALIVE);
  }

  /**
   * Updates the cell at row, col and sets it in the given gameboard.
   * If the cell is alive in the next generation, it will be alive in the next gameboard.
   * Otherwise, it will be dead.
   * @param nextBoard
   * @param row
   * @param col
   */
  @Override
  public void updateCell(GameBoard nextBoard, int row, int col) {
    if (aliveNextGen(row, col)) {
      nextBoard.setPiece(row, col, ConwayCell.ALIVE);
    } else {
      nextBoard.setPiece(row, col, ConwayCell.DEAD);
    }
  }

  /**
   * Returns the number of alive neighbors for the cell at row, col
   * @param row
   * @param col
   * @return
   */
  public int countLivingNeighbors(int row, int col) {
    int aliveCount = 0;

    List<List<Integer>> neighbors = getGameBoard().getCell(row, col).getNeighborhood()
        .getNeighbors();
    for (List<Integer> neighbor : neighbors) {
      if (isAlive(neighbor.get(0), neighbor.get(1))) {
        aliveCount++;
      }
    }
    return aliveCount;
  }
}