package cellsociety.view;


import cellsociety.controller.Controller;
import java.util.Arrays;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
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
  public static final int DEFAULT_SPEED = 100/60;
  public static final Paint BACKGROUND = Color.AZURE;
  public static final int GAME_WIDTH = 13;
  public static final int GAME_HEIGHT = 11;

  private static final String DEFAULT_PROPERTY_FILE_NAME = "RPS.properties"; //TODO - ADD REFLECTION
  private static final String CSS_STYLE_SHEET = "default.css";

  private final Group myRoot = new Group();
  //private final ConwayGameOfLife game = new ConwayGameOfLife(GAME_WIDTH, GAME_HEIGHT);

  private final ButtonSetup myButtonSetup = new ButtonSetup(this);
  private Stage myStage;
  private Timeline animation;
  private Controller myController;
  private SimulationBoard myBoard;
  private Slider speedAdjuster;

  public double getAnimationSpeed() {
    return animationSpeed;
  }

  public void setAnimationSpeed(double animationSpeed) {
    this.animationSpeed = animationSpeed;
  }

  private double animationSpeed = 100 / FRAMES_PER_SECOND;
  //private Controller myController = new Controller("ConwayGameOfLife.properties");

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
    SplashScreen startScreen = new SplashScreen();
    myStage.setScene(startScreen.getMyScene()); //connectinga splash screen
    checkWhichGame(startScreen);
    stage.setTitle(TITLE); //will also come from properties
    stage.show();
  }

  private void checkWhichGame(SplashScreen startScreen) {
    //TODO - figure out how to add multiple buttons
    // TODO: 2020-10-12 abstract for any default property file or any simulation
    startScreen.getMyButton().setOnAction(new EventHandler<ActionEvent>(){
      @Override public void handle(ActionEvent e){
        setController(new Controller(DEFAULT_PROPERTY_FILE_NAME));
          Scene gameScene = setupScene();
          myStage.setScene(gameScene);
      }
    });
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
    speedAdjuster = new Slider(.5,5,1);
    // TODO: 2020-10-12 can we vbox this with the buttons
    speedAdjuster.setLayoutX(WIDTH/2 - 50);
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
  }

  // TODO: 2020-10-04 this 100% needs to change, but just doing this for now to be able to update?
  void step(double elapsedTime) {
    animation.setRate(animationSpeed);
    myController.updateView();
    myBoard.updateMyGrid(myController.getGameBoard(), myController.getProperties());
  }

  public Window getStage() {
    return myStage;
  }

  public void pauseGame(){
    if(animation != null) {
      animation.pause();
    }
  }


  public void setController(Controller controller) {
      myController = controller;
      myBoard= new SimulationBoard(myRoot, myController.getGameBoard(), myController.getProperties());
  }

  public Controller getController(){
    return myController;
  }

  }




