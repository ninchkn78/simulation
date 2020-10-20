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


class SimulationBoardTest extends DukeApplicationTest {

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
  }

  @Test
  void testColorChangesOnClick() {
    Rectangle cell1 = lookup("#cell1,0").query();
    clickOn("#cell1,0");
    Assertions.assertEquals(Color.DARKSLATEBLUE, cell1.getFill());
    Assertions.assertEquals(0, cell1.getY());
  }
}