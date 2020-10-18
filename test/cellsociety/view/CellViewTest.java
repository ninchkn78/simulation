package cellsociety.view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Properties;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

class CellViewTest {

  @Test
  public void setColorTest() throws IOException {
    Properties prop = new Properties();
    prop.load(RectangleCellView.class.getClassLoader()
        .getResourceAsStream("ConwayGameOfLife.properties"));
    RectangleCellView testCell = new RectangleCellView(0, 0, "DEAD", prop);
    assertEquals(Color.BLUE, testCell.getFill());
    RectangleCellView testCell2 = new RectangleCellView(0, 0, "ALIVE", prop);
    assertEquals(Color.RED, testCell2.getFill());
    RectangleCellView testCell3 = new RectangleCellView(0, 0, "FAKESTATE", prop);
    assertEquals(Color.WHITE, testCell3.getFill());
  }

}