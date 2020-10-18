package cellsociety.model.games;

import cellsociety.model.GameBoard;
import cellsociety.model.cells.WaTorCell;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class WaTorWorld extends Simulation{

  public final int REPRODUCTION_TIME = 5;

  private Random rand;


  public WaTorWorld(String config) {
    super(config);
    rand = new Random();
  }

  @Override
  public void updateCell(GameBoard gameBoard, int row, int col){
    WaTorCell currentCell = (WaTorCell) (getGameBoard().getCell(row, col));
    if(!isOcean(row, col)){
      gameBoard.copyCell(row, col, currentCell);
    }
    if (currentCell.isShark()){
      sharkMovement(gameBoard, row, col);
    }else if (currentCell.isFish()){
      currentCell.incrementSurvivalTime();
      fishMovement(gameBoard, row, col);
    }
  }

  public boolean isShark(int row, int col){
    return getGameBoard().getState(row, col).equals(WaTorCell.SHARK);
  }

  public boolean isFish(int row, int col){
    return getGameBoard().getState(row, col).equals(WaTorCell.FISH);
  }

  public boolean isOcean(int row, int col){
    return getGameBoard().getState(row, col).equals(WaTorCell.OCEAN);
  }

  public void fishMovement(GameBoard gameBoard, int row, int col){
    moveToOcean(gameBoard, row, col);
  }

  public void moveToOcean(GameBoard gameBoard, int row, int col){
    List<List<Integer>> neighboringOcean = getGameBoard().getNeighboringPositionsOfCellState(WaTorCell.OCEAN, row, col);

    if (!neighboringOcean.isEmpty()) {
      int oceanIndex = rand.nextInt(neighboringOcean.size());
      List<Integer> oceanCoordinates = neighboringOcean.get(oceanIndex);
      System.out.println("Fish move to: " + oceanCoordinates);
      gameBoard.swapCells(row, col, oceanCoordinates.get(0), oceanCoordinates.get(1));
      WaTorCell movedCell = (WaTorCell) (gameBoard.getCell(oceanCoordinates.get(0), oceanCoordinates.get(1)));

      //check if fish should reproduce
      if (movedCell.isFish() && movedCell.getSurvivalTime() >= REPRODUCTION_TIME) {
        gameBoard.copyCell(row, col, new WaTorCell(WaTorCell.FISH));
        movedCell.resetSurvivalTime();
      }
      //check if this was a shark, in which case we decrease energy
      if (movedCell.isShark()) {
        movedCell.decrementEnergy();
      }

      //check if shark should die
      if (movedCell.isShark() && movedCell.getEnergyPoints() <=0){
        gameBoard.copyCell(oceanCoordinates.get(0), oceanCoordinates.get(1), new WaTorCell(WaTorCell.OCEAN));
      }

    }

  }

  public void eatFish(GameBoard gameBoard, int row, int col){
    List<List<Integer>> neighboringFish = getGameBoard().getNeighboringPositionsOfCellState(WaTorCell.FISH, row, col);
    if (!neighboringFish.isEmpty()) {
      int fishIndex = rand.nextInt(neighboringFish.size());
      List<Integer> fishCoordinates = neighboringFish.get(fishIndex);
      System.out.println("Shark move to: " + fishCoordinates);
      gameBoard.swapCells(row, col, fishCoordinates.get(0), fishCoordinates.get(1));
      //increment shark energy and survival time
      WaTorCell movedShark = (WaTorCell) (gameBoard
          .getCell(fishCoordinates.get(0), fishCoordinates.get(1)));
      movedShark.incrementSurvivalTime();
    }

  }

  public void sharkMovement(GameBoard gameBoard, int row, int col){
    List<List<Integer>> neighboringFish = getGameBoard().getNeighboringPositionsOfCellState(WaTorCell.FISH, row, col);
    if(!neighboringFish.isEmpty()){
      eatFish(gameBoard, row, col);
    }
    else{
      moveToOcean(gameBoard, row, col);
    }
  }

}
