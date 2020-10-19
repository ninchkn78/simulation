package cellsociety.controller;

import cellsociety.model.GameBoard;
import Exceptions.InvalidPropertiesFileException;
import cellsociety.model.games.Simulation;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Properties;
import javafx.scene.control.Button;

public class Controller {

  // TODO: 2020-10-18 single responbility principle
  private final Properties properties = new Properties();
  private final String propertiesFileName;
  private GameBoard board;
  private Simulation game;

  // TODO: 2020-10-18  fix this shit
  public Controller(String propertiesName) {
    propertiesFileName = propertiesName;
    setProperties(propertiesFileName);
    String gameType = properties.getProperty("GameType");
    String cellType = properties.getProperty("CellType");
    chooseSimulation(gameType, cellType);
    board = game.getGameBoard();
  }

  private void chooseSimulation(String gameType, String cellType) {
    Class operation;
    try {
      operation = Class.forName("cellsociety.model.games." + gameType);
      game = (Simulation) operation.getConstructor(String.class, String.class, String[].class)
          .newInstance(properties.getProperty("CSVSource"), cellType,properties.getProperty("States").split(","));
    } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
      // TODO: 2020-10-12 handle this error
      e.printStackTrace();

    }
  }

  public Properties getProperties() {
    return properties;
  }

  public void setProperties(String propertiesFileName) {
    try {
      properties
          .load(Controller.class.getClassLoader().getResourceAsStream(propertiesFileName));
    } catch (IOException e) {
      // TODO: 2020-10-12 better error handling  
      e.printStackTrace();
    }
    validatePropertiesFile();
  }

  public void overWriteProperties() {
    try {
      properties.store(new FileOutputStream("resources/" + propertiesFileName), null);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void validatePropertiesFile() throws InvalidPropertiesFileException {
    String[] requiredProperties = {"Description", "Title", "States","CellType","GameType","Author","CSVSource"};
    for(String property: requiredProperties){
      if(properties.get(property) == null ){
        throw new InvalidPropertiesFileException("This will be replaced anyways");
      }
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
