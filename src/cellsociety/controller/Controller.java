package cellsociety.controller;

import cellsociety.model.ConwayGameOfLife;
import cellsociety.model.Simulation;

public class Controller {

  private String[][] states;
  private Simulation game;

  public Controller(String config){
    game = new ConwayGameOfLife(config); //TODO: SimulationChooser class
    states = game.getGameBoard().getGameBoardStates();
  }

  /**
   * Update the gameboard in the backend and return to front end
   */
  public String[][] updateView(){
    game.nextGen();
    states = game.getGameBoard().getGameBoardStates();
    return states;
  }

}
