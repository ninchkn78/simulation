package cellsociety.view;

import cellsociety.model.GameBoard;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;


public class SimulationBoard {

  // TODO: 2020-10-10 make this CSS
  public static final double CELL_GRID_WIDTH = 600;
  public static final double CELL_GRID_HEIGHT = 350;

  private final GridPane myGrid = new GridPane();
  private final List<List<CellView>> cells = new ArrayList<>();
  private final List<List<ImageView>> cellImages = new ArrayList<>();



  public SimulationBoard(Group root, GameBoard gameBoard, Properties properties) {
    myGrid.setLayoutX(75);
    myGrid.setLayoutY(50);
    myGrid.setGridLinesVisible(true);
    root.getChildren().add(myGrid);
    initializeMyGrid(gameBoard, properties);
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
        addCellViewToGrid(
            new RectangleCellView(width, CELL_GRID_HEIGHT / states.length, states[i][j], properties), i, j);
        cellImages.get(i).add(new ImageView());
      }
    }
  }

  private void addCellViewToGrid(CellView cell1, int i, int j) {
    Node cell = cell1.getCell();
    cell.setId(String.format("cell%d,%d", i, j));
    GridPane.setConstraints(cell, j, i);
    myGrid.getChildren().add(cell);
    cells.get(i).add(cell1);
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

//  public void updateMyGrid(GameBoard gameBoard, Properties properties) {
//    String[][] states = gameBoard.getGameBoardStates();
//    for (int i = 0; i < cells.size(); i++) {
//      for (int j = 0; j < cells.get(i).size(); j++) {
//        cells.get(i).get(j).setColor(states[i][j], properties);
//        updateCellImage(properties, cellImages.get(i).get(j), states[i][j]);
//      }
//    }
//  }

  public void updateMyGrid(GameBoard gameBoard, Properties properties) {
    gameBoard.apply( (i, j, state) -> {
        cells.get(i).get(j).updateView(state, properties);
        updateCellImage(properties, cellImages.get(i).get(j), state);
        });
  }

  public void addImagesOverStates(Properties properties) {
    for (int i = 0; i < cells.size(); i++) {
      for (int j = 0; j < cells.get(i).size(); j++) {
        replaceCellWithImage((RectangleCellView) cells.get(i).get(j), cellImages.get(i).get(j));
        updateCellImage(properties, cellImages.get(i).get(j), ((RectangleCellView) cells.get(i)
            .get(j))
            .getState());
      }
    }
  }

  private void replaceCellWithImage(RectangleCellView cell, ImageView imageView) {
    imageView.setFitWidth(cell.getWidth());
    imageView.setFitHeight(cell.getHeight());
    myGrid.getChildren().remove(cell.getCell());
    int col = GridPane.getColumnIndex(cell.getCell());
    int row = GridPane.getRowIndex(cell.getCell());
    GridPane.setConstraints(imageView, col, row);
    myGrid.getChildren().add(imageView);
  }

  public void updateCellImage(Properties properties, ImageView cellImage, String state) {
    FileInputStream inputstream = null;
    try {
      // TODO: 2020-10-13 if state doesn't exist
      inputstream = new FileInputStream(properties.getProperty(state + "image"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    Image image = new Image(inputstream);
    cellImage.setImage(image);
  }
}
