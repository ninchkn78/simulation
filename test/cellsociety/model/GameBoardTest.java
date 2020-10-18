package cellsociety.model;

import static org.junit.jupiter.api.Assertions.*;

import cellsociety.model.cells.ConwayCell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameBoardTest {

  private GameBoard board;

  @BeforeEach
  void init(){
    board = new GameBoard(5,5);
  }


  @Test
  public void inBoundsTest(){
    assertTrue(board.inBounds(4,4));
    assertTrue(board.inBounds(1,3));
    assertTrue(board.inBounds(0,0));
    assertFalse(board.inBounds(-1,4));
    assertFalse(board.inBounds(5,4));
    assertFalse(board.inBounds(0,10));
  }

  

}