package cellsociety.model.cells;

import cellsociety.model.cells.SpreadingFireCell.SpreadingFireState;

public class WaTorCell extends Cell{

  public enum WaTorState implements CellState {
    OCEAN("0"),
    FISH("1"),
    SHARK("2");


    public String getState() {
      return state;
    }

    private String state;

    WaTorState(String inputState){
      this.state = inputState;
    }

    public static WaTorState fromValue(String state){
      for (WaTorState value :  WaTorState.values()) {
        if(state.equals(value.getState())){
          return value;
        }
      }
      return OCEAN;
    }
  }

  public static final String OCEAN = "0";
  public static final String FISH = "1";
  public static final String SHARK = "2";
  public static final int STARTING_ENERGY = 10;
  public static final int STARTING_SURVIVAL_TIME = 0;

  public int energyPoints;
  public int survivalTime;

  public WaTorCell(String state, int energy, int survivalTime) {
    super(state);
    this.energyPoints = energy;
    this.survivalTime = survivalTime;
  }

  public WaTorCell(String state){
    this(state, STARTING_ENERGY, STARTING_SURVIVAL_TIME);
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
    this.survivalTime+=1;
  }

  public void resetSurvivalTime(){
    this.survivalTime = STARTING_SURVIVAL_TIME;
  }

  public boolean isShark(){
    return getState().equals(SHARK);
  }

  public boolean isFish(){
    return getState().equals(FISH);
  }
}
