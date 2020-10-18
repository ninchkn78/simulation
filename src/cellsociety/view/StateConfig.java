package cellsociety.view;

import cellsociety.controller.Controller;
import javafx.scene.Group;
import javafx.scene.layout.VBox;

public class StateConfig {

  private final StateImagePicker imagePickers;
  private final StateColorPicker colorPickers;
  private final VBox stateConfigBox = new VBox();

  public StateConfig(Group root, Display display) {
    imagePickers = new StateImagePicker(stateConfigBox, display);
    colorPickers = new StateColorPicker(stateConfigBox);
    root.getChildren().add(stateConfigBox);
  }

  public void addStateConfigs(Controller controller) {
    imagePickers.addImagePickers(controller);
    colorPickers.addColorPickers(controller);
  }
}