package cellsociety.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ButtonSetup {
    public ButtonSetup(Group root){
        addButtons(root);
    }

    private void addButtons(Group root){
        Button testButton = new Button("TEST ME PUSH ME AHHH");
        testButton.setLayoutX(Display.WIDTH / 2);
        testButton.setLayoutY(Display.HEIGHT/2);
        root.getChildren().add(testButton);
    }
}
