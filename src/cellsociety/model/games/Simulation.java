package cellsociety.model.games;


import cellsociety.model.GameBoard;
import cellsociety.model.Reader;

public abstract class Simulation {

  private GameBoard board;
  private int generation;

  public Simulation(String config) {
    Reader reader = new Reader();
    board = new GameBoard(reader.readFile(config));
    generation = 1;
  }

  public GameBoard getGameBoard(){
    return board;
  }

  public void setGameBoard(GameBoard updatedBoard){
    board = updatedBoard;
  }

  public void incrementGeneration(){
    generation++;
  }


  public abstract void updateCell(GameBoard gameBoard, int row, int col);


  public abstract void nextGen();






}
