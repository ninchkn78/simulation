package cellsociety.view;

import cellsociety.controller.Controller;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class ButtonSetupTest extends DukeApplicationTest {

  private final Display myDisplay = new Display();
  Scene myScene;

  @Override
  public void start(Stage stage) {
    // create game's scene with all shapes in their initial positions and show i
    //javafxRun(() -> myDisplay.setController(new Controller("DefaultConwayGameOfLife.properties")));
    myDisplay.chooseSimulation("ConwayGameOfLife");
    //myScene = myDisplay.setupScene();
    //stage.setScene(myScene);
    //stage.show();
  }

  @Test
  public void testButtonGeneration() {

    lookup("#Load File").queryButton();
    lookup("#Play").queryButton();
    lookup("#Save File").queryButton();
    lookup("#Pause").queryButton();
    lookup("#Image").queryButton();
  }

  @Test
  public void testRunButton() {
    Button runButton = lookup("#runButton").queryButton();

    Rectangle cell1 = lookup("#cell1,0").query();
    Rectangle cell2 = lookup("#cell0,1").query();
    Assertions.assertEquals(Color.BLUE, cell1.getFill());
    Assertions.assertEquals(Color.RED, cell2.getFill());
    runButton.fire();
    javafxRun(() -> myDisplay.step(myDisplay.getAnimationSpeed()));
    Assertions.assertEquals(Color.RED, cell1.getFill());
    Assertions.assertEquals(Color.BLUE, cell2.getFill());

  }

  @Test

  public void testPauseButton() {

    Button pause = lookup("#pauseButton").queryButton();

    Rectangle cell1 = lookup("#cell1,0").query();
    Rectangle cell2 = lookup("#cell0,1").query();
    Assertions.assertEquals(Color.BLUE, cell1.getFill());
    Assertions.assertEquals(Color.RED, cell2.getFill());

    javafxRun(() -> myDisplay.nextGen());
    Assertions.assertEquals(Color.RED, cell1.getFill());
    Assertions.assertEquals(Color.BLUE, cell2.getFill());

    pause.fire();

    javafxRun(() -> myDisplay.step(myDisplay.getAnimationSpeed()));
    Assertions.assertEquals(Color.RED, cell1.getFill());
    Assertions.assertEquals(Color.BLUE, cell2.getFill());

  }

  @Test
  public void testStepButton() {

    Button stepButton = lookup("#Step Once").queryButton();

    Rectangle cell1 = lookup("#cell1,0").query();
    Rectangle cell2 = lookup("#cell0,1").query();
    Assertions.assertEquals(Color.BLUE, cell1.getFill());
    Assertions.assertEquals(Color.RED, cell2.getFill());

    stepButton.fire();
    Assertions.assertEquals(Color.RED, cell1.getFill());
    Assertions.assertEquals(Color.BLUE, cell2.getFill());

  }


}
