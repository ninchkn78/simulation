package cellsociety.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cellsociety.model.cells.ConwayCell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameBoardTest {

  private GameBoard board;

  @BeforeEach
  void init() {
    board = new GameBoard(5, 5);
  }

  @Test
  public void clear() {
    for (int i = 0; i < board.getHeight(); i++) {
      for (int j = 0; j < board.getWidth(); j++) {
        board.setPiece(i, j, ConwayCell.ALIVE);
      }
    }
    board.clear();
    for (int i = 0; i < board.getHeight(); i++) {
      for (int j = 0; j < board.getWidth(); j++) {
        assertEquals(ConwayCell.DEAD, board.getCell(i, j).getState());
      }
    }
  }

  @Test
  public void inBoundsTest() {
    assertTrue(board.inBounds(4, 4));
    assertTrue(board.inBounds(1, 3));
    assertTrue(board.inBounds(0, 0));
    assertFalse(board.inBounds(-1, 4));
    assertFalse(board.inBounds(5, 4));
    assertFalse(board.inBounds(0, 10));
  }


}