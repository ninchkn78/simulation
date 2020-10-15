package cellsociety.view;

import static org.junit.jupiter.api.Assertions.*;

import cellsociety.controller.Controller;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class StateColorPickerTest extends DukeApplicationTest {
  // create an instance of our game to be able to call in tests (like step())
  private final Display conwayDisplay = new Display();
  // keep created scene to allow mouse and keyboard events
  private Scene myScene;
  // keep any useful elements whose values you want to test directly in multiple tests

  @Override
  public void start(Stage stage) {
    // create game's scene with all shapes in their initial positions and show it
    myScene = conwayDisplay.setupScene();
    stage.setScene(myScene);
    stage.show();
    javafxRun(() -> conwayDisplay.setController(new Controller("ConwayGameOfLife.properties")));
    // find individual items within game by ID (must have been set in your code using setID())
  }

  @Test
  void testColorChanges(){
    // TODO: 2020-10-13 ask how to test this
    ColorPicker colorPicker = lookup("#0color").query();
    Rectangle cell = lookup("#cell1,0").query();
    Assertions.assertEquals(Color.BLUE, cell.getFill());
    javafxRun(() -> colorPicker.setValue(Color.PURPLE));
    javafxRun(() -> conwayDisplay.step(conwayDisplay.getAnimationSpeed()));
    Assertions.assertEquals(Color.PURPLE, cell.getFill());
  }
}