package cellsociety.view;

import cellsociety.controller.Controller;
import cellsociety.model.GameBoard;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

public class ButtonSetup {

  private HBox myHbox = createHBox();
  private final Display myDisplay;
  private boolean fileSelected = false;

  //TODO - idea - just add more buttons here by calling subclasses or something

  public ButtonSetup(Display myGame) {
    myDisplay = myGame;
  }


  public void buttonPipeline(List<String> buttonNames, Group root){
      List<Button> buttonList = new ArrayList<>();
      for(String buttonName: buttonNames){
          //TODO - get the button name from the text resources file
          Button currentButton = new Button(buttonName);
          currentButton.setId(buttonName);
          buttonList.add(currentButton);
          myHbox.getChildren().add(currentButton);
          System.out.println(buttonName);
          try {
            Method method = this.getClass().getDeclaredMethod("check" + buttonName, Button.class);
            method.invoke(this, currentButton);
          }
          catch(SecurityException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e){
            e.printStackTrace();
          }

        //TODO 10/18- add something here I think with a lambda function maybe? Or Reflection? That checks all of the button status'
        }
        root.getChildren().add(myHbox);
  }

  private HBox createHBox() {
    HBox buttonBox = new HBox();
    buttonBox.setPrefWidth(Display.WIDTH);
    buttonBox.getStyleClass().add("HBox");
    buttonBox.setLayoutY(Display.HEIGHT - 100);
    return buttonBox;
  }


  public void checkStepOnce(Button stepButton) {
    stepButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {

        myDisplay.nextGen();
      }
    });

  }

  public void checkLoadFile(Button loadFileButton) {
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

  public void checkPlay(Button runButton) {
    runButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        myDisplay.play();
      }

    });
  }

  public void checkPause(Button pauseButton) {
    pauseButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        myDisplay.pauseGame();

      }

    });
  }

  public void checkSaveFile(Button fileSaveButton) {
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

  public void checkImage(Button chooseImageButton) {
    chooseImageButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        myDisplay.changeCellsToImages();

      }

    });
  }


}
