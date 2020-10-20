package cellsociety.view;


import cellsociety.controller.Controller;
import cellsociety.view.ButtonSetups.GridViewButtonSetup;
import exceptions.InvalidPropertiesFileException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Properties;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Display extends Application {

  // TODO: 2020-10-1 THisshit should be in css
  public static final String TITLE = "Simulation";
  public static final int WIDTH = 800;
  public static final int HEIGHT = 600;
  public static final int FRAMES_PER_SECOND = 60;
  public static final Paint BACKGROUND = Color.AZURE;
  private static final String DEFAULT_HBOX_CSS_CLASS = "HBox";
  private static final double DEFAULT_Y_OFFSET = 0.75;

  private static final String CSS_STYLE_SHEET = "default.css";
  private static final int NUMBER_POSSIBLE_BUTTONS = 10;

  private final Group myRoot = new Group();

  private StateConfig stateConfigBox;


  private final GridViewButtonSetup myGridViewButtonSetup = new GridViewButtonSetup(this);
  private Stage myStage;
  private Timeline animation;
  private Controller myController;
  private SimulationBoard myBoard;
  private double animationSpeed = 120 / FRAMES_PER_SECOND;
  private boolean isPaused = true;
  private final String DEFAULT_LANGUAGE_PROP_FILE = "resources/Text_Properties_Files/English.properties";
  private Properties languageProperties;

  public Display() {

  }

  /**
   * Start the program.
   */
  public static void main(String[] args) {
    launch(args);
  }
  //private Controller myController = new Controller("ConwayGameOfLife.properties");

  public void setAnimationSpeed(double animationSpeed) {
    this.animationSpeed = animationSpeed;
  }

  @Override
  public void start(Stage stage) {
    languageProperties = createPropertiesObject(DEFAULT_LANGUAGE_PROP_FILE);
    myStage = stage;
    generateSplashScreen(languageProperties, stage);

  }

  //
  public Properties createPropertiesObject(String propertiesFileName) {
    Properties tempPropFile = null;
    try (InputStream input = new FileInputStream(propertiesFileName)) {
      tempPropFile = new Properties();
      tempPropFile.load(input);
    }
    catch (IOException ex) {
      ex.printStackTrace();
    }

    return tempPropFile;
  }


  public void generateSplashScreen(Properties languageProperties, Stage stage) {
    myStage = stage;
    SplashScreen startScreen = new SplashScreen(this, languageProperties);
    stage.setScene(startScreen.getMyScene()); //connectinga splash screen
    stage.setTitle(TITLE); //will also come from properties
    stage.show();
  }

  public void chooseSimulation(String simulationType, Properties textProperties) {
    myBoard = new SimulationBoard(myRoot);
    stateConfigBox = new StateConfig(myRoot, this, textProperties);
    setNewSimulation("Default" + simulationType + ".properties");
    Scene gameScene = setupScene(textProperties);
    myStage.setScene(gameScene);
  }


  // TODO: 2020-10-04 some way to set up the scene based on a level file for testing different levels?
  Scene setupScene(Properties textProperties) {
    Scene scene = new Scene(myRoot, WIDTH, HEIGHT, BACKGROUND);
    scene.getStylesheets().add(CSS_STYLE_SHEET);
    setUpButtons(textProperties);
    //parseButtonsFromProperties();
    setUpSpeedAdjuster();
    setUpAnimation();
    animation.play();
    return scene;
  }

  private void setUpButtons(Properties textProperties) {
    Properties properties = myController.getProperties();
    List<String> buttonNameList = myGridViewButtonSetup
        .parseButtonsFromProperties(NUMBER_POSSIBLE_BUTTONS, properties);
    myGridViewButtonSetup
        .buttonPipeline(buttonNameList, myRoot, DEFAULT_HBOX_CSS_CLASS, DEFAULT_Y_OFFSET,
            textProperties);
  }


  private void setUpAnimation() {
    KeyFrame frame = new KeyFrame(Duration.seconds(animationSpeed), e -> step());
    animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames().add(frame);

  }

  private void setUpSpeedAdjuster() {
    Slider speedAdjuster = new Slider(.5, 5, 1);
    // TODO: 2020-10-12 can we vbox this with the buttons
    speedAdjuster.setLayoutX(WIDTH / 2 - 50);
    speedAdjuster.setLayoutY(HEIGHT - HEIGHT / 4);
    speedAdjuster.valueProperty().addListener((
        ObservableValue<? extends Number> ov,
        Number old_val, Number new_val) -> setAnimationSpeed(new_val.doubleValue()));
    myRoot.getChildren().add(speedAdjuster);
  }

  public void play() {
    isPaused = false;
  }

  void step() {
    if (!isPaused) {
      animation.setRate(animationSpeed);
      nextGen();
    }
  }

  public void nextGen() {
    myController.updateView();
    myBoard.updateMyGrid(myController.getGameBoard(), myController.getProperties());
  }


  public Stage getStage() {
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

  public void setNewSimulation(String propertiesName){
    try {
      Controller controller = new Controller(propertiesName);
      setController(controller);
      stateConfigBox.addStateConfigs(myController);
      myBoard.setUpNewSimulation(myController);
    } catch(InvalidPropertiesFileException e){
      makeAlert("Bad",e.getMessage());
    }catch(InvocationTargetException e){
      makeAlert("Bad Bad",e.getCause().getLocalizedMessage());
    }
  }

  public void makeAlert (String header, String message) {
    Alert a = new Alert(Alert.AlertType.NONE);
    ButtonType close = new ButtonType(":(", ButtonBar.ButtonData.CANCEL_CLOSE);
    a.getButtonTypes().addAll(close);
    a.setHeaderText(header);
    a.setContentText(message);
    a.show();
  }

  public void setController(Controller controller) {
    myController = controller;
  }
}




