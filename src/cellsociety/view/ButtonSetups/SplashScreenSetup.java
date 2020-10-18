package cellsociety.view.ButtonSetups;

import cellsociety.view.Display;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;


public class SplashScreenSetup extends ButtonSetup {

  private final Display myDisplay;

  public SplashScreenSetup(Display display) {
    myDisplay = display;
  }


  @Override
  protected void invokeHandlerMethod(String buttonName, Button currentButton) {
    System.out.println(buttonName);
    currentButton.setOnAction(new SimulationChooserHandler(buttonName));
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

