package cellsociety.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;


public class ConwayDisplay extends Application {

  public static final String TITLE = "Simulation";
  public static final int WIDTH = 800;
  public static final int HEIGHT = 600;
  public static final int FRAMES_PER_SECOND = 60;
  public static final double SECOND_DELAY = 4.0 / FRAMES_PER_SECOND;
  public static final Paint BACKGROUND = Color.AZURE;

  private Group myRoot = new Group();
  private ConwaySimulationBoard myBoard = new ConwaySimulationBoard(myRoot);
  private ButtonSetup myButtonSetup = new ButtonSetup();
  /**
   * Start the program.
   */
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) {
    // attach scene to the stage and display it
    Scene myScene = setupScene();
    stage.setScene(myScene);
    stage.setTitle(TITLE);
    stage.show();
    // attach "game loop" to timeline to play it (basically just calling step() method repeatedly forever)
    KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY));
    Timeline animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames().add(frame);
    animation.play();
  }

  // TODO: 2020-10-04 some way to set up the scene based on a level file for testing different levels?
  Scene setupScene() {
    Scene scene = new Scene(myRoot, WIDTH, HEIGHT, BACKGROUND);
    // respond to input
    //scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
    myButtonSetup.addButtons(myRoot);
    return scene;
  }

  // TODO: 2020-10-04 this 100% needs to change, but just doing this for now to be able to update?
  void nextGen(String[][] states)  {
    myBoard.updateMyGrid(states);
  }

  // TODO: 2020-10-04 this 100% needs to change, but just doing this for now to be able to update?
  void step(double elapsedTime){
    String[][] tempState = {{"1","0","1","1","0","1", "1", "0", "1", "0", "1", "1", "1"},
            {"1","0","0","1","0","1", "1", "0", "1", "0", "1", "1", "0"},
            {"1","0","1","0","0","1", "1", "0", "1", "0", "1", "1", "1"},
            {"1","1","1","1","0","1", "1", "0", "1", "0", "1", "0", "1"},
            {"1","0","1","1","0","1", "1", "0", "0", "0", "1", "1", "1"},
            {"1","0","0","1","0","1", "0", "0", "1", "0", "1", "1", "0"},
            {"0","0","1","1","0","0", "1", "1", "1", "1", "0", "1", "0"},
            {"1","0","1","1","0","1", "1", "0", "1", "0", "1", "1", "1"},
            {"1","0","1","1","0","1", "1", "0", "1", "1", "0", "1", "1"},
            {"1","0","1","1","0","1", "1", "0", "1", "0", "1", "1", "1"},
            {"1","0","1","1","0","1", "1", "0", "1", "1", "1", "1", "1"}};
            nextGen(tempState);
            myButtonSetup.checkButtonStatus();

  }
}



