package cellsociety.view;

import cellsociety.model.GameBoard;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class ConwaySimulationBoard extends SimulationBoard {

  // TODO: 2020-10-10 make this CSS
  public static final double CELL_GRID_WIDTH = 600;
  public static final double CELL_GRID_HEIGHT = 350;

  private final GridPane myGrid = new GridPane();
  private final List<List<CellView>> cells = new ArrayList<>();


  public ConwaySimulationBoard(Group root, GameBoard gameBoard, String propertiesFileName) {
    myGrid.setLayoutX(75);
    myGrid.setLayoutY(50);
    myGrid.setGridLinesVisible(true);
    root.getChildren().add(myGrid);
    initializeMyGrid(gameBoard,propertiesFileName);
  }

  //works for non square 2D arrays
  // TODO: 2020-10-04 ask about X position for tests
  // TODO: 2020-10-05  don't make new rectangles every time
  private void initializeMyGrid(GameBoard gameBoard, String propertiesFileName) {
    String[][] states = gameBoard.getGameBoardStates();
    cells.clear();
    double width = CELL_GRID_WIDTH / maxRowLength(states);
    for (int i = 0; i < states.length; i++) {
      cells.add(new ArrayList<>());
      for (int j = 0; j < states[i].length; j++) {
        CellView cell = new CellView(width, CELL_GRID_HEIGHT / states.length, states[i][j], propertiesFileName);
        cell.setId(String.format("cell%d,%d", i, j));
        GridPane.setConstraints(cell, j, i);
        myGrid.getChildren().add(cell);
        cells.get(i).add(cell);
      }
    }
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

  public void updateMyGrid(GameBoard gameBoard, String propertiesFileName) {
    String[][] states = gameBoard.getGameBoardStates();
    for(int i = 0; i < cells.size(); i++){
      for(int j = 0; j < cells.get(i).size(); j++){
        cells.get(i).get(j).setColor(states[i][j],propertiesFileName);
      }
    }
  }
}
