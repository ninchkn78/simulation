package cellsociety.model.games;

import cellsociety.model.GameBoard;
import cellsociety.model.cells.PercolationCell;

public class RPS extends Simulation{

  public RPS(String csvConfig){
    super(csvConfig);
  }


  @Override
  public void updateCell(GameBoard gameBoard, int row, int col) {
    if (isDefeated(row,col)){
      gameBoard.setPiece(row, col, PercolationCell.FULL);
    }
  }

  private boolean isDefeated(int row, int col) {

  }


}
