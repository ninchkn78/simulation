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
        if (getGameBoard().inBounds(i,j)){
          neighborCount++;
        }
      }
    }
    return neighborCount;
  }

  public boolean isDifferent(int x, int y, int row, int col) { //TODO: Move to cell
    return !getState(x, y).equals(getState(row, col));
  }

  public String getState(int row, int col){
    return getGameBoard().getCell(row, col).getState();
  }

//  public Cell nextVacantCell(GameBoard gameBoard){
//    String[][] statesBoard = gameBoard.getGameBoardStates();
//    for (int i = 0; i <= statesBoard.length; i++){
//      for (int j = 0; j <= statesBoard[0].length; j++){
//        if (statesBoard[i][j].equals(SchellingCell.VACANT)) {
//          return gameBoard[i][j]
//        }
//      }
//    }
//  }

}
