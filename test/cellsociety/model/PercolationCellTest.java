package cellsociety.model;

import static org.junit.jupiter.api.Assertions.*;

import cellsociety.model.cells.ConwayCell;
import cellsociety.model.cells.PercolationCell;
import org.junit.jupiter.api.Test;

public class PercolationCellTest {

  @Test
  public void isFullTest(){
    PercolationCell cell = new PercolationCell(PercolationCell.FULL);
    assertTrue(cell.isFull());
    assertFalse(cell.isOpen());
    assertFalse(cell.isClosed());
  }

  @Test
  public void isOpenTest(){
    PercolationCell cell = new PercolationCell(PercolationCell.OPEN);
    assertFalse(cell.isFull());
    assertTrue(cell.isOpen());
    assertFalse(cell.isClosed());
  }

  @Test
  public void isClosedTest(){
    PercolationCell cell = new PercolationCell(PercolationCell.CLOSED);
    assertFalse(cell.isFull());
    assertFalse(cell.isOpen());
    assertTrue(cell.isClosed());
  }

  @Test
  public void toStringTest(){
    ConwayCell cell = new ConwayCell(ConwayCell.DEAD);
    assertEquals(ConwayCell.DEAD, cell.toString());
  }

}
