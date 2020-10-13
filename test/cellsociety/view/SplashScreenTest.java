package cellsociety.view;

import static org.junit.jupiter.api.Assertions.*;

import cellsociety.controller.Controller;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class SplashScreenTest extends DukeApplicationTest {
  // create an instance of our game to be able to call in tests (like step())
  private final Display myDisplay = new Display();
  // keep created scene to allow mouse and keyboard events
  private Scene myScene;
  // keep any useful elements whose values you want to test directly in multiple tests

  @Override
  public void start(Stage stage) {
    myDisplay.generateSplashScreen(stage);
  }

  @Test
  void checkConwayButton(){
    Button conwayButton = lookup("#ConwayGameOfLife").queryButton();
    javafxRun(() -> conwayButton.fire());
    assertEquals("DefaultConway", myDisplay.getController().getProperties().getProperty("Title"));
  }

  @Test
  void checkPercolationButton(){
    Button conwayButton = lookup("#Percolation").queryButton();
    javafxRun(() -> conwayButton.fire());
    assertEquals("DefaultPercolation", myDisplay.getController().getProperties().getProperty("Title"));
  }

  @Test
  void checkRPSButton(){
    Button conwayButton = lookup("#RPS").queryButton();
    javafxRun(() -> conwayButton.fire());
    assertEquals("DefaultRPS", myDisplay.getController().getProperties().getProperty("Title"));
  }


}