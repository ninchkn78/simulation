package cellsociety.model.games;


import cellsociety.model.GameBoard;
import cellsociety.model.SetStateReader;

public abstract class Simulation {

  private GameBoard board;
  private int generation;

  public Simulation(String config) {
    SetStateReader setStateReader = new SetStateReader();
    board = new GameBoard(setStateReader.getStatesFromFile(config));
    generation = 1;
  }

  public GameBoard getGameBoard() {
    return board;
  }


  public void incrementGeneration() {
    generation++;
  }


  public abstract void updateCell(GameBoard gameBoard, int row, int col);


  public void nextGen() {
    GameBoard nextBoard = new GameBoard(getGameBoard().getWidth(), getGameBoard().getHeight());
    for (int i = 0; i < getGameBoard().getHeight(); i++) {
      for (int j = 0; j < getGameBoard().getWidth(); j++) {
        updateCell(nextBoard, i, j);
      }
    }
    incrementGeneration();
    board = nextBoard;
  }


}
