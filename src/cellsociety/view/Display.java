package cellsociety.view;


import cellsociety.controller.Controller;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
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
  public static final Paint BACKGROUND = Color.AZURE;

  private static final String CSS_STYLE_SHEET = "default.css";
  private static final int NUMBER_POSSIBLE_BUTTONS = 10;

  private final Group myRoot = new Group();

  private final StateConfig stateConfigBox = new StateConfig(myRoot, this);


  private final ButtonSetup myButtonSetup = new ButtonSetup(this);
  private Stage myStage;
  private Timeline animation;
  private Controller myController;
  private SimulationBoard myBoard;
  private Slider speedAdjuster;
  private double animationSpeed = 120 / FRAMES_PER_SECOND;
  private boolean isPaused = true;

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
    generateSplashScreen(stage);
  }


  public void generateSplashScreen(Stage stage) {
    myStage = stage;
    SplashScreen startScreen = new SplashScreen(this);
    stage.setScene(startScreen.getMyScene()); //connectinga splash screen
    stage.setTitle(TITLE); //will also come from properties
    stage.show();
  }

  public void chooseSimulation(String simulationType) {
    myBoard = new SimulationBoard(myRoot);
    setController(new Controller("Default" + simulationType + ".properties"));
    Scene gameScene = setupScene();
    myStage.setScene(gameScene);
  }

  // TODO: 2020-10-04 some way to set up the scene based on a level file for testing different levels?
  Scene setupScene() {
    Scene scene = new Scene(myRoot, WIDTH, HEIGHT, BACKGROUND);
    scene.getStylesheets().add(CSS_STYLE_SHEET);
    parseButtonsFromProperties();
    setUpSpeedAdjuster();
    setUpAnimation();
    animation.play();
    return scene;
  }

  public void parseButtonsFromProperties(){
    List<String> buttonNameList = new ArrayList<>();
    for(int buttonNum = 1; buttonNum<=NUMBER_POSSIBLE_BUTTONS; buttonNum++){
      if(!((String)myController.getProperties().get("Button"+buttonNum)).equals("")) {
        buttonNameList.add((String) myController.getProperties().get("Button" + buttonNum));
      }
      }
    myButtonSetup.buttonPipeline(buttonNameList, myRoot);
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
    isPaused = false;
  }

  void step(double elapsedTime) {
    if (!isPaused) {

      animation.setRate(animationSpeed);
      nextGen();
    }
  }

  public void nextGen() {
    myController.updateView();
    myBoard.updateMyGrid(myController.getGameBoard(), myController.getProperties());
  }


  public Window getStage() {
    return myStage;
  }

  public void pauseGame() {
    isPaused = true;
  }

  public void changeCellsToImages() {
    myBoard.setGridType("Image");
  }

  public Controller getController() {
    return myController;
  }

  public void setController(Controller controller) {
    myController = controller;
    // TODO: 2020-10-17 make a simulation board at the beginning, then have another method that updates
    stateConfigBox.addStateConfigs(myController);
    myBoard.setUpNewSimulation(controller.getGameBoard(), controller.getProperties());
  }
}




