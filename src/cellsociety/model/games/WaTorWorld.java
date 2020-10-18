package cellsociety.model.games;

import cellsociety.model.GameBoard;
import cellsociety.model.cells.WaTorCell;
import java.util.List;
import java.util.Random;

public class WaTorWorld extends Simulation{

  public final int REPRODUCTION_TIME = 5;

  private Random rand;


  public WaTorWorld(String config) {
    super(config);
    rand = new Random();
  }

  public WaTorWorld(String csvConfig, boolean isTest){
    super(csvConfig);
    rand = new Random();
    if (isTest){
      rand.setSeed(0);
    }
  }

  @Override
  public void updateCell(GameBoard gameBoard, int row, int col){
    WaTorCell currentCell = (WaTorCell) (getGameBoard().getCell(row, col));
    if(!isOcean(row, col)){
      gameBoard.copyCell(row, col, currentCell);
    }
    if (currentCell.isShark()){
      sharkMovement(gameBoard, row, col);
    }
    else if (currentCell.isFish()){
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
      List<Integer> oceanCoordinates = getDestinationCoordinates(neighboringOcean);
      gameBoard.swapCells(row, col, oceanCoordinates.get(0), oceanCoordinates.get(1));
      WaTorCell movedCell = (WaTorCell) (gameBoard.getCell(oceanCoordinates.get(0), oceanCoordinates.get(1)));
      movedCell.incrementSurvivalTime();
      movedCell.decrementEnergy();
      checkFishReproduction(gameBoard, row, col, movedCell);
      checkSharkDeath(gameBoard, oceanCoordinates, movedCell);
    }
  }

  private List<Integer> getDestinationCoordinates(List<List<Integer>> neighboringOcean) {
    int oceanIndex = rand.nextInt(neighboringOcean.size());
    return neighboringOcean.get(oceanIndex);
  }

  private void checkSharkDeath(GameBoard gameBoard, List<Integer> oceanCoordinates,
      WaTorCell movedCell) {
    if (movedCell.isShark() && movedCell.getEnergyPoints() <=0){
      gameBoard.copyCell(oceanCoordinates.get(0), oceanCoordinates.get(1), new WaTorCell(WaTorCell.OCEAN));
    }
  }

  private void checkFishReproduction(GameBoard gameBoard, int row, int col, WaTorCell movedCell) {
    if (movedCell.isFish() && movedCell.getSurvivalTime() >= REPRODUCTION_TIME) {
      gameBoard.copyCell(row, col, new WaTorCell(WaTorCell.FISH));
      movedCell.resetSurvivalTime();
    }
  }

  public void eatFish(GameBoard gameBoard, int row, int col){
    List<List<Integer>> neighboringFish = getGameBoard().getNeighboringPositionsOfCellState(WaTorCell.FISH, row, col);
    if (!neighboringFish.isEmpty()) {
      List<Integer> fishCoordinates = getDestinationCoordinates(neighboringFish);
      gameBoard.swapCells(row, col, fishCoordinates.get(0), fishCoordinates.get(1));
      getGameBoard().copyCell(fishCoordinates.get(0), fishCoordinates.get(1), new WaTorCell(WaTorCell.OCEAN));
      WaTorCell movedShark = (WaTorCell) (gameBoard
          .getCell(fishCoordinates.get(0), fishCoordinates.get(1)));
      if (row > fishCoordinates.get(0) || col > fishCoordinates.get(1)){
          movedShark.decrementEnergy();
      }
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
