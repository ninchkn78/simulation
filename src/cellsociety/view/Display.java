package cellsociety.view;


import cellsociety.controller.Controller;
import java.util.Arrays;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;


public class Display extends Application {
 //THisshit should be in css
  public static final String TITLE = "Simulation";
  public static final int WIDTH = 800;
  public static final int HEIGHT = 600;
  public static final int FRAMES_PER_SECOND = 60;
  public static final double SECOND_DELAY = 100 / FRAMES_PER_SECOND;
  public static final Paint BACKGROUND = Color.AZURE;
  public static final int GAME_WIDTH = 13;
  public static final int GAME_HEIGHT = 11;

  private static final String CSS_STYLE_SHEET = "default.css";

  private final Group myRoot = new Group();
  //private final ConwayGameOfLife game = new ConwayGameOfLife(GAME_WIDTH, GAME_HEIGHT);

  private final ButtonSetup myButtonSetup = new ButtonSetup(this);
  private Stage myStage;
  private Timeline animation;

  // TODO: 2020-10-11 controller should make a prop object from properties then pass it around
  private Controller myController = new Controller("ConwayGameOfLife.properties");
  private final ConwaySimulationBoard myBoard = new ConwaySimulationBoard(myRoot, myController.getGameBoard(),"ConwayGameOfLife.properties");


  public Display(){
  }

  /**
   * Start the program.
   */
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) {
    // attach scene to the stage and display it
    myStage = stage;
    Scene myScene = setupScene();
    stage.setScene(myScene);
    stage.setTitle(TITLE); //will also come from properties
    stage.show();
    // attach "game loop" to timeline to play it (basically just calling step() method repeatedly forever)
    //startStepMethod();
  }

  public void startStepMethod() {
    KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY));
    animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames().add(frame);
    animation.play();
  }

  // TODO: 2020-10-04 some way to set up the scene based on a level file for testing different levels?
  Scene setupScene() {
    Scene scene = new Scene(myRoot, WIDTH, HEIGHT, BACKGROUND);
    // respond to input
    //scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
    //GameBoard myGameBoard = new GameBoard();
    scene.getStylesheets().add(CSS_STYLE_SHEET);
    myButtonSetup.addButtons(myRoot);
    myButtonSetup.checkRunButton();
    //nextGen(tempState);

    return scene;
  }

  // TODO: 2020-10-04 this 100% needs to change, but just doing this for now to be able to update?
  void nextGen() {
    myBoard.updateMyGrid(myController.getGameBoard(), "ConwayGameOfLife.properties");
  }

  // TODO: 2020-10-04 this 100% needs to change, but just doing this for now to be able to update?
  void step(double elapsedTime) {
    myController.updateView();
    nextGen();
  }



  public Window getStage() {
    return myStage;
  }

  public void pauseGame(){
    animation.pause();
  }


  public Controller getController() {
      return myController;
  }
}




