package cellsociety.model.cells;

import cellsociety.model.cells.PercolationCell.PercolationState;

public class SchellingCell extends Cell{

  public enum SchellingCellState implements CellState {
    AGENT_X("0"),
    AGENT_O("1");


    public String getState() {
      return state;
    }

    private String state;

    SchellingCellState(String inputState){
      this.state = inputState;
    }

    public static SchellingCellState fromValue(String state){
      for (SchellingCellState value :  SchellingCellState.values()) {
        if(state.equals(value.getState())){
          return value;
        }
      }
      return AGENT_X;
    }
  }

  public static final String AGENT_X = "0";
  public static final String AGENT_O = "1";

  public SchellingCell(String state) {
    super(state);
  }


}
