package cellsociety.model.games;

import cellsociety.model.CountStateReader;
import cellsociety.model.GameBoard;
import cellsociety.model.RandomStateReader;
import cellsociety.model.Reader;
import cellsociety.model.SetStateReader;
import exceptions.InvalidCSVFormatException;
import java.util.ArrayList;

import java.util.List;

/**
 * The Simulation class acts as the superclass for all of the games. It initializes the reder, the game board,
 * validates the states of the csv file, and also modifies the back-end data when a cell in the front end
 * has been clicked.
 *
 * @author Franklin Wu
 */

public abstract class Simulation {

  private GameBoard board;
  private final String cellType;
  private final String neighborPolicy;
  private final String edgePolicy;
  private final String[] possibleStates;
  private int generation;

  /**
   * Creates an instance of the Simulation class
   * @param config
   * @param cellType
   * @param neighborPolicy
   * @param edgePolicy
   * @param possibleStates
   * @throws InvalidCSVFormatException
   */
  public Simulation(String config, String cellType, String neighborPolicy, String edgePolicy,
      String[] possibleStates) throws InvalidCSVFormatException {
    String[] configAndType = config.split(",");
    Reader stateReader = chooseReader(configAndType[1]);
    this.board = new GameBoard(stateReader.getStatesFromFile(configAndType[0]), cellType,
        neighborPolicy, edgePolicy,
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

  /**
   * Updates the cell at row, col according to the game rules and sets the updated cell in
   * the given gameboard.
   * @param gameBoard
   * @param row
   * @param col
   */
  public abstract void updateCell(GameBoard gameBoard, int row, int col);

  /**
   * Returns the cell counts of each state
   * @return
   */
  public List<Integer> getGraphCounts() {
    List<Integer> stateCounts = new ArrayList<>();
    for (String state : possibleStates) {
      stateCounts.add(board.getAllPositionsOfCellState(state).size());
    }
    return stateCounts;
  }

  /**
   * When a cell is clicked, its state changes cyclically through the possible states of the cell type
   * @param i
   * @param j
   */
  public void cylceStateOnClicked(int i, int j) {
    String[][] newGameBoardStates = board.getGameBoardStates();
    String currentState = newGameBoardStates[i][j];
    String newState = Integer
        .toString((Integer.parseInt(currentState) + 1) % possibleStates.length);
    newGameBoardStates[i][j] = newState;
    this.board = new GameBoard(newGameBoardStates, cellType, neighborPolicy, edgePolicy,
        possibleStates);
  }

  /**
   * Calculates the next generation of the simulation
   */
  public void nextGen() {
    GameBoard nextBoard = new GameBoard(getGameBoard().getWidth(), getGameBoard().getHeight(),
        cellType, neighborPolicy, edgePolicy);
    for (int i = 0; i < getGameBoard().getHeight(); i++) {
      for (int j = 0; j < getGameBoard().getWidth(); j++) {
        updateCell(nextBoard, i, j);
      }
    }
    board = nextBoard;
  }

  private Reader chooseReader(String configType) {
    if (configType.equals("random")) {
      return new RandomStateReader();
    } else if (configType.equals("count")) {
      return new CountStateReader();
    } else {
      return new SetStateReader();
    }
  }
}
