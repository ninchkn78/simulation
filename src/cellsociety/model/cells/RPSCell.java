package cellsociety.model.cells;

import java.util.HashMap;
import java.util.Map;

public class RPSCell extends Cell{

  private String opponentState;

  public enum RPSState implements CellState {
    CLOSED("0"),
    OPEN("1"),
    FULL("2");


    public String getState() {
      return state;
    }

    private String state;

    RPSState(String inputState){
      this.state = inputState;
    }

    public static RPSState fromValue(String state){
      for (RPSState value :  RPSState.values()) {
        if(state.equals(value.getState())){
          return value;
        }
      }
      return CLOSED;
    }
  }

  public static final String ROCK = "0";
  public static final String PAPER = "1";
  public static final String SCISSORS = "2";

  public RPSCell(String state) {
    super(state);
   // opponentState = calculateOpponent();
  }

//  public String calculateOpponent(){
//    int state = Integer.parseInt(getState());
//    return Integer.toString((state + 1)%3);
//  }
//
//  public String getOpponentState(){
//    return opponentState;
//  }




}
