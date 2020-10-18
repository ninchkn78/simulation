package cellsociety.view;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class SplashScreen {

  private final Scene myScene;
  private final List<Button> myButtons = new ArrayList<>();
  private final Display myDisplay;

  public SplashScreen(Display display) {
    myDisplay = display;
    Group root = new Group();
    myScene = new Scene(root, Display.WIDTH, Display.HEIGHT);
    myScene.getStylesheets().add("SplashScreen.css");

    ButtonSetup myButtonSetup = new ButtonSetup(myDisplay);

    HBox titleBox = myButtonSetup.createHBox("TitleBox", 2/5.0);
    HBox buttonBox = myButtonSetup.createHBox("buttonBox", 3/5.0);

    Label newText = new Label("Choose Your Simulation!!");
    titleBox.getChildren().add(newText);

    // TODO: 2020-10-12 abstract :(
    addNewButton("ConwayGameOfLife");
    addNewButton("Percolation");
    addNewButton("RPS");
    addNewButton("SpreadingFire");

    buttonBox.getChildren().addAll(myButtons);
    root.getChildren().addAll(titleBox, buttonBox);

  }

  private Button createSimulationButton(String simulationName) {
    Button button = new Button(simulationName);
    button.setOnAction(new SimulationChooserHandler(simulationName));
    return button;
  }

  public Scene getMyScene() {
    return myScene;
  }

  private void addNewButton(String simulationName) {
    Button simulationButton = createSimulationButton(simulationName);
    myButtons.add(simulationButton);
    simulationButton.setId(simulationName);
  }

  class SimulationChooserHandler implements EventHandler<ActionEvent> {

    private final String simulationName;

    SimulationChooserHandler(String simulationName) {
      this.simulationName = simulationName;
    }

    @Override
    public void handle(ActionEvent event) {
      myDisplay.chooseSimulation(simulationName);
    }
  }
}
