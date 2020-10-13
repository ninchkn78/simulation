package cellsociety.view;

import cellsociety.model.GameBoard;
import cellsociety.model.games.Simulation;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;


public class SimulationBoard {

  // TODO: 2020-10-10 make this CSS
  public static final double CELL_GRID_WIDTH = 600;
  public static final double CELL_GRID_HEIGHT = 350;

  private final GridPane myGrid = new GridPane();
  private final List<List<CellView>> cells = new ArrayList<>();
  private final List<List<ImageView>> cellImages = new ArrayList<>();
  private final Group myRoot;


  public SimulationBoard(Group root, GameBoard gameBoard, Properties properties) {
    myGrid.setLayoutX(75);
    myGrid.setLayoutY(50);
    myGrid.setGridLinesVisible(true);
    root.getChildren().add(myGrid);
    myRoot = root;
    initializeMyGrid(gameBoard,properties);
  }

  //works for non square 2D arrays
  // TODO: 2020-10-04 ask about X position for tests
  // TODO: 2020-10-05  don't make new rectangles every time
  private void initializeMyGrid(GameBoard gameBoard, Properties properties) {
    String[][] states = gameBoard.getGameBoardStates();
    double width = CELL_GRID_WIDTH / maxRowLength(states);
    for (int i = 0; i < states.length; i++) {
      cells.add(new ArrayList<>());
      cellImages.add(new ArrayList<>());
      for (int j = 0; j < states[i].length; j++) {
        CellView cell = addCellViewToGrid(
            new CellView(width, CELL_GRID_HEIGHT / states.length, states[i][j], properties), i, j);
        cells.get(i).add(cell);
        cellImages.get(i).add(new ImageView());
      }
    }
  }

  private CellView addCellViewToGrid(CellView cell1, int i, int j) {
    CellView cell = cell1;
    cell.setId(String.format("cell%d,%d", i, j));
    GridPane.setConstraints(cell, j, i);
    myGrid.getChildren().add(cell);
    return cell;
  }

  //TODO: just a thought: maybe put the 2d array in to a list of arrays, and then call Collections.max? - franklin
  private int maxRowLength(String[][] array) {
    int maxRowLength = 0;
    for (String[] row : array) {
      if (row.length > maxRowLength) {
        maxRowLength = row.length;
      }
    }
    return maxRowLength;
  }

  // TODO: 2020-10-10  abstract this

  public void updateMyGrid(GameBoard gameBoard, Properties properties) {
    String[][] states = gameBoard.getGameBoardStates();
    for(int i = 0; i < cells.size(); i++){
      for(int j = 0; j < cells.get(i).size(); j++){
        cells.get(i).get(j).setColor(states[i][j],properties);
        updateCellImage(properties,cellImages.get(i).get(j),states[i][j]);
      }
    }
  }
  public void addImagesOverStates(Properties properties) {
    for(int i = 0; i < cells.size(); i++){
      for(int j = 0; j < cells.get(i).size(); j++){
        replaceCellWithImage(cells.get(i).get(j), cellImages.get(i).get(j));
        updateCellImage(properties,cellImages.get(i).get(j),cells.get(i).get(j).getState());
      }
    }
  }

  private void replaceCellWithImage(CellView cell, ImageView imageView) {
    imageView.setFitWidth(cell.getWidth());
    imageView.setFitHeight(cell.getHeight());
    myGrid.getChildren().remove(cell);
    int col = GridPane.getColumnIndex(cell);
    int row = GridPane.getRowIndex(cell);
    GridPane.setConstraints(imageView, row, col);
    myGrid.getChildren().add(imageView);
  }

  public void updateCellImage(Properties properties, ImageView cellImage, String state){
      FileInputStream inputstream = null;
      try {
        // TODO: 2020-10-13 if state doesn't exist  
        System.out.println(properties.getProperty(state + "image"));
        inputstream = new FileInputStream(properties.getProperty(state + "image"));

      } catch (IOException e) {
        e.printStackTrace();
      }
      Image image = new Image(inputstream);
      cellImage.setImage(image);


  }
}
