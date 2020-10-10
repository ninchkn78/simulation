package cellsociety.view;

import static org.junit.jupiter.api.Assertions.*;

import cellsociety.model.ConwayCell;
import java.io.IOException;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

class CellViewTest {

  @Test
  public void setColorTest() {
    CellView testCell = new CellView(0,0);
   testCell.setColor("DEAD", "ConwayGameOfLife.properties");
   assertEquals(Color.BLUE, testCell.getFill());
  }

}