package cellsociety.model.games;

import cellsociety.model.GameBoard;
import cellsociety.model.cells.Cell;
import cellsociety.model.cells.SchellingCell;
import cellsociety.model.cells.SpreadingFireCell;

public class SchellingSegregation extends Simulation{

  public SchellingSegregation(String csvConfig){
    super(csvConfig);
  }


  @Override
  public void updateCell(GameBoard gameBoard, int row, int col) {

  }

  public int countNeighbors(int row, int col){
    int neighborCount = 0;
    for (int i = row - 1; i <= row + 1; i++){
      for (int j = col - 1; j <= col + 1; j++){
        if (getGameBoard().inBounds(i,j) && !isVacant(i, j)){
          neighborCount++;
        }
      }
    }
    return neighborCount;
  }

  public boolean isDifferent(int x, int y, int row, int col) { //TODO: Move to cell
    return !getState(x, y).equals(getState(row, col));
  }

  public boolean isVacant(int row, int col) { //TODO: Move to cell
    return !getState(row,col).equals(SchellingCell.VACANT);
  }

  public String getState(int row, int col){
    return getGameBoard().getCell(row, col).getState();
  }

  public int countOpponents(int row, int col){
    int opponentCount = 0;
    for (int i = row - 1; i <= row + 1; i++){
      for (int j = col - 1; j <= col + 1; j++){
        if (getGameBoard().inBounds(i,j) && isDifferent(i, j, row, col)){
          opponentCount++;
        }
      }
    }
    return opponentCount;
  }
//
//  public boolean willMove(int row, int col){
//    int neighborCount = countNeighbors(row, col);
//    int opponentCount = countOpponents()
//    return opponentCount > neighborCount * THRESHOLD;
//  }

}
