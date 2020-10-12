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
    runButton = new Button("Run Simulation");
    fileSaveButton = new Button("Save File");

  }

  private HBox createHBox() {
    HBox buttonBox = new HBox();
    buttonBox.setPrefWidth(Display.WIDTH);
    buttonBox.getStyleClass().add("HBox");
    buttonBox.setLayoutY(Display.HEIGHT - 100);
    return buttonBox;
  }


  public void checkButtonStatus(GameBoard myGameBoard) {
    checkFileButton(myGameBoard);
    checkFileReaderButton();
  }

  private void checkFileReaderButton() {
      loadFileButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        if(myDisplay.getAnimation()!=null ) {
          myDisplay.pauseGame();
        }
        //System.out.println(1);  //TODO add file reader
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File propertiesFile = fileChooser.showOpenDialog(myDisplay.getStage());
        //this method will create the agme board and create na instance of proper model

        String[] pFilePathArr = propertiesFile.getPath().split(".");
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
        myDisplay.startStepMethod();
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
