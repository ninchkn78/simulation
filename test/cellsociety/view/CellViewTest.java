package cellsociety.view;

import static org.junit.jupiter.api.Assertions.*;

import cellsociety.model.cells.ConwayCell;
import java.io.IOException;
import java.util.Properties;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

class CellViewTest {

  @Test
  public void setColorTest() throws IOException {
    Properties prop = new Properties();
    prop.load(CellView.class.getClassLoader().getResourceAsStream("ConwayGameOfLife.properties"));
    CellView testCell = new CellView(0,0, "DEAD", prop);
    assertEquals(Color.BLUE, testCell.getFill());
    CellView testCell2 = new CellView(0,0, "ALIVE", prop);
    assertEquals(Color.RED, testCell2.getFill());
    CellView testCell3 = new CellView(0,0, "FAKESTATE", prop);
    assertEquals(Color.WHITE, testCell3.getFill());
  }

}