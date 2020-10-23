package cellsociety.model.cells;

import cellsociety.model.Neighborhood;

/**
 * Cell class for WatorWorld simulation
 *
 * @author Franklin Wu
 */

public class WaTorCell extends Cell {


  public static final String OCEAN = "0";
  public static final String FISH = "1";
  public static final String SHARK = "2";
  public static final int STARTING_ENERGY = 10;
  public static final int STARTING_SURVIVAL_TIME = 0;
  public int energyPoints;
  public int survivalTime;

  /**
   * Creates a WaTor cell based on the starting state, the neighbors, the energy, and the survival time
   * @param state
   * @param neighbors
   */
  public WaTorCell(String state, Neighborhood neighbors, int energy, int survivalTime) {
    super(state, neighbors);
    this.energyPoints = energy;
    this.survivalTime = survivalTime;
  }

  /**
   * Creates a WaTor cell based on the starting state and the neighbors
   * @param state
   * @param neighbors
   */
  public WaTorCell(String state, Neighborhood neighbors) {
    this(state, neighbors, STARTING_ENERGY, STARTING_SURVIVAL_TIME);
  }

  public int getEnergyPoints() {
    return energyPoints;
  }

  public int getSurvivalTime() {
    return survivalTime;
  }

  public void decrementEnergy() {
    this.energyPoints--;
  }

  public void incrementSurvivalTime() {
    this.survivalTime += 1;
  }

  public void resetSurvivalTime() {
    this.survivalTime = STARTING_SURVIVAL_TIME;
  }

  public boolean isShark() {
    return getState().equals(SHARK);
  }

  public boolean isFish() {
    return getState().equals(FISH);
  }

//  public enum WaTorState implements CellState {
//    OCEAN("0"),
//    FISH("1"),
//    SHARK("2");
//
//
//    private final String state;
//
//    WaTorState(String inputState) {
//      this.state = inputState;
//    }
//
//    public static WaTorState fromValue(String state) {
//      for (WaTorState value : WaTorState.values()) {
//        if (state.equals(value.getState())) {
//          return value;
//        }
//      }
//      return OCEAN;
//    }
//
//    public String getState() {
//      return state;
//    }
//  }
}
