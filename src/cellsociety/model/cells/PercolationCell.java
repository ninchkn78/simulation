package cellsociety.model.cells;

import cellsociety.model.Neighborhood;

/**
 * Cell class for Percolation
 *
 * @author Franklin Wu
 */

public class PercolationCell extends Cell {

  public static final String CLOSED = "0";
  public static final String OPEN = "1";
  public static final String FULL = "2";

  /**
   * Creates a Percolation Cell
   * @param state
   * @param neighbors
   */
  public PercolationCell(String state, Neighborhood neighbors) {
    super(state, neighbors);
  }

  /**
   * Returns true if the cell is open
   * @return
   */
  public boolean isOpen() {
    return this.getState().equals(OPEN);
  }

  /**
   * Returns true if the cell is closed
   * @return
   */
  public boolean isClosed() {
    return this.getState().equals(CLOSED);
  }

  /**
   * Returns true if the cell is full
   * @return
   */
  public boolean isFull() {
    return this.getState().equals(FULL);
  }
//
//  public enum PercolationState implements CellState {
//    CLOSED("0"),
//    OPEN("1"),
//    FULL("2");
//
//
//    private final String state;
//
//    PercolationState(String inputState) {
//      this.state = inputState;
//    }
//
//    public static PercolationState fromValue(String state) {
//      for (PercolationState value : PercolationState.values()) {
//        if (state.equals(value.getState())) {
//          return value;
//        }
//      }
//      return CLOSED;
//    }
//
//    public String getState() {
//      return state;
//    }
//  }


}
