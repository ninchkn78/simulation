package cellsociety.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;

import java.io.IOException;

public class ButtonSetup {
    private Button fileSaveButton;

    //TODO - idea - just add more buttons here by calling subclasses or something

    public void addButtons(Group root){
        fileSaveButton = new Button("TEST ME PUSH ME AHHH");
        fileSaveButton.setLayoutX((ConwayDisplay.WIDTH / 3)*2);
        fileSaveButton.setLayoutY((ConwayDisplay.HEIGHT/5) * 4);
        root.getChildren().add(fileSaveButton);
    }


    //TODO - idea - consider having a button maker class - and then button classes which all extend an abstract button
    //TODO- class which have subclass buttons which all have unique styling and their own actions when pressed.
    public void checkButtonStatus(String[][] tempState){
        fileSaveButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
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
