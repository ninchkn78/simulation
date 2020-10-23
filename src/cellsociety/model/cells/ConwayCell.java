package cellsociety.model.cells;

import cellsociety.model.Neighborhood;

/**
 * Cell class for Conway's Game of Life
 *
 * @author Franklin Wu
 */

public class ConwayCell extends Cell {

  public static final String DEAD = "0";
  public static final String ALIVE = "1";

  /**
   * Creates a Conway Cell
   * @param state
   * @param neighbors
   */
  public ConwayCell(String state, Neighborhood neighbors) {
    super(state, neighbors);
  }

  /**
   * Returns true if the cell is alive. Otherwise, it returns false
   * @return
   */
  public boolean isAlive() {
    return this.getState().equals(ALIVE);
  }

  /**
   * Returns true if the cell is dead. Otherwise, it returns false
   * @return
   */
  public boolean isDead() {
    return this.getState().equals(DEAD);
  }

//  public enum ConwayState implements CellState {
//    ALIVE("1"),
//    DEAD("0");
//
//    private final String state;
//
//    ConwayState(String inputState) {
//      this.state = inputState;
//    }
//
//    public static ConwayState fromValue(String state) {
//      for (ConwayState value : ConwayState.values()) {
//        if (state.equals(value.getState())) {
//          return value;
//        }
//      }
//      return DEAD;
//    }
//
//    public String getState() {
//      return state;
//    }
//  }

}
