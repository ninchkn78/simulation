package cellsociety.view;

import static org.junit.jupiter.api.Assertions.*;

import cellsociety.model.ConwayCell;
import java.io.IOException;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

class CellViewTest {

  @Test
  public void setColorTest() {
    CellView testCell = new CellView(0,0, "DEAD", "ConwayGameOfLife.properties");
   assertEquals(Color.BLUE, testCell.getFill());
    CellView testCell2 = new CellView(0,0, "ALIVE", "ConwayGameOfLife.properties");
    assertEquals(Color.RED, testCell2.getFill());
    CellView testCell3 = new CellView(0,0, "FAKESTATE", "ConwayGameOfLife.properties");
    assertEquals(Color.BLACK, testCell3.getFill());
  }

}