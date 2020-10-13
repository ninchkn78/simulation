package cellsociety.controller;

import cellsociety.model.GameBoard;
import cellsociety.model.games.*;
import cellsociety.model.games.Simulation;
import cellsociety.view.CellView;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class Controller {

  private GameBoard board;
  private Simulation game;
  private Properties properties = new Properties();

  public Properties getProperties() {
    return properties;
  }

  public void setProperties(String propertiesFileName) {
    try {
      properties.load(CellView.class.getClassLoader().getResourceAsStream(propertiesFileName));
    } catch (IOException e) {
      // TODO: 2020-10-12 better error handling  
      e.printStackTrace();
    }
  }


  public Controller(String propertiesFileName) {
    setProperties(propertiesFileName);
    String gameType = properties.getProperty("Type");
    Class operation;
    try {
      operation = Class.forName("cellsociety.model.games." + gameType);
      game = (Simulation) operation.getConstructor(String.class).newInstance(properties.getProperty("CSVSource"));
    } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
      // TODO: 2020-10-12 handle this error  
      e.printStackTrace();
    }

    //game = new ConwayGameOfLife(prop.getProperty("CSVSource")); //TODO: SimulationChooser class
    board = game.getGameBoard();
  }

  /**
   * Update the gameboard in the backend and return to front end
   */
  public void updateView(){
    game.nextGen();
    board = game.getGameBoard();
  }

  public GameBoard getGameBoard() {
      return board;
  }
}
