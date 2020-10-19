package cellsociety.model.gametests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cellsociety.model.games.WaTorWorld;
import cellsociety.model.games.Simulation;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class WaTorWorldTest {

  @Test
  public void wator0ConfigTest(){
    Simulation wator = new WaTorWorld("board_config/wator0.csv", "WaTorCell", "complete", true);
    wator.nextGen();
    String[][] nextStates = wator.getGameBoard().getGameBoardStates();
    String[][] correctNextStates =
        {{"0","0","0","0","0","0","0","0","0","0","0"},
            {"0","2","0","0","0","0","0","0","0","0","0"},
            {"0","1","0","0","0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0","0","0","0","0"}};
    assertEquals(Arrays.deepToString(nextStates), Arrays.deepToString(correctNextStates));
  }

  @Test
  public void wator1ConfigTest(){
    Simulation wator = new WaTorWorld("board_config/wator1.csv", "WaTorCell", "complete",true);
    for (int i = 0; i < 15; i++){
      wator.nextGen();
    }
    String[][] nextStates = wator.getGameBoard().getGameBoardStates();
    String[][] correctNextStates =
        {{"0","0","0","0","0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0","0","0","0","0"}};
    assertEquals(Arrays.deepToString(nextStates), Arrays.deepToString(correctNextStates));
  }

  @Test
  public void wator2ConfigTest(){
    Simulation wator = new WaTorWorld("board_config/wator2.csv", "WaTorCell", "complete",true);
    for (int i = 0; i < 13; i++){
      wator.nextGen();
    }
    String[][] nextStates = wator.getGameBoard().getGameBoardStates();
    String[][] correctNextStates =
        {{"0","0","0","0","0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0","0","0","0","0"}};
    assertEquals(Arrays.deepToString(nextStates), Arrays.deepToString(correctNextStates));
  }

  @Test
  public void wator3ConfigTest(){
    Simulation wator = new WaTorWorld("board_config/wator3.csv", "WaTorCell", "complete", true);
    for (int i = 0; i < 50; i++){
      wator.nextGen();
    }
    String[][] nextStates = wator.getGameBoard().getGameBoardStates();
    String[][] correctNextStates =
        {{"1","1","1","1","1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1","1","1","1","1"}};
    assertEquals(Arrays.deepToString(nextStates), Arrays.deepToString(correctNextStates));
  }



}
