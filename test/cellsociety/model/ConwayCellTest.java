package cellsociety.model;

import static org.junit.jupiter.api.Assertions.*;

import cellsociety.model.cells.ConwayCell;
import org.junit.jupiter.api.Test;
public class ConwayCellTest {

  @Test
  public void isAliveTest(){
    ConwayCell cell = new ConwayCell(ConwayCell.ALIVE);
    assertTrue(cell.isAlive());
    assertFalse(cell.isDead());
  }

  @Test
  public void isDeadTest(){
    ConwayCell cell = new ConwayCell(ConwayCell.DEAD);
    assertTrue(cell.isDead());
    assertFalse(cell.isAlive());
  }

  @Test
  public void toStringTest(){
    ConwayCell cell = new ConwayCell(ConwayCell.DEAD);
    assertEquals(ConwayCell.DEAD, cell.toString());
  }

}
