package cellsociety.controller;

import exceptions.InvalidPropertiesFileException;
import cellsociety.model.GameBoard;
import cellsociety.model.games.Simulation;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class Controller {

  // TODO: 2020-10-18 single responbility principle
  private Properties properties = new Properties();
  private String propertiesFileName;
  private GameBoard board;
  private Simulation game;

  public Controller(String propertiesName) throws InvalidPropertiesFileException{
      propertiesFileName = propertiesName;
      setProperties(propertiesFileName);
      String gameType = properties.getProperty("GameType");
      String cellType = properties.getProperty("CellType");
      String neighborPolicy = properties.getProperty("NeighborPolicy");
      String edgePolicy = properties.getProperty("EdgePolicy");
      chooseSimulation(gameType, cellType, neighborPolicy, edgePolicy);
      board = game.getGameBoard();

  }

  private void chooseSimulation(String gameType, String cellType, String neighborPolicy, String edgePolicy) {
    Class operation;
    try {
      operation = Class.forName("cellsociety.model.games." + gameType);
      game = (Simulation) operation.getConstructor(String.class, String.class, String.class, String.class, String[].class)
          .newInstance(properties.getProperty("CSVSource"), cellType, neighborPolicy, edgePolicy,
              properties.getProperty("States").split(","));
    } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
      // TODO: 2020-10-12 handle this error
      //e.printStackTrace();

    }
  }

  public Properties getProperties() {
    return properties;
  }

  public void setProperties(String propertiesFileName) throws InvalidPropertiesFileException {
    try {
      properties
          .load(Controller.class.getClassLoader().getResourceAsStream(propertiesFileName));
    } catch (NullPointerException | IOException e) {

      //e.printStackTrace();
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
    String[] requiredProperties = {"Description", "Title", "States", "CellType", "GameType",
        "Author", "CSVSource", "NeighborPolicy", "EdgePolicy"};
    for (String property : requiredProperties) {
      if (properties.get(property) == null) {
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
