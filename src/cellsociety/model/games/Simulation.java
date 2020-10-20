package cellsociety.model.games;

import cellsociety.model.CountStateReader;
import cellsociety.model.GameBoard;
import cellsociety.model.RandomStateReader;
import cellsociety.model.Reader;
import cellsociety.model.SetStateReader;
import exceptions.InvalidCSVFormatException;


public abstract class Simulation {

  private GameBoard board;
  private String cellType;
  private String neighborPolicy;
  private String edgePolicy;


  private String[] possibleStates;
  private int generation;

  public Simulation(String config, String cellType, String neighborPolicy, String edgePolicy, String[] possibleStates) throws InvalidCSVFormatException {
    String[] configAndType = config.split(",");
    Reader stateReader = chooseReader(configAndType[1]);
    this.board = new GameBoard(stateReader.getStatesFromFile(configAndType[0]), cellType, neighborPolicy, edgePolicy,
        possibleStates);
    this.cellType = cellType;
    this.neighborPolicy = neighborPolicy;
    this.edgePolicy = edgePolicy;
    this.possibleStates = possibleStates;
    this.generation = 1;
  }

  public GameBoard getGameBoard() {
    return board;
  }

  public String[] getPossibleStates() {
    return possibleStates;
  }

  public String getNeighborPolicy() {
    return neighborPolicy;
  }

  public String getEdgePolicy() {
    return edgePolicy;
  }


  public void incrementGeneration() {
    generation++;
  }


  public abstract void updateCell(GameBoard gameBoard, int row, int col);

  //public abstract void setOnClicked();

  public void nextGen() {
    GameBoard nextBoard = new GameBoard(getGameBoard().getWidth(), getGameBoard().getHeight(), cellType, neighborPolicy, edgePolicy);
    for (int i = 0; i < getGameBoard().getHeight(); i++) {
      for (int j = 0; j < getGameBoard().getWidth(); j++) {
        updateCell(nextBoard, i, j);
      }
    }
    board = nextBoard;
  }



  private Reader chooseReader(String configType) {
    // TODO: 2020-10-18  maybe do a reflection here if I'm feeling it
    if (configType.equals("random")) {
      return new RandomStateReader();
    } else if (configType.equals("count")) {
      return new CountStateReader();
    } else {
      return new SetStateReader();
    }
  }

}
