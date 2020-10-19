package cellsociety.model.gametests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cellsociety.model.games.SchellingSegregation;
import cellsociety.model.games.Simulation;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class SchellingSegregationTest {

  @Test
  public void segregation0ConfigTest(){
    Simulation segregation = new SchellingSegregation("board_config/segregation0.csv", "SchellingCell", "complete", true);
    segregation.nextGen();
    String[][] nextStates = segregation.getGameBoard().getGameBoardStates();
    String[][] correctNextStates =
        {{"0","0","2","2","1","0","1","0","2","1","0"},
            {"2","2","2","0","1","2","1","0","2","2","0"},
            {"2","1","0","0","1","0","2","0","0","0","0"},
            {"1","0","0","1","0","1","0","0","2","2","2"},
            {"0","0","2","2","2","0","1","0","0","0","0"},
            {"2","0","0","0","0","0","0","0","0","0","2"},
            {"2","2","0","1","1","1","0","0","0","0","0"},
            {"0","0","1","0","0","0","0","1","1","1","0"},
            {"0","1","1","1","0","2","0","2","0","0","2"},
            {"0","1","1","1","2","2","2","2","2","1","1"},
            {"0","0","1","2","0","0","0","0","0","1","2"}
        };
    assertEquals(Arrays.deepToString(nextStates), Arrays.deepToString(correctNextStates));
  }

  @Test
  public void segregation1ConfigTest(){
    Simulation segregation = new SchellingSegregation("board_config/segregation1.csv", "SchellingCell", "complete", true);
    segregation.nextGen();
    String[][] nextStates = segregation.getGameBoard().getGameBoardStates();
    String[][] correctNextStates =
        {{"2","0","0","0","0","0","0","0","0","0","0"},
            {"0","1","1","1","1","1","1","1","1","1","0"},
            {"0","1","1","1","1","1","1","1","1","1","0"},
            {"0","1","1","1","1","1","1","1","1","1","0"},
            {"0","1","1","1","1","1","1","1","1","1","0"},
            {"0","1","1","1","1","0","1","1","1","1","0"},
            {"0","1","1","1","1","1","1","1","1","1","0"},
            {"0","1","1","1","1","1","1","1","1","1","0"},
            {"0","1","1","1","1","1","1","1","1","1","0"},
            {"0","1","1","1","1","1","1","1","1","1","0"},
            {"0","0","0","0","0","0","0","0","0","0","0"}
        };
    assertEquals(Arrays.deepToString(nextStates), Arrays.deepToString(correctNextStates));
  }

  @Test
  public void segregation2ConfigTest(){
    Simulation segregation = new SchellingSegregation("board_config/segregation2.csv","SchellingCell", "complete", true);
    segregation.nextGen();
    String[][] nextStates = segregation.getGameBoard().getGameBoardStates();
    String[][] correctNextStates =
        {{"0","0","0","0","0","1","1","0","0","2","2"},
            {"1","0","0","0","0","0","1","0","2","0","0"},
            {"0","1","1","0","2","2","0","2","2","2","1"},
            {"2","2","1","0","0","0","0","1","2","2","1"},
            {"2","2","1","1","1","0","2","1","0","0","0"},
            {"2","2","1","1","1","1","2","1","1","2","0"},
            {"2","2","0","1","2","2","0","2","0","2","2"},
            {"2","0","1","0","2","0","1","1","0","2","1"},
            {"0","1","0","2","1","2","0","1","2","0","1"},
            {"2","2","1","1","1","1","0","0","2","1","1"},
            {"0","1","0","1","1","1","2","2","2","2","2"}};

    assertEquals(Arrays.deepToString(nextStates), Arrays.deepToString(correctNextStates));
  }

}
