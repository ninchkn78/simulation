package cellsociety.model;

import static org.junit.jupiter.api.Assertions.*;

import cellsociety.model.cells.ConwayCell;
import cellsociety.model.games.ConwayGameOfLife;
import cellsociety.model.games.Simulation;
import org.junit.jupiter.api.Test;

public class ConwayGameOfLifeTest {

  @Test
  public void initialConfig1Test(){
    Simulation conway = new ConwayGameOfLife("");
  }



}
