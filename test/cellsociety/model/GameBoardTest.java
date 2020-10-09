package cellsociety.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameBoardTest {

  private GameBoard board;

  @BeforeEach
  void init(){
    board = new GameBoard(5,5);
  }



  @Test
  public void clear(){
      for (int i = 0; i < board.getHeight(); i++){
        for (int j = 0; j < board.getWidth(); j++){
          board.setPiece(i,j,ConwayCell.ALIVE);
        }
      }
      board.clear();
    for (int i = 0; i < board.getHeight(); i++){
      for (int j = 0; j < board.getWidth(); j++){
        assertEquals(ConwayCell.DEAD, board.getCell(i,j).getState());
      }
    }
  }

  @Test
  public void isValidLocationTest(){
    assertTrue(board.isValidLocation(4,4));
    assertTrue(board.isValidLocation(1,3));
    assertTrue(board.isValidLocation(0,0));
    assertFalse(board.isValidLocation(-1,4));
    assertFalse(board.isValidLocation(5,4));
    assertFalse(board.isValidLocation(0,10));
  }

  

}