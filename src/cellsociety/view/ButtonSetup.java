package cellsociety.view;

import cellsociety.controller.Controller;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
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


  public void addButtons(Group root) {
    addSaveFileButton(root);
    addRunButton(root);
    addLoadFileButton(root);
  }

  private void addLoadFileButton(Group root) {
    loadFileButton = new Button("Load File");
    loadFileButton.setLayoutX((Display.WIDTH / 4) * 1);
    loadFileButton.setLayoutY((Display.HEIGHT / 5) * 4);
    root.getChildren().add(loadFileButton);
  }

  private void addRunButton(Group root) {
    runButton = new Button("RUN SIMULATION");
    runButton.setLayoutX((Display.WIDTH / 4) * 2);
    runButton.setLayoutY((Display.HEIGHT / 5) * 4);
    root.getChildren().add(runButton);
  }

  private void addSaveFileButton(Group root) {
    fileSaveButton = new Button("TEST ME PUSH ME AHHH");
    fileSaveButton.setLayoutX((Display.WIDTH / 4) * 3);
    fileSaveButton.setLayoutY((Display.HEIGHT / 5) * 4);
    root.getChildren().add(fileSaveButton);
  }


  //TODO - idea - consider having a button maker class - and then button classes which all extend an abstract button
  //TODO- class which have subclass buttons which all have unique styling and their own actions when pressed.
  public void checkButtonStatus(String[][] tempState) {
    checkFileButton(tempState);
    checkFileReaderButton();
  }

  private void checkFileReaderButton() {
      loadFileButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        myDisplay.pauseGame();
        //System.out.println(1);  //TODO add file reader
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.showOpenDialog(myDisplay.getStage());

        //this method will create the agme board and create na instance of proper model
        //myDisplay.getController() = new Controller();


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


  public void checkFileButton(String[][] tempState) {
    fileSaveButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        System.out.println("WRITE FILE");
        saveFiles saveFileObject = new saveFiles();
        try {
          saveFileObject.saveState(tempState);
        } catch (IOException ioException) {
        }

      }
    });
  }


}
