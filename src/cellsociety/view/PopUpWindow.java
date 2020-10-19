package cellsociety.view;

import cellsociety.model.GameBoard;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class PopUpWindow {

  // TODO: 2020-10-19 this needs to be in side of text properties
  private static final String HEADER_TITLE = "Fill Out Required Information";
  private static final String DIALOG_TITLE = "Save Current Simulation State";
  private static final String BUTTON_TITLE = "Save";

  private static final String TITLE = "Title";
  private static final String AUTHOR = "Author";
  private static final String DESCRIPTION = "Description";

  private final GameBoard myGameBoard;
  private final Properties properties;
  private GridPane myGrid;

  public PopUpWindow(Display display, GameBoard gameBoard) {
    myGameBoard = gameBoard;
    properties = display.getController().getProperties();

    Dialog<String[]> dialog = createDialog();
    createGridPane();

    TextField title = createTextFeild(TITLE);
    TextField author = createTextFeild(AUTHOR);
    TextField description = createTextFeild(DESCRIPTION);

    addToGrid(title, TITLE, 0);
    addToGrid(author, AUTHOR, 1);
    addToGrid(description, DESCRIPTION, 2);

    dialog.getDialogPane().setContent(myGrid);
    //Optional<ButtonType> result = dialog.getButtonType().showAndWait();

    dialog.setResultConverter(dialogButton -> {
      String[] retArray = new String[]{title.getText(), author.getText(), description.getText()};
      System.out.println(retArray[0]);
      storeInPropertiesFile(retArray);
      return retArray;
    });

    dialog.show();
  }

  private void createGridPane() {
    //found this ish online
    myGrid = new GridPane();
    myGrid.setHgap(10);
    myGrid.setVgap(10);
    myGrid.setPadding(new Insets(20, 150, 10, 10));
  }

  private void addToGrid(TextField title, String title2, int row) {
    myGrid.add(new Label(title2 + ":"), 0, row);
    myGrid.add(title, 1, row);
  }

  private TextField createTextFeild(String fieldText) {
    TextField title = new TextField();
    title.setPromptText(fieldText);
    return title;
  }

  private Dialog<String[]> createDialog() {
    Dialog<String[]> dialog = new Dialog<>();
    dialog.setTitle(DIALOG_TITLE);
    dialog.setHeaderText(HEADER_TITLE);
    ButtonType save = new ButtonType(BUTTON_TITLE);
    dialog.getDialogPane().getButtonTypes().add(save);
    return dialog;
  }

  public void storeInPropertiesFile(String[] inputs) {
    try {
      properties.setProperty(TITLE, inputs[0]);
      properties.setProperty(AUTHOR, inputs[1]);
      properties.setProperty(DESCRIPTION, inputs[2]);

      SaveFiles saveFileObject = new SaveFiles();
      saveFileObject.saveState(myGameBoard.getGameBoardStates(), inputs[0]);

      properties.setProperty("CSVSource", "GAME_CSVS/" + inputs[0] + ".csv,set");
      properties.store(new FileOutputStream("resources/Default_Properties_Files/" + inputs[0] + ".properties"), null);

    } catch (IOException e) {
      e.printStackTrace();
    }


  }

}
