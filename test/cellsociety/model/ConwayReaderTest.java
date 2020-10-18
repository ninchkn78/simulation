package cellsociety.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ReaderTest {

  @Test
  void readFile() {
    Reader reader = new Reader();
    String[][] test = reader.readFile("board_config/conway1.csv");
    assertEquals(11, test.length);
    assertEquals(11, test[0].length);
    assertEquals("0", test[0][0]);
    assertEquals("1", test[0][1]);
  }
}