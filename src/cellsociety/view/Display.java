package cellsociety.view;


import static java.lang.Thread.sleep;

import cellsociety.controller.Controller;
import java.io.FileOutputStream;
import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
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
  public static final Paint BACKGROUND = Color.AZURE;

  private static final String CSS_STYLE_SHEET = "default.css";

  private final Group myRoot = new Group();
  private final StateColorPicker colorPickers = new StateColorPicker(myRoot);
  //private final ConwayGameOfLife game = new ConwayGameOfLife(GAME_WIDTH, GAME_HEIGHT);

  private final ButtonSetup myButtonSetup = new ButtonSetup(this);
  private Stage myStage;
  private Timeline animation;
  private Controller myController;
  private SimulationBoard myBoard;
  private Slider speedAdjuster;
  private double animationSpeed = 100 / FRAMES_PER_SECOND;
  private boolean isPaused = false;

  public Display() {
  }

  /**
   * Start the program.
   */
  public static void main(String[] args) {
    launch(args);
  }
  //private Controller myController = new Controller("ConwayGameOfLife.properties");

  public double getAnimationSpeed() {
    return animationSpeed;
  }

  public void setAnimationSpeed(double animationSpeed) {
    this.animationSpeed = animationSpeed;
  }

  @Override
  public void start(Stage stage) {
    // attach scene to the stage and display it

    generateSplashScreen(stage);
  }

  public void generateSplashScreen(Stage stage) {
    myStage = stage;
    SplashScreen startScreen = new SplashScreen(this);
    stage.setScene(startScreen.getMyScene()); //connectinga splash screen
    stage.setTitle(TITLE); //will also come from properties
    stage.show();
  }

//  private void checkWhichGame(SplashScreen startScreen) {
//    //TODO - figure out how to add multiple buttons
//    // TODO: 2020-10-12 abstract for any default property file or any simulation
//    startScreen.getMyButton().setOnAction(new EventHandler<ActionEvent>() {
//      @Override
//      public void handle(ActionEvent e) {
//        chooseSimulation();
//      }
//    });
//  }

  public void chooseSimulation(String simulationType) {
    setController(new Controller("Default" + simulationType + ".properties"));
    Scene gameScene = setupScene();
    myStage.setScene(gameScene);
  }

  // TODO: 2020-10-04 some way to set up the scene based on a level file for testing different levels?
  Scene setupScene() {
    Scene scene = new Scene(myRoot, WIDTH, HEIGHT, BACKGROUND);
    scene.getStylesheets().add(CSS_STYLE_SHEET);
    myButtonSetup.createSetup(myRoot);
    myButtonSetup.checkButtonStatus();
    setUpSpeedAdjuster();
    setUpAnimation();

    return scene;
  }

  private void setUpAnimation() {
    KeyFrame frame = new KeyFrame(Duration.seconds(animationSpeed), e -> step(animationSpeed));
    animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames().add(frame);

  }

  private void setUpSpeedAdjuster() {
    speedAdjuster = new Slider(.5, 5, 1);
    // TODO: 2020-10-12 can we vbox this with the buttons
    speedAdjuster.setLayoutX(WIDTH / 2 - 50);
    speedAdjuster.setLayoutY(HEIGHT - HEIGHT / 4);
    speedAdjuster.valueProperty().addListener((
        ObservableValue<? extends Number> ov,
        Number old_val, Number new_val) -> {
      setAnimationSpeed(new_val.doubleValue());
    });
    myRoot.getChildren().add(speedAdjuster);
  }

  public void play() {
    animation.play();
    isPaused = false;
  }

  void step(double elapsedTime) {
    if(!isPaused){
    animation.setRate(animationSpeed);
    myController.updateView();
    myBoard.updateMyGrid(myController.getGameBoard(), myController.getProperties());
  }
  }

  public Window getStage() {
    return myStage;
  }

  public void pauseGame() {
    isPaused = true;
  }

  public void addImages() {
    myBoard.addImagesOverStates(myController.getProperties());
  }
  public Controller getController() {
    return myController;
  }

  public void setController(Controller controller) {
    myController = controller;
    myBoard = new SimulationBoard(myRoot, myController.getGameBoard(),
        myController.getProperties());
    colorPickers.addColorPickers(myController);
  }


}




