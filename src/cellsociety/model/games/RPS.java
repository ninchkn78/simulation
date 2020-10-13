package cellsociety.model.games;

import cellsociety.model.GameBoard;

public class RPS extends Simulation{

  public static final int THRESHOLD = 3;

  public RPS(String csvConfig){
    super(csvConfig);
  }


  @Override
  public void updateCell(GameBoard gameBoard, int row, int col) {
    if (isDefeated(row,col)){
      gameBoard.setPiece(row, col, getOpponent(row, col));
    }
  }

  private boolean isDefeated(int currentRow, int currentColumn) {
    return countNeighboringOpponents(currentRow, currentColumn) > THRESHOLD;
  }

  public String getOpponent(int row, int col){
    int state = Integer.parseInt(getGameBoard().getCell(row, col).getState());
    return Integer.toString((state + 1)%3);
  }

  public int countNeighboringOpponents(int currentRow, int currentColumn) {
    int opponentCount = 0;
    for (int i = currentRow - 1; i <= currentRow + 1; i++){
      for (int j = currentColumn - 1; j <= currentColumn + 1; j++){
        if (getGameBoard().inBounds(i,j) && isOpponent(i,j, currentRow, currentColumn)){ //TODO: make this not ugly af
          opponentCount++;
        }
      }
    }
    return opponentCount;
  }

  public boolean isOpponent(int x, int y, int currentRow, int currentColumn){
    String currentState = getGameBoard().getCell(x, y).getState();
    String opponentState = getOpponent(currentRow, currentColumn);
    return currentState.equals(opponentState);
  }




}
