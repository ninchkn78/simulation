package cellsociety.view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class SplashScreen {

  private Scene myScene;
  private Button myButton;

  public SplashScreen(){
      Group root = new Group();
      Text newText = new Text("lorem ipsum");
      newText.setLayoutX(50);
      newText.setLayoutY(100);

      myButton = new Button("Conway");
      root.getChildren().add(myButton);

      root.getChildren().add(newText);
      myScene = new Scene(root, Display.WIDTH, Display.HEIGHT);
    }

  public Scene getMyScene() {
    return myScene;
  }

  public Button getMyButton() {
    return myButton;
  }

}
