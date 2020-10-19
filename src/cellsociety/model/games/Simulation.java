package cellsociety.model.games;

import cellsociety.model.CountStateReader;
import cellsociety.model.GameBoard;
import cellsociety.model.RandomStateReader;
import cellsociety.model.Reader;
import cellsociety.model.SetStateReader;
import java.util.Set;


public abstract class Simulation {

  private GameBoard board;
  private String cellType;


  public Simulation(String config, String cellType, String[] possibleStates) {
    String[] configAndType = config.split(",");
    Reader stateReader = chooseReader(configAndType[1]);
    this.board = new GameBoard(stateReader.getStatesFromFile(configAndType[0]), cellType, possibleStates);
    this.cellType = cellType;
  }

  public GameBoard getGameBoard() {
    return board;
  }

  public abstract void updateCell(GameBoard gameBoard, int row, int col);

  //public abstract void setOnClicked();

  public void nextGen() {
    GameBoard nextBoard = new GameBoard(getGameBoard().getWidth(), getGameBoard().getHeight(), cellType);
    for (int i = 0; i < getGameBoard().getHeight(); i++) {
      for (int j = 0; j < getGameBoard().getWidth(); j++) {
        updateCell(nextBoard, i, j);
      }
    }
    board = nextBoard;
  }

  public void validateStates(String[] states){

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
