package cellsociety.model.games;

import cellsociety.model.GameBoard;
import cellsociety.model.cells.PercolationCell;

public class Percolation extends Simulation {

  public Percolation(String csvConfig, String cellType, String[] possibleStates) {
    super(csvConfig, cellType, possibleStates);
  }

  @Override
  public void updateCell(GameBoard gameBoard, int row, int col) {
    if (fullNextGen(row, col)) {
      gameBoard.setPiece(row, col, PercolationCell.FULL);
    } else {
      gameBoard.setPiece(row, col, getGameBoard().getState(row, col));
    }
  }

  public boolean fullNextGen(int currentRow, int currentColumn) {
    if (!isOpen(currentRow, currentColumn)) {
      return false;
    }
    for (int i = currentRow - 1; i <= currentRow + 1; i++) {
      for (int j = currentColumn - 1; j <= currentColumn + 1; j++) {
        if (getGameBoard().inBounds(i, j) && isFull(i, j)) {
          return true;
        }
      }
    }
    return false;
  }


  public boolean isFull(int row, int col) { //TODO: Move to cell
    return getGameBoard().getCell(row, col).getState().equals(PercolationCell.FULL);
  }

  public boolean isOpen(int row, int col) { //TODO: MovetoCell
    return getGameBoard().getCell(row, col).getState().equals(PercolationCell.OPEN);
  }

}
