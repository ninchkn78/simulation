package cellsociety.controller;

import cellsociety.model.GameBoard;
import cellsociety.model.games.Simulation;
import exceptions.InvalidPropertiesFileException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class Controller {

  // TODO: 2020-10-18 single responbility principle
  private final Properties properties = new Properties();
  private final String propertiesFileName;
  private GameBoard board;
  private Simulation game;

  public Controller(String propertiesName)
      throws InvalidPropertiesFileException, InvocationTargetException {
    propertiesFileName = propertiesName;
    setProperties(propertiesFileName);
    String gameType = properties.getProperty("GameType");
    String cellType = properties.getProperty("CellType");
    String neighborPolicy = properties.getProperty("NeighborPolicy");
    String edgePolicy = properties.getProperty("EdgePolicy");
    chooseSimulation(gameType, cellType, neighborPolicy, edgePolicy);
    board = game.getGameBoard();
  }

  private void chooseSimulation(String gameType, String cellType, String neighborPolicy,
      String edgePolicy)
      throws InvocationTargetException {
    Class operation;
    try {
      operation = Class.forName("cellsociety.model.games." + gameType);
      game = (Simulation) operation
          .getConstructor(String.class, String.class, String.class, String.class, String[].class)
          .newInstance(properties.getProperty("CSVSource"), cellType, neighborPolicy, edgePolicy,
              properties.getProperty("States").split(","));
    } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {

    }
  }

  public Properties getProperties() {
    return properties;
  }

  public void setProperties(String propertiesFileName) throws InvalidPropertiesFileException {
    try {
      properties
          .load(Controller.class.getClassLoader()
              .getResourceAsStream("Properties_Files/" + propertiesFileName));
    } catch (NullPointerException | IOException e) {
      // TODO: 2020-10-19 ?
      System.out.println(propertiesFileName);
      throw new InvalidPropertiesFileException(
          "Please choose a file from the properties files folder");
    }
    validatePropertiesFile();
  }

  public void handleCellClick(int row, int col) {
    game.cylceStateOnClicked(row, col);
    board = game.getGameBoard();
  }

  public void overWriteProperties() {
    try {
      properties.store(new FileOutputStream("resources/" + propertiesFileName), null);
    } catch (IOException e) {
      throw new InvalidPropertiesFileException("Properties File Not Found");
    }
  }

  private void validatePropertiesFile() throws InvalidPropertiesFileException {
    if (!propertiesFileName.substring(propertiesFileName.indexOf('.')).equals(".properties")) {
      throw new InvalidPropertiesFileException("Not a properties file");
    }
    String[] requiredProperties = {"Description", "Title", "States", "CellType", "GameType",
        "Author", "CSVSource", "NeighborPolicy", "EdgePolicy"};
    for (String property : requiredProperties) {
      if (properties.get(property) == null) {
        // TODO: 2020-10-19 error messages in resource file
        throw new InvalidPropertiesFileException("Missing Resource Key");
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
