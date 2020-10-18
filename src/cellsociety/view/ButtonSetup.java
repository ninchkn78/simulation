package cellsociety.view;

import cellsociety.controller.Controller;
import cellsociety.model.GameBoard;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

public class ButtonSetup {

  private final Display myDisplay;
  private Button fileSaveButton;
  private Button runButton;
  private Button pauseButton;
  private Button loadFileButton;
  private Button chooseImageButton;
  private Button stepButton;
  private boolean fileSelected = false;

  //TODO - idea - just add more buttons here by calling subclasses or something

  public ButtonSetup(Display myGame) {
    myDisplay = myGame;
  }

  public void createSetup(Group root) {
    createButtons();
    HBox buttonBox = createHBox();
    buttonBox.getChildren()
        .addAll(loadFileButton, runButton, pauseButton, fileSaveButton, chooseImageButton,
            stepButton);
    root.getChildren().add(buttonBox);
  }

  private void createButtons() {
    loadFileButton = new Button("Load File");
    loadFileButton.setId("loadFileButton");
    runButton = new Button("Play");
    runButton.setId("runButton");
    fileSaveButton = new Button("Save File");
    fileSaveButton.setId("saveFileButton");
    pauseButton = new Button("Pause");
    pauseButton.setId("pauseButton");
    chooseImageButton = new Button("Image");
    chooseImageButton.setId("chooseImageButton");
    stepButton = new Button("Step Once");

    stepButton.setId("stepButton");
  }

  private HBox createHBox() {
    HBox buttonBox = new HBox();
    buttonBox.setPrefWidth(Display.WIDTH);
    buttonBox.getStyleClass().add("HBox");
    buttonBox.setLayoutY(Display.HEIGHT - 100);
    return buttonBox;
  }


  public void checkButtonStatus() {
    checkFileWriteButton();
    checkFileReaderButton();
    checkRunButton();
    checkPauseButton();
    checkImageButton();
    checkStepButton();
  }

  public void checkStepButton() {
    stepButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {

        myDisplay.nextGen();
      }
    });


  }

  public void checkFileReaderButton() {
    loadFileButton.setOnAction(e -> {
      fileSelected = true;
      myDisplay.pauseGame();
      FileChooser fileChooser = new FileChooser();
      fileChooser.setTitle("Open Resource File");
      File propertiesFile = fileChooser.showOpenDialog(myDisplay.getStage());
      //String[] pFilePathArr = propertiesFile.getPath().split(".");
      if (propertiesFile != null) {
        myDisplay.setController(new Controller(propertiesFile.getName()));
      }
    });
  }

  public void checkRunButton() {
    runButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        myDisplay.play();
      }

    });
  }

  public void checkPauseButton() {
    pauseButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        myDisplay.pauseGame();

      }

    });
  }

  public void checkFileWriteButton() {
    fileSaveButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        if (myDisplay.getController() != null) {
          System.out.println("WRITE FILE");
          GameBoard myGameBoard = myDisplay.getController().getGameBoard();

          PopUpWindow pUp = new PopUpWindow(myDisplay, myGameBoard);

        }


      }
    });
  }

  public void checkImageButton() {
    chooseImageButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        myDisplay.addImages();

      }

    });
  }


}
