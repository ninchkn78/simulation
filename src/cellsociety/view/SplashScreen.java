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

      HBox titleBox = createHBox(5, "TitleBox");
      HBox buttonBox = createHBox(3, "buttonBox");


      Label newText = new Label("Choose Your Simulation!!");
      titleBox.getChildren().add(newText);

      myButton = new Button("Conway");
      myButton.setId("Conway");
      Button percButton = new Button("Percolation");

      buttonBox.getChildren().addAll(myButton, percButton);


      root.getChildren().addAll(titleBox, buttonBox);



  }

    private HBox createHBox(int yOffsetFactor, String cssClass) {
    HBox currentHBox = new HBox();
    currentHBox.setPrefWidth(Display.WIDTH);
    currentHBox.setPrefHeight(Display.HEIGHT/4);
    currentHBox.getStyleClass().add(cssClass);
    currentHBox.setLayoutY((Display.HEIGHT/yOffsetFactor) *2);
    return currentHBox;
  }

  public Scene getMyScene() {
    return myScene;
  }

  public Button getMyButton() {
    return myButton;
  }

}
