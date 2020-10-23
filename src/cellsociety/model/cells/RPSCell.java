package cellsociety.model.cells;

import cellsociety.model.Neighborhood;

/**
 * Cell class for Rock, Paper, Scissors simulation game
 *
 * @author Franklin Wu
 */

public class RPSCell extends Cell {

  public static final String ROCK = "0";
  public static final String PAPER = "1";
  public static final String SCISSORS = "2";

  /**
   * Creates a RPS Cell
   * @param state
   * @param neighbors
   */
  public RPSCell(String state, Neighborhood neighbors) {
    super(state, neighbors);
  }

  /**
   * Returns true if the cell is rock
   * @return
   */
  public boolean isRock() {
    return getState().equals(ROCK);
  }

  /**
   * Returns true if the cell is paper
   * @return
   */
  public boolean isPaper() {
    return getState().equals(PAPER);
  }

  /**
   * Returns true if the cell is scissors
   * @return
   */
  public boolean isScissors() {
    return getState().equals(SCISSORS);
  }

//  public enum RPSState implements CellState {
//    ROCK("0"),
//    PAPER("1"),
//    SCISSORS("2");
//
//
//    private final String state;
//
//    RPSState(String inputState) {
//      this.state = inputState;
//    }
//
//    public static RPSState fromValue(String state) {
//      for (RPSState value : RPSState.values()) {
//        if (state.equals(value.getState())) {
//          return value;
//        }
//      }
//      return ROCK;
//    }
//
//    public String getState() {
//      return state;
//    }
//  }


}
