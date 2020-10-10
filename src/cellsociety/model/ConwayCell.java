package cellsociety.model;

public class ConwayCell extends Cell {

  public enum ConwayState implements CellState{
    ALIVE("1"),
    DEAD("0");

    public String getState() {
      return state;
    }

    private String state;

    ConwayState(String inputState){
      this.state = inputState;
    }

    public static ConwayState fromValue(String state){
      for (ConwayState value :  ConwayState.values()) {
        if(state.equals(value.getState())){
          return value;
        }
      }
      return DEAD;
    }
  }
  public static final String DEAD = "0";
  public static final String ALIVE = "1";



  public ConwayCell(String state) {
    super(state);
  }

  public ConwayCell() {
    super(DEAD);
  }

  public void toggleState() { //TODO: make this cleaner
    if (this.getState() == DEAD) {
      this.setState(ALIVE);
    } else {
      this.setState(DEAD);
    }
  }

  public boolean isAlive() {
    return this.getState().equals(ALIVE);
  }

  public boolean isDead() {
    return this.getState().equals(DEAD);
  }

  public String toString() {
    return this.getState();
  }


}
