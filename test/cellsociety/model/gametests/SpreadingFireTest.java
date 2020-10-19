package cellsociety.model.gametests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cellsociety.model.games.Simulation;
import cellsociety.model.games.SpreadingFire;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class SpreadingFireTest {

  @Test
  public void SpreadingFire0ConfigTest(){
    Simulation spreadingFire = new SpreadingFire("board_config/spreadingfire0.csv", "SpreadingFireCell",  true);
    spreadingFire.nextGen();
    String[][] nextStates = spreadingFire.getGameBoard().getGameBoardStates();
    String[][] correctNextStates =
        {{"0","0","0","0","0","0","0","0","0","0","0"},
            {"0","1","1","1","1","1","1","1","1","1","0"},
            {"0","1","1","1","1","1","1","1","1","1","0"},
            {"0","1","1","1","1","1","1","1","1","1","0"},
            {"0","1","1","1","1","2","1","1","1","1","0"},
            {"0","1","1","1","1","0","2","1","1","1","0"},
            {"0","1","1","1","1","2","1","1","1","1","0"},
            {"0","1","1","1","1","1","1","1","1","1","0"},
            {"0","1","1","1","1","1","1","1","1","1","0"},
            {"0","1","1","1","1","1","1","1","1","1","0"},
            {"0","0","0","0","0","0","0","0","0","0","0"}
        };
    assertEquals(Arrays.deepToString(nextStates), Arrays.deepToString(correctNextStates));
  }

  @Test
  public void SpreadingFire1ConfigTest(){
    Simulation spreadingFire = new SpreadingFire("board_config/spreadingfire1.csv", "SpreadingFireCell",true);
    spreadingFire.nextGen();
    String[][] nextStates = spreadingFire.getGameBoard().getGameBoardStates();
    String[][] correctNextStates =
        {{"0","0","0","0","0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0","0","0","0","0"},
            {"0","0","0","0","0","2","0","0","0","0","0"},
            {"0","0","0","0","1","0","2","0","0","0","0"},
            {"0","0","0","0","0","2","0","0","0","0","0"},
            {"0","0","0","0","0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0","0","0","0","0"}
        };
    assertEquals(Arrays.deepToString(nextStates), Arrays.deepToString(correctNextStates));
  }

  @Test
  public void SpreadingFire2ConfigTest(){
    Simulation spreadingFire = new SpreadingFire("board_config/spreadingfire2.csv", "SpreadingFireCell",true);
    spreadingFire.nextGen();
    String[][] nextStates = spreadingFire.getGameBoard().getGameBoardStates();
    String[][] correctNextStates =
        {{"0","0","0","0","0","0","0","0","0","0","0"},
            {"0","1","1","1","1","1","1","1","1","1","0"},
            {"0","1","1","1","1","1","1","2","1","1","0"},
            {"0","1","1","1","1","1","1","0","2","1","0"},
            {"0","1","1","2","1","2","1","1","1","1","0"},
            {"0","1","1","0","2","0","2","1","1","1","0"},
            {"0","1","1","2","1","1","1","1","1","1","0"},
            {"0","1","1","1","1","1","1","1","1","1","0"},
            {"0","1","1","1","1","1","1","1","1","1","0"},
            {"0","1","1","1","1","1","1","1","0","1","0"},
            {"0","0","0","0","0","0","0","0","0","0","0"}
        };
    assertEquals(Arrays.deepToString(nextStates), Arrays.deepToString(correctNextStates));
  }

}
