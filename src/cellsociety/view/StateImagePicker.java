package cellsociety.view;

import cellsociety.controller.Controller;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class StateImagePicker {

  private final HBox imagePickerBox;
  private final Display myDisplay;

  public StateImagePicker(VBox stateConfigBox, Display display) {
    myDisplay = display;
    imagePickerBox = new HBox();
    imagePickerBox.getStyleClass().add("color-picker-box");
    stateConfigBox.getChildren().add(imagePickerBox);
  }

  private static void configureFileChooser(
      final FileChooser fileChooser) {
    fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("All Images", "*.*"),
        new FileChooser.ExtensionFilter("JPG", "*.jpg"),
        new FileChooser.ExtensionFilter("PNG", "*.png")
    );
  }

  private void addImagePicker(String state, Controller controller) {
    final FileChooser fileChooser = new FileChooser();
    Button openButton = new Button("Open a Picture...");
    openButton.setOnAction(
        (final ActionEvent e) -> {
          configureFileChooser(fileChooser);
          File file = fileChooser.showOpenDialog(myDisplay.getStage());
          if (file != null) {
            controller.getProperties().setProperty(String.format("%simage", state),
                file.getAbsolutePath().replaceAll("\\\\", "/"));
            controller.overWriteProperties();
          }
        });
    imagePickerBox.getChildren().add(openButton);
  }

  public void addImagePickers(Controller controller) {
    imagePickerBox.getChildren().clear();
    for (String state : controller.getProperties().getProperty("States").split(",")) {
      addImagePicker(state, controller);
    }
  }

}
