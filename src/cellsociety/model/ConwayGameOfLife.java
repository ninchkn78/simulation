package cellsociety.model;


public class ConwayGameOfLife extends Simulation{


  public ConwayGameOfLife(String config){
    super(config);
  }

  public boolean aliveNextGen(int row, int col) {
    Cell currentCell = getGameBoard().getCell(row, col);
    if (isAlive(currentCell)) {
      return countLivingNeighbors(row, col) > 1 && countLivingNeighbors(row, col) < 4;
    }
    return countLivingNeighbors(row, col) == 3;
  }

  public boolean isAlive(Cell cell){
    return cell.getState().equals(ConwayCell.ALIVE);
  }

  @Override
  public void updateCell(GameBoard nextBoard, int row, int col){
    if (aliveNextGen(row,col)){
      nextBoard.setPiece(row, col, ConwayCell.ALIVE);
    }
    else{
      nextBoard.setPiece(row, col, ConwayCell.DEAD);
    }
  }





  public void handleMouseInput(double x, double y) {
//    int scaledHeight = boardPanel.getHeight()/g.getHeight();
//    int scaledWidth =  boardPanel.getWidth()/g.getWidth();
//
//    int x = e.getX()/scaledWidth;
//    int y = e.getY()/scaledHeight;
//    g.toggleState(x, y);
  }

  public int countLivingNeighbors(int currentRow, int currentColumn) {
    int aliveCount = 0;
    for (int i = currentRow - 1; i <= currentRow + 1; i++){
      for (int j = currentColumn - 1; j <= currentColumn + 1; j++){
        if (getGameBoard().isValidLocation(i,j) && isAlive(getGameBoard().getCell(i, j))){ //TODO: make this not ugly af
          aliveCount++;
        }
      }
    }
    if (isAlive(getGameBoard().getCell(currentRow, currentColumn))){
      aliveCount--;
    }
    return aliveCount;
  }

}
