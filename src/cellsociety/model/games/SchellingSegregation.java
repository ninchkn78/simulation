package cellsociety.model.games;

import cellsociety.model.GameBoard;
import cellsociety.model.cells.Cell;
import cellsociety.model.cells.SchellingCell;
import cellsociety.model.cells.SpreadingFireCell;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class SchellingSegregation extends Simulation {


  private final double THRESHOLD = 0.5;
  private Random rand;

  public SchellingSegregation(String csvConfig, String cellType, String neighborPolicy){
    super(csvConfig, cellType,neighborPolicy);
    rand = new Random();
  }

  public SchellingSegregation(String csvConfig, String cellType, String neighborPolicy, boolean isTest){
    super(csvConfig, cellType, neighborPolicy);
    rand = new Random();
    if (isTest){
      rand.setSeed(0);
    }
  }


  @Override
  public void updateCell(GameBoard gameBoard, int row, int col) {
    if(!isVacant(row, col)){
      gameBoard.setPiece(row, col, getGameBoard().getState(row, col));
    }
    if(willMove(row, col)) {
      List<List<Integer>> vacantCells = getGameBoard()
          .getPositionsOfCellState(SchellingCell.VACANT);
      int vacantIndex = rand.nextInt(vacantCells.size());
      List<Integer> vacantCoordinates = vacantCells.get(vacantIndex);
      gameBoard.swapCells(row, col, vacantCoordinates.get(0), vacantCoordinates.get(1));
    }
  }

  public int countNeighbors(int row, int col) {
    int neighborCount = 0;
    for (int i = row - 1; i <= row + 1; i++){
      for (int j = col - 1; j <= col + 1; j++){
        if (getGameBoard().inBounds(i,j) && !isVacant(i, j) && !(row == i && col == j)){
          neighborCount++;
        }
      }
    }
    return neighborCount;
  }

  public boolean isOppositeAgent(int x, int y, int row, int col) { //TODO: Move to cell
    return !getState(x,y).equals(SchellingCell.VACANT) && !getState(x, y).equals(getState(row, col));
  }

  public boolean isVacant(int row, int col) { //TODO: Move to cell
    return getState(row,col).equals(SchellingCell.VACANT);
  }

  public String getState(int row, int col) {
    return getGameBoard().getCell(row, col).getState();
  }

  public int countOppositeAgent(int row, int col){
    int oppositeAgentCount = 0;
    for (int i = row - 1; i <= row + 1; i++){
      for (int j = col - 1; j <= col + 1; j++){
        if (getGameBoard().inBounds(i,j) && isOppositeAgent(i, j, row, col)){
          oppositeAgentCount += 1;
        }
      }
    }
    return oppositeAgentCount;
  }

  public boolean willMove(int row, int col){
    if (isVacant(row, col)){
      return false;
    }
    int neighborCount = countNeighbors(row, col);
    int oppositeAgentCount = countOppositeAgent(row, col);
    return neighborCount - oppositeAgentCount < neighborCount * THRESHOLD;
  }








}
