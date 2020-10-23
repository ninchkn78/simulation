package cellsociety.view;

import cellsociety.controller.Controller;
import java.util.Properties;
import javafx.scene.Group;
import javafx.scene.layout.VBox;

/**
 * Represents the possible customizations for each state in a simulation
 */
public class StateConfig {

  private final StateImagePicker imagePickers;
  private final StateColorPicker colorPickers;
  private final VBox stateConfigBox = new VBox();
  private final Properties languageProperties;

  public StateConfig(Group root, Display display, Properties textProperties) {
    languageProperties = textProperties;
    imagePickers = new StateImagePicker(stateConfigBox, display);
    colorPickers = new StateColorPicker(stateConfigBox);
    root.getChildren().add(stateConfigBox);
  }

  /**
   * Adds image and state config options
   * @param controller
   */
  public void addStateConfigs(Controller controller) {
    imagePickers.addImagePickers(controller, languageProperties);
    colorPickers.addColorPickers(controller);
  }
}
