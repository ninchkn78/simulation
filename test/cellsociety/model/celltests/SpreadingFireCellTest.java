package cellsociety.model.celltests;

import static org.junit.jupiter.api.Assertions.*;

import cellsociety.model.cells.SpreadingFireCell;
import org.junit.jupiter.api.Test;

public class SpreadingFireCellTest {

  @Test
  public void isBurningTest(){
    SpreadingFireCell cell = new SpreadingFireCell(SpreadingFireCell.BURNING);
    assertTrue(cell.isBurning());
    assertFalse(cell.isEmpty());
    assertFalse(cell.isTree());
  }

  @Test
  public void isEmptyTest(){
    SpreadingFireCell cell = new SpreadingFireCell(SpreadingFireCell.EMPTY);
    assertTrue(cell.isEmpty());
    assertFalse(cell.isBurning());
    assertFalse(cell.isTree());
  }
  @Test
  public void isTreeTest(){
    SpreadingFireCell cell = new SpreadingFireCell(SpreadingFireCell.TREE);
    assertTrue(cell.isTree());
    assertFalse(cell.isEmpty());
    assertFalse(cell.isBurning());
  }

}
