package cellsociety.view;

import cellsociety.controller.Controller;
import java.util.Properties;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class ConwayBoardTest extends DukeApplicationTest {

  // create an instance of our game to be able to call in tests (like step())
  private final Display conwayDisplay = new Display();
  // keep created scene to allow mouse and keyboard events
  private Scene myScene;
  // keep any useful elements whose values you want to test directly in multiple tests

  @Override
  public void start(Stage stage) {
    Properties english = conwayDisplay.createPropertiesObject("resources/Text_Properties_Files/English.properties");
    conwayDisplay.generateSplashScreen(english,stage);
    conwayDisplay.chooseSimulation("ConwayGameOfLife",english);
    Controller controller = new Controller("TestConway.properties");
    conwayDisplay.setNewSimulation(controller);
    stage.show();
    //conwayDisplay.setController(controller);

    // find individual items within game by ID (must have been set in your code using setID())
  }

  @Test
  void testCellsAreInitialized() {
    Rectangle cell1 = lookup("#cell1,0").query();
    Rectangle cell2 = lookup("#cell0,1").query();
    Assertions.assertEquals(Color.BLUE, cell1.getFill());
    Assertions.assertEquals(Color.RED, cell2.getFill());
    Assertions.assertEquals(0, cell1.getX());
    Assertions.assertEquals(0, cell1.getY());
  }

  @Test
  void testNextGen() {
    javafxRun(() -> conwayDisplay.nextGen());
    Rectangle cell1 = lookup("#cell1,0").query();
    Rectangle cell2 = lookup("#cell0,1").query();
    Assertions.assertEquals(Color.RED, cell1.getFill());
    Assertions.assertEquals(Color.BLUE, cell2.getFill());
    Assertions.assertEquals(0, cell1.getX());
    Assertions.assertEquals(0, cell1.getY());
  }

}