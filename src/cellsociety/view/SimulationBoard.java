package cellsociety.view;

import cellsociety.model.GameBoard;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class SimulationBoard {

  // TODO: 2020-10-10 make this CSS
  public static final double CELL_GRID_WIDTH = 600;
  public static final double CELL_GRID_HEIGHT = 350;

  private final GridPane myGrid = new GridPane();
  private final List<List<CellView>> cells = new ArrayList<>();
  private GameBoard gameBoard;
  private Properties properties;

  public SimulationBoard(Group root) {
    myGrid.setLayoutX(75);
    myGrid.setLayoutY(75);
    myGrid.setGridLinesVisible(true);
    root.getChildren().add(myGrid);
  }

  public void setUpNewSimulation(GameBoard gameBoard, Properties properties) {
    this.gameBoard = gameBoard;
    this.properties = properties;
    setGridType("Rectangle");
  }

  //works for non square 2D arrays
  // TODO: 2020-10-04 ask about X position for tests
  public void setGridType(String cellType) {
    clear();
    String[][] states = gameBoard.getGameBoardStates();
    double width = CELL_GRID_WIDTH / maxRowLength(states);
    for (int i = 0; i < states.length; i++) {
      cells.add(new ArrayList<>());
      for (int j = 0; j < states[i].length; j++) {
        addCellToGrid(
            chooseCellType(cellType, width, CELL_GRID_HEIGHT / states.length, states[i][j],
                properties), i, j);
      }
    }
  }

  private void clear() {
    myGrid.getChildren().clear();
    cells.clear();
  }

  private void addCellToGrid(CellView cellView, int i, int j) {
    Node cell = cellView.getCell();
    cell.setId(String.format("cell%d,%d", i, j));
    GridPane.setConstraints(cell, j, i);
    myGrid.getChildren().add(cell);
    cells.get(i).add(cellView);
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

  public void updateMyGrid(GameBoard gameBoard, Properties properties) {
    gameBoard.apply((i, j, state) -> cells.get(i).get(j).updateView(state, properties));
  }


  private CellView chooseCellType(String cellType, double width, double height, String state,
      Properties properties) {
    Class operation;
    CellView cell = null;
    try {
      operation = Class.forName("cellsociety.view." + cellType + "CellView");
      cell = (CellView) operation
          .getConstructor(double.class, double.class, String.class, Properties.class)
          .newInstance(width, height, state, properties);
    } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
      // TODO: 2020-10-12 handle this error
      e.printStackTrace();
    }
    return cell;
  }
}
