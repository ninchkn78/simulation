package cellsociety.model.games;

import cellsociety.model.GameBoard;
import cellsociety.model.cells.PercolationCell;
import java.util.List;

public class Percolation extends Simulation {

  public Percolation(String csvConfig, String cellType, String neighborPolicy, String edgePolicy) {
    super(csvConfig, cellType, neighborPolicy, edgePolicy);
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
    List<List<Integer>> neighbors = getGameBoard().getCell(currentRow,currentColumn).getNeighborhood().getNeighbors();
    for (List<Integer> neighbor : neighbors){
      if (isFull(neighbor.get(0), neighbor.get(1))){
        return true;
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
