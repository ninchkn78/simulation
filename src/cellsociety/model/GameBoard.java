package cellsociety.model;

public class GameBoard {

  private int width;
  private int height;
  private Cell[][] gameBoardCells;
  private String[][] gameBoardStates;

  public GameBoard(int width, int height){
    this.width = width;
    this.height = height;
    this.gameBoardCells = new Cell[height][width];
    this.gameBoardStates = new String[height][width];
  }

  public GameBoard(Cell[][] initialState){
    this.width = initialState[0].length;
    this.height = initialState.length;
    this.gameBoardCells = initialState;
    setGameBoardStates(initialState);

  }

  public void setGameBoardStates(Cell[][] initialState){
    for (int i = 0; i < initialState.length; i++){
      for (int j = 0; j < initialState[0].length; j++){
        gameBoardStates[i][j] = gameBoardCells[i][j].getState();
      }
    }
  }


}
