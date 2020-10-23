package cellsociety.model.games;

import cellsociety.model.GameBoard;
import cellsociety.model.Neighborhood;
import cellsociety.model.cells.WaTorCell;
import java.util.List;
import java.util.Random;

/**
 * Game class for WatorWorld. This class extends Simulation.java and contains the rules
 * for the game.
 *
 * @author Franklin Wu
 */

public class WaTorWorld extends Simulation {

  private static final int DEAD_ENERGY = 0;
  public final int REPRODUCTION_TIME = 5;

  private final Random rand;

  /**
   * Creates an instance of the WaTorWorld class with the given parameters.
   * @param config
   * @param cellType
   * @param neighborPolicy
   * @param edgePolicy
   * @param possibleStates
   */
  public WaTorWorld(String config, String cellType, String neighborPolicy, String edgePolicy,
      String[] possibleStates) {
    super(config, cellType, neighborPolicy, edgePolicy, possibleStates);
    rand = new Random();
  }

  /**
   * Updates the cell at row, col and sets it in the given gameboard.
   * If the cell is a shark, it will move according to the shark movement rules.
   * If the cell is a fish, it will move according to the fish movement rules.
   * Otherwise, it will maintain its current state and stay put.
   * @param gameBoard
   * @param row
   * @param col
   */
  @Override
  public void updateCell(GameBoard gameBoard, int row, int col) {
    WaTorCell currentCell = (WaTorCell) (getGameBoard().getCell(row, col));
    if (!isOcean(row, col)) {
      gameBoard.copyCell(row, col, currentCell);
    }
    if (currentCell.isShark()) {
      sharkMovement(gameBoard, row, col);
    } else if (currentCell.isFish()) {
      fishMovement(gameBoard, row, col);
    }
  }


  private boolean isOcean(int row, int col) {
    return getGameBoard().getState(row, col).equals(WaTorCell.OCEAN);
  }

  /**
   * Fish moves to the nearest adjacent ocean square
   * @param gameBoard
   * @param row
   * @param col
   */
  private void fishMovement(GameBoard gameBoard, int row, int col) {
    moveToOcean(gameBoard, row, col);
  }

  /**
   * The cell at row, col moves to the nearest adjacent ocean square.
   * If none exists, there is no movement.
   * Survival time increases and energy decreases
   * If movement occurs and the cell is a fish, fish reproduction is checked for.
   * If movement occurs and the cell is a shark, then shark death is checked for.
   * @param gameBoard
   * @param row
   * @param col
   */
  private void moveToOcean(GameBoard gameBoard, int row, int col) {
    List<List<Integer>> neighboringOcean = getGameBoard()
        .getNeighboringPositionsOfCellState(WaTorCell.OCEAN, row, col);
    if (!neighboringOcean.isEmpty()) {
      List<Integer> oceanCoordinates = getDestinationCoordinates(neighboringOcean);
      int fishRow = oceanCoordinates.get(0);
      int fishCol = oceanCoordinates.get(1);
      gameBoard.swapCells(row, col, fishRow, fishCol);
      WaTorCell movedCell = (WaTorCell) (gameBoard.getCell(fishRow, fishCol));
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

  /**
   * Checks if the shark should die and if so, replaces the shark with ocean.
   * @param gameBoard
   * @param oceanCoordinates
   * @param movedCell
   */
  private void checkSharkDeath(GameBoard gameBoard, List<Integer> oceanCoordinates,
      WaTorCell movedCell) {
    int fishRow = oceanCoordinates.get(0);
    int fishCol = oceanCoordinates.get(1);
    if (movedCell.isShark() && movedCell.getEnergyPoints() <= DEAD_ENERGY) {
      gameBoard.copyCell(fishRow, fishCol, new WaTorCell(WaTorCell.OCEAN,
          new Neighborhood(fishRow, fishCol, getGameBoard(),
              getNeighborPolicy(), getEdgePolicy())));
    }
  }

  /**
   * Checks if fish should reproduce. If so, then a baby fish is created in the fish's former location
   * @param gameBoard
   * @param row
   * @param col
   * @param movedCell
   */
  private void checkFishReproduction(GameBoard gameBoard, int row, int col, WaTorCell movedCell) {
    if (movedCell.isFish() && movedCell.getSurvivalTime() >= REPRODUCTION_TIME) {
      gameBoard.copyCell(row, col, new WaTorCell(WaTorCell.FISH,
          new Neighborhood(row, col, getGameBoard(), getNeighborPolicy(), getEdgePolicy())));
      movedCell.resetSurvivalTime();
    }
  }

  /**
   * Shark moves to adjacent fish square.
   * If no such square exists, the shark does not move.
   * If the shark moves to an adjacent fish square but does not actually eat the fish (i.e the fish
   * escapes because it moves first), its energy decreases.
   * If it successfully eats a fish, its energy does not decrease.
   * @param gameBoard
   * @param row
   * @param col
   */
  private void eatFish(GameBoard gameBoard, int row, int col) {
    List<List<Integer>> neighboringFish = getGameBoard()
        .getNeighboringPositionsOfCellState(WaTorCell.FISH, row, col);
    if (!neighboringFish.isEmpty()) {
      List<Integer> fishCoordinates = getDestinationCoordinates(neighboringFish);
      int fishRow = fishCoordinates.get(0);
      int fishCol = fishCoordinates.get(1);
      gameBoard.swapCells(row, col, fishRow, fishCol);
      getGameBoard().copyCell(fishRow, fishCol, new WaTorCell(WaTorCell.OCEAN,
          new Neighborhood(fishRow, fishCol, getGameBoard(), getNeighborPolicy(),
              getEdgePolicy())));
      WaTorCell movedShark = (WaTorCell) (gameBoard.getCell(fishRow, fishCol));
      if (row > fishRow || col > fishCol) {
        movedShark.decrementEnergy();
      }
      movedShark.incrementSurvivalTime();
    }
  }

  /**
   * Moves to any adjacent fish square.
   * If no fish square exists, it moves to the nearest ocean.
   * If no ocean square exists, it does not move.
   * @param gameBoard
   * @param row
   * @param col
   */
  private void sharkMovement(GameBoard gameBoard, int row, int col) {
    List<List<Integer>> neighboringFish = getGameBoard()
        .getNeighboringPositionsOfCellState(WaTorCell.FISH, row, col);
    if (!neighboringFish.isEmpty()) {
      eatFish(gameBoard, row, col);
    } else {
      moveToOcean(gameBoard, row, col);
    }
  }

  /**
   * Sets the seed of the random variable to the given seed. Used for testing to
   * obtain consistent results
   * @param seed
   */
  public void setSeed(long seed) {
    rand.setSeed(seed);
  }
}
