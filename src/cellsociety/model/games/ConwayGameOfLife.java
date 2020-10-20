package cellsociety.model.games;


import cellsociety.model.GameBoard;
import cellsociety.model.Neighborhood;
import cellsociety.model.cells.Cell;
import cellsociety.model.cells.ConwayCell;
import java.util.List;

public class ConwayGameOfLife extends Simulation {

  public ConwayGameOfLife(String csvConfig, String cellType, String neighborPolicy, String edgePolicy, String[] possibleStates){
    super(csvConfig, cellType, neighborPolicy, edgePolicy, possibleStates);
  }

  public boolean aliveNextGen(int row, int col) {
    if (isAlive(row, col)) {
      return countLivingNeighbors(row, col) > 1 && countLivingNeighbors(row, col) < 4;
    }
    return countLivingNeighbors(row, col) == 3;
  }

  public boolean isAlive(int row, int col) {
    return getGameBoard().getCell(row, col).getState().equals(ConwayCell.ALIVE);
  }

  @Override
  public void updateCell(GameBoard nextBoard, int row, int col) {
    if (aliveNextGen(row, col)) {
      nextBoard.setPiece(row, col, ConwayCell.ALIVE);
    } else {
      nextBoard.setPiece(row, col, ConwayCell.DEAD);
    }
  }

  public int countLivingNeighbors(int currentRow, int currentColumn) {
    int aliveCount = 0;
    List<List<Integer>> neighbors = getGameBoard().getCell(currentRow,currentColumn).getNeighborhood().getNeighbors();
    for (List<Integer> neighbor : neighbors){
      if (isAlive(neighbor.get(0), neighbor.get(1))){
        aliveCount++;
      }
    }
    return aliveCount;
  }
}