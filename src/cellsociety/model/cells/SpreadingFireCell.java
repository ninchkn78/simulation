package cellsociety.model.cells;

import cellsociety.model.cells.PercolationCell.PercolationState;

public class SpreadingFireCell extends Cell{

  public enum SpreadingFireState implements CellState {
    EMPTY("0"),
    TREE("1"),
    BURNING("2");


    public String getState() {
      return state;
    }

    private String state;

    SpreadingFireState(String inputState){
      this.state = inputState;
    }

    public static SpreadingFireState fromValue(String state){
      for (SpreadingFireState value :  SpreadingFireState.values()) {
        if(state.equals(value.getState())){
          return value;
        }
      }
      return EMPTY;
    }
  }

  public static final String EMPTY = "0";
  public static final String TREE = "1";
  public static final String BURNING = "2";

  public SpreadingFireCell(String state) {
    super(state);
  }


}
