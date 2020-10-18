package cellsociety.view;

import cellsociety.view.ButtonSetups.ButtonSetup;
import cellsociety.view.ButtonSetups.SplashScreenSetup;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class SplashScreen {

  private final Scene myScene;
  private final List<Button> myButtons = new ArrayList<>();
  private final String CSS_FILE_PATH = "SplashScreen.css";
  private final Display myDisplay;
  private final String PROPERTIES_FILE_PATH = "resources/SplashScreen.properties";
  private Properties splashProperties;

  public SplashScreen(Display display) {
    myDisplay = display;
    Group root = new Group();
    createPropertiesObject();
    myScene = new Scene(root, Display.WIDTH, Display.HEIGHT);
    myScene.getStylesheets().add(CSS_FILE_PATH);

    ButtonSetup mySplashScreenSetup = createButtonSetup(root);
    addTextBox(root, mySplashScreenSetup);

  }

  public ButtonSetup createButtonSetup(Group root) {
    ButtonSetup mySplashScreenSetup = new SplashScreenSetup(myDisplay);
    List<String> simulationNames = mySplashScreenSetup
        .parseButtonsFromProperties(10, splashProperties);
    mySplashScreenSetup.buttonPipeline(simulationNames, root, "buttonBox", 3 / 5.0);
    return mySplashScreenSetup;
  }

  public void addTextBox(Group root, ButtonSetup mySplashScreenSetup) {
    HBox titleBox = mySplashScreenSetup.createHBox("TitleBox", 2 / 5.0);
    Label newText = new Label("Choose Your Simulation!!");
    titleBox.getChildren().add(newText);
    root.getChildren().add(titleBox);
  }

  private void createPropertiesObject() {
    try (InputStream input = new FileInputStream(PROPERTIES_FILE_PATH)) {
      splashProperties = new Properties();
      splashProperties.load(input);

    } catch (IOException ex) {
      ex.printStackTrace();
    }

  }

  public Scene getMyScene() {
    return myScene;
  }

}
