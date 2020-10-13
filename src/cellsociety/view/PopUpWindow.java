package cellsociety.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

public class PopUpWindow {

  public PopUpWindow(){
//    TextInputDialog td = new TextInputDialog("New File Name");
//    td.setHeaderText("Input Information");
//    td.show();
    // Create the custom dialog.
    Dialog<String[]> dialog = new Dialog<>();
    dialog.setTitle("Save Current Simulation State");
    dialog.setHeaderText("Fill Out Required Information");

// Set the icon (must be included in the project).
    //dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));

// Set the button types.
    ButtonType save = new ButtonType("Save");
    dialog.getDialogPane().getButtonTypes().add(save);

// Create the username and password labels and fields.
    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(20, 150, 10, 10));

    TextField title = new TextField();
    title.setPromptText("Title");
    TextField author = new TextField();
    author.setPromptText("Author");
    TextField description = new TextField();
    description.setPromptText("description");


    grid.add(new Label("Title:"), 0, 0);
    grid.add(title, 1, 0);
    grid.add(new Label("Author:"), 0, 1);
    grid.add(author, 1, 1);
    grid.add(new Label("Description:"),0,2);
    grid.add(description, 1, 2);


    dialog.getDialogPane().setContent(grid);

    dialog.setResultConverter(dialogButton->{
        String[] retArray = new String[]{title.getText(),author.getText(), description.getText()};
        //Basically want to write to a new properties file with this Name
        System.out.println(retArray[0]);

        return retArray ;
    });

    dialog.show();
  }

//  class SaveFileHandler implements EventHandler<ActionEvent> {
//
//    SaveFileHandler() {
//    }
//    @Override
//    public void handle(ActionEvent event) {
//
//    }
//  }
}
