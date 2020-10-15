package cellsociety.model;

import static org.junit.jupiter.api.Assertions.*;

import cellsociety.model.cells.RPSCell;
import org.junit.jupiter.api.Test;

public class RPSCellTest {

  @Test
  public void isRockTest(){
    RPSCell cell = new RPSCell(RPSCell.ROCK);
    assertTrue(cell.isRock());
    assertFalse(cell.isPaper());
    assertFalse(cell.isScissors());
  }

  @Test
  public void isPaperTest(){
    RPSCell cell = new RPSCell(RPSCell.PAPER);
    assertTrue(cell.isPaper());
    assertFalse(cell.isScissors());
    assertFalse(cell.isRock());
  }
  @Test
  public void isScissorsTest(){
    RPSCell cell = new RPSCell(RPSCell.SCISSORS);
    assertTrue(cell.isScissors());
    assertFalse(cell.isRock());
    assertFalse(cell.isRock());
  }

}
