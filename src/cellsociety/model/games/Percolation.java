package cellsociety.model.games;

import cellsociety.model.GameBoard;
import cellsociety.model.cells.PercolationCell;
import java.util.List;

/**
 * Game class for Percolation. This class extends Simulation.java and contains the rules
 * for the game.
 *
 * @author Franklin Wu
 */

public class Percolation extends Simulation {

  /**
   * Creates an instance of the Percolation class
   * @param csvConfig
   * @param cellType
   * @param neighborPolicy
   * @param edgePolicy
   * @param possibleStates
   */
  public Percolation(String csvConfig, String cellType, String neighborPolicy, String edgePolicy,
      String[] possibleStates) {
    super(csvConfig, cellType, neighborPolicy, edgePolicy, possibleStates);
  }

  /**
   * Updates the cell at row, col and sets it in the given gameboard.
   * If the cell should be full in the next generation, it will be set as full in the next gameboard.
   * Otherwise, it will maintain its current state.
   * @param gameBoard
   * @param row
   * @param col
   */
  @Override
  public void updateCell(GameBoard gameBoard, int row, int col) {
    if (fullNextGen(row, col)) {
      gameBoard.setPiece(row, col, PercolationCell.FULL);
    } else {
      gameBoard.setPiece(row, col, getGameBoard().getState(row, col));
    }
  }

  private boolean fullNextGen(int row, int col) {
    if (!isOpen(row, col)) {
      return false;
    }

    if (isTopRow(row)) {
      return true;
    }
    List<List<Integer>> neighbors = getGameBoard().getCell(row, col).getNeighborhood()
        .getNeighbors();
    for (List<Integer> neighbor : neighbors) {
      if (isFull(neighbor.get(0), neighbor.get(1))) {
        return true;
      }
    }
    return false;
  }

  private boolean isTopRow(int row) {
    return row == 0;
  }


  private boolean isFull(int row, int col) {
    return getGameBoard().getCell(row, col).getState().equals(PercolationCell.FULL);
  }

  private boolean isOpen(int row, int col) {
    return getGameBoard().getCell(row, col).getState().equals(PercolationCell.OPEN);
  }

}
