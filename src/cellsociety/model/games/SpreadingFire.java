package cellsociety.model.games;

import cellsociety.model.GameBoard;
import cellsociety.model.cells.SpreadingFireCell;
import java.util.List;
import java.util.Random;

/**
 * Game class for SpreadingFire. This class extends Simulation.java and contains the rules
 * for the game.
 *
 * @author Franklin Wu
 */

public class SpreadingFire extends Simulation {

  public static final double probCatch = 0.5;
  public Random rand;

  /**
   * Creates an instance of the SpreadingFire class
   * @param csvConfig
   * @param cellType
   * @param neighborPolicy
   * @param edgePolicy
   * @param possibleStates
   */
  public SpreadingFire(String csvConfig, String cellType, String neighborPolicy, String edgePolicy,
      String[] possibleStates) {
    super(csvConfig, cellType, neighborPolicy, edgePolicy, possibleStates);
    rand = new Random();
  }

  /**
   * Updates the cell at row, col and sets it in the given gameboard.
   * If the cell is currently burning, it will be set as empty in the next gameboard.
   * If the cell should be burning in the next generation, it will be set as burning in th next gameboard.
   * Otherwise, it will maintain its current state.
   * @param gameBoard
   * @param row
   * @param col
   */
  @Override
  public void updateCell(GameBoard gameBoard, int row, int col) {
    if (isBurning(row, col)) {
      gameBoard.setPiece(row, col, SpreadingFireCell.EMPTY);
    } else if (burningNextGen(row, col)) {
      gameBoard.setPiece(row, col, SpreadingFireCell.BURNING);
    } else {
      gameBoard.setPiece(row, col, getGameBoard().getState(row, col));
    }
  }

  private boolean isBurning(int row, int col) { //TODO: Move to cell
    return getGameBoard().getCell(row, col).getState().equals(SpreadingFireCell.BURNING);
  }

  private boolean isEmpty(int row, int col) { //TODO: Move to cell
    return getGameBoard().getCell(row, col).getState().equals(SpreadingFireCell.EMPTY);
  }

  private boolean burningNextGen(int currentRow, int currentColumn) {

    List<List<Integer>> neighbors = getGameBoard().getCell(currentRow, currentColumn)
        .getNeighborhood().getNeighbors();
    for (List<Integer> neighbor : neighbors) {
      if (isBurning(neighbor.get(0), neighbor.get(1)) && !isEmpty(currentRow, currentColumn)) {
        double probability = rand.nextDouble();
        return probability > probCatch;
      }
    }
    return false;
  }

  /**
   * Sets the seed of the random variable to the given seed. Used for testing to
   * obtain consistent results
   * @param seed
   */
  public void setSeed(long seed) {
    rand.setSeed(seed);
  }

}
