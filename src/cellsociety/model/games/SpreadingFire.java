package cellsociety.model.games;

import cellsociety.model.GameBoard;
import cellsociety.model.cells.SpreadingFireCell;
import java.util.Random;

public class SpreadingFire extends Simulation {

  public static final double probCatch = 0.5;

  public SpreadingFire(String csvConfig) {
    super(csvConfig);
  }


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

  public boolean isDirectNeighbor(int x, int y, int currentRow, int currentCol) {
    return (x == currentRow || y == currentCol);
  }

  public boolean isBurning(int row, int col) { //TODO: Move to cell
    return getGameBoard().getCell(row, col).getState().equals(SpreadingFireCell.BURNING);
  }

  public boolean isEmpty(int row, int col) { //TODO: Move to cell
    return getGameBoard().getCell(row, col).getState().equals(SpreadingFireCell.EMPTY);
  }

  public boolean burningNextGen(int currentRow, int currentColumn) {
    if (isEmpty(currentRow, currentColumn)) {
      return false;
    }
    for (int i = currentRow - 1; i <= currentRow + 1; i++) {
      for (int j = currentColumn - 1; j <= currentColumn + 1; j++) {
        if (getGameBoard().inBounds(i, j) &&
            isDirectNeighbor(i, j, currentRow, currentColumn) &&
            isBurning(i, j)) {
          Random rand = new Random();
          double probability = rand.nextDouble();
          return probability > probCatch;
        }
      }
    }
    return false;
  }

}
