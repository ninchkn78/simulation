package cellsociety.view;

import javafx.scene.Group;
import javafx.scene.control.Button;

public class ButtonSetup {
    public ButtonSetup(Group root){
        addButtons(root);
    }

    private void addButtons(Group root){
        Button testButton = new Button("TEST ME PUSH ME AHHH");
        testButton.setLayoutX(ConwayDisplay.WIDTH / 2);
        testButton.setLayoutY(ConwayDisplay.HEIGHT/2);
        root.getChildren().add(testButton);
    }
}
