package cellsociety.controller;

import cellsociety.model.GameBoard;
import cellsociety.model.games.*;
import cellsociety.model.games.Simulation;
import cellsociety.view.CellView;
import java.io.IOException;
import java.util.Properties;

public class Controller {

  private GameBoard board;
  private Simulation game;

  public Controller(String propertiesFileName) {
    Properties prop = new Properties();
    try {
      prop.load(CellView.class.getClassLoader().getResourceAsStream(propertiesFileName));
    } catch (IOException e) {
      e.printStackTrace();
    }
    game = new ConwayGameOfLife(prop.getProperty("CSVSource")); //TODO: SimulationChooser class
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
