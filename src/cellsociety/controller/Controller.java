package cellsociety.controller;

import cellsociety.model.GameBoard;
import cellsociety.model.games.Simulation;
import cellsociety.view.RectangleCellView;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class Controller {

  private GameBoard board;
  private Simulation game;
  private final Properties properties = new Properties();
  private final String propertiesFileName;

  public Controller(String propertiesName) {
    propertiesFileName = propertiesName;
    setProperties(propertiesFileName);
    String gameType = properties.getProperty("GameType");
    String cellType = properties.getProperty("CellType");
    System.out.println(gameType);
    Class operation;
    try {
      operation = Class.forName("cellsociety.model.games." + gameType);
      game = (Simulation) operation.getConstructor(String.class, String.class)
          .newInstance(properties.getProperty("CSVSource"), cellType);
    } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
      // TODO: 2020-10-12 handle this error
      System.out.println(e.getCause());

      e.printStackTrace();
    }
    board = game.getGameBoard();
  }

  public Properties getProperties() {
    return properties;
  }

  public void setProperties(String propertiesFileName) {
    try {
      properties
          .load(RectangleCellView.class.getClassLoader().getResourceAsStream(propertiesFileName));
    } catch (IOException e) {
      // TODO: 2020-10-12 better error handling  
      e.printStackTrace();
    }
  }

  public void overWriteProperties() {
    try {
      properties.store(new FileOutputStream("resources/" + propertiesFileName), null);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Update the gameboard in the backend and return to front end
   */
  public void updateView() {
    game.nextGen();
    board = game.getGameBoard();
  }

  public GameBoard getGameBoard() {
    return board;
  }
}
