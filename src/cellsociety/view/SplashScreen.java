package cellsociety.view;

import cellsociety.view.ButtonSetups.ButtonSetup;
import cellsociety.view.ButtonSetups.SplashScreenSetup;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class SplashScreen {

  private final Scene myScene;
  private final List<Button> myButtons = new ArrayList<>();
  private final String CSS_FILE_PATH = "SplashScreen.css";
  private final Display myDisplay;
  private final String PROPERTIES_FILE_PATH = "resources/SplashScreen.properties";
  private Properties splashProperties;
  private Properties languageProperties;

  public SplashScreen(Display display, Properties inputPropertiesFile) {
    myDisplay = display;

    languageProperties = inputPropertiesFile;

    Group root = new Group();
    createPropertiesObject();
    myScene = new Scene(root, Display.WIDTH, Display.HEIGHT);
    myScene.getStylesheets().add(CSS_FILE_PATH);

    ButtonSetup mySplashScreenSetup = createButtonSetup(root);
    addTextBox(root, mySplashScreenSetup);
    createLanguageDropdown(root);
  }

  private void createLanguageDropdown(Group root) {


    ObservableList<String> options =
        FXCollections.observableArrayList(
            splashProperties.getProperty("Language1"),
            splashProperties.getProperty("Language2"),
            splashProperties.getProperty("Language3")
        );
    final ComboBox comboBox = new ComboBox(options);

    Label defaultLanguage = new Label("English");

    EventHandler<ActionEvent> event =
        new EventHandler<ActionEvent>() {
          public void handle(ActionEvent e)
          {
            defaultLanguage.setText(comboBox.getValue() + " selected");
          }
        };

    comboBox.setOnAction(event);
    root.getChildren().add(comboBox);
  }

  public ButtonSetup createButtonSetup(Group root) {
    ButtonSetup mySplashScreenSetup = new SplashScreenSetup(myDisplay);
    List<String> simulationNames = mySplashScreenSetup
        .parseButtonsFromProperties(10, splashProperties);
    mySplashScreenSetup.buttonPipeline(simulationNames, root, "buttonBox", 3 / 5.0, languageProperties);
    return mySplashScreenSetup;
  }

  public void addTextBox(Group root, ButtonSetup mySplashScreenSetup) {
    HBox titleBox = mySplashScreenSetup.createHBox("TitleBox", 2 / 5.0);
    Label newText = new Label(languageProperties.getProperty("TitleText"));
    titleBox.getChildren().add(newText);
    root.getChildren().add(titleBox);
  }

  //TODO - use the one from display instead - it doesnt the same thing pretty much
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
