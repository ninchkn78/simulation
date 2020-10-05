package cellsociety.model;

public class ConwayCell extends Cell{

  private final String ALIVE = "0";
  private final String DEAD = "1";


  public ConwayCell(String state){
    super(state);
  }

  public void toggleState() { //TODO: make this cleaner
    if(this.getState() == DEAD) {
      this.setState(ALIVE);
    }
    else this.setState(DEAD);
  }






}
