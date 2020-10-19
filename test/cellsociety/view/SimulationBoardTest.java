//package cellsociety.view;
//
//import cellsociety.controller.Controller;
//import javafx.scene.Scene;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Rectangle;
//import javafx.stage.Stage;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import util.DukeApplicationTest;
//
//
//class SimulationBoardTest extends DukeApplicationTest {
//
//  // create an instance of our game to be able to call in tests (like step())
//  private final Display conwayDisplay = new Display();
//  // keep created scene to allow mouse and keyboard events
//  private Scene myScene;
//  // keep any useful elements whose values you want to test directly in multiple tests
//
//  @Override
//  public void start(Stage stage) {
//    myScene = conwayDisplay.setupScene();
//    stage.setScene(myScene);
//    stage.show();
//    javafxRun(() -> conwayDisplay.setController(new Controller("TestConway.properties")));
//  }
//
//  @Test
//  void testColorChangesOnClick() {
//    Rectangle cell1 = lookup("#cell1,0").query();
//    clickOn(cell1);
//
//    Assertions.assertEquals(Color.BLUE, cell1.getFill());
//    Assertions.assertEquals(0, cell1.getY());
//  }
//
//}