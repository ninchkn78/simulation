package cellsociety.view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class SplashScreen {

  private Scene myScene;
  private Button myButton;

  public SplashScreen(){
      Group root = new Group();
      myScene = new Scene(root, Display.WIDTH, Display.HEIGHT);
      myScene.getStylesheets().add("SplashScreen.css");

      HBox titleBox = new HBox();

      Label newText = new Label("Choose Your Simulation!!");

      titleBox.setPrefWidth(Display.WIDTH);
      titleBox.setPrefHeight(Display.HEIGHT/3);
      titleBox.getChildren().add(newText);
      titleBox.setLayoutY(Display.HEIGHT/4);
      titleBox.getStyleClass().add("TitleBox");

      myButton = new Button("Conway");
      myButton.setId("Conway");
      Button percButton = new Button("Percolation");

      HBox buttonBox = new HBox();
      buttonBox.setPrefWidth(Display.WIDTH);
      buttonBox.setPrefHeight(Display.HEIGHT/3);
      buttonBox.getStyleClass().add("buttonBox");
      buttonBox.setLayoutY((Display.HEIGHT/3) * 2);
      buttonBox.getChildren().addAll(myButton, percButton);


      root.getChildren().addAll(titleBox, buttonBox);



  }

  public Scene getMyScene() {
    return myScene;
  }

  public Button getMyButton() {
    return myButton;
  }

}
