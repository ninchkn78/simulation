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
    buttonBox.getChildren().addAll(loadFileButton,runButton,fileSaveButton);
    root.getChildren().add(buttonBox);
  }

  private void createButtons() {
    loadFileButton = new Button("Load File");
    runButton = new Button("Run Default");
    fileSaveButton = new Button("Save File");

  }

  private HBox createHBox() {
    HBox buttonBox = new HBox();
    buttonBox.setPrefWidth(Display.WIDTH);
    buttonBox.getStyleClass().add("HBox");
    buttonBox.setLayoutY(Display.HEIGHT - 100);
    return buttonBox;
  }


  public void checkButtonStatus() {
    //checkFileButton(myGameBoard);
    checkFileReaderButton();
    checkRunButton();
  }

  public void checkFileReaderButton() {

    loadFileButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        fileSelected = true;
        if(myDisplay.getAnimation()!=null ) {
          myDisplay.pauseGame();
        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File propertiesFile = fileChooser.showOpenDialog(myDisplay.getStage());

        //String[] pFilePathArr = propertiesFile.getPath().split(".");
        if(propertiesFile!= null){
          myDisplay.setController(new Controller(propertiesFile.getName()));
        }


      }
      });
  }

  public void checkRunButton() {
    runButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        if(!fileSelected){
          myDisplay.setController(new Controller(Display.getDefaultPropertyFileName()));
        }
        myDisplay.startStepMethod(Display.SECOND_DELAY);
      }

    });
  }


  public void checkFileButton(GameBoard myGameBoard) {
    fileSaveButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        System.out.println("WRITE FILE");
        saveFiles saveFileObject = new saveFiles();
        try {
          saveFileObject.saveState(myGameBoard.getGameBoardStates());
        } catch (IOException ioException) {
        }
      }
    });
  }


}
