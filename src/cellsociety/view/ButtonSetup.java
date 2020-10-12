package cellsociety.view;

import cellsociety.controller.Controller;
import cellsociety.model.GameBoard;
import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

public class ButtonSetup {

  private Button fileSaveButton;
  private Button runButton;
  private Button pauseButton;

  private final Display myDisplay;
  private Button loadFileButton;
  private boolean fileSelected = false;

  //TODO - idea - just add more buttons here by calling subclasses or something

  public ButtonSetup(Display myGame) {
    myDisplay = myGame;
  }


  public void createSetup(Group root) {
    createButtons();
    HBox buttonBox = createHBox();
    buttonBox.getChildren().addAll(loadFileButton,runButton,pauseButton,fileSaveButton);
    root.getChildren().add(buttonBox);
  }

  private void createButtons() {
    loadFileButton = new Button("Load File");
    runButton = new Button("Play");
    fileSaveButton = new Button("Save File");
    pauseButton = new Button("Pause");
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
    checkPauseutton();
  }

  public void checkFileReaderButton() {

    loadFileButton.setOnAction(e -> {
      fileSelected = true;
      myDisplay.pauseGame();
      FileChooser fileChooser = new FileChooser();
      fileChooser.setTitle("Open Resource File");
      File propertiesFile = fileChooser.showOpenDialog(myDisplay.getStage());

      //String[] pFilePathArr = propertiesFile.getPath().split(".");
      if(propertiesFile!= null){
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

  public void checkPauseutton() {
    pauseButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        myDisplay.pauseGame();

    };
  });}

  public void checkFileWriteButton() {
    fileSaveButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        if(myDisplay.getController()!=null ) {
          System.out.println("WRITE FILE");
          GameBoard myGameBoard = myDisplay.getController().getGameBoard();
          saveFiles saveFileObject = new saveFiles();
          try {
            saveFileObject.saveState(myGameBoard.getGameBoardStates());
          } catch (IOException ioException) {
          }
        }


      }
    });
  }


}
