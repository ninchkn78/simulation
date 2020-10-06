package cellsociety.model;

public class GameBoard {

  private int width;
  private int height;
  private ConwayCell[][] gameBoardCells;
  private String[][] gameBoardStates;

  public GameBoard(int width, int height){
    this.width = width;
    this.height = height;
    this.gameBoardCells = new ConwayCell[height][width];
    initializeGameBoardCells();
    this.gameBoardStates = new String[height][width];
    setGameBoardStates(gameBoardCells);
  }

  public GameBoard(ConwayCell[][] initialCellConfig){
    this.width = initialCellConfig[0].length;
    this.height = initialCellConfig.length;
    this.gameBoardCells = initialCellConfig;
    this.gameBoardStates = new String[height][width];
    setGameBoardStates(initialCellConfig);
  }

  public void setGameBoardStates(Cell[][] initialState){
    for (int i = 0; i < initialState.length; i++){
      for (int j = 0; j < initialState[0].length; j++){
        gameBoardStates[i][j] = gameBoardCells[i][j].getState();
      }
    }
  }

  public void clear(){
    this.gameBoardCells = new ConwayCell[height][width];
    this.gameBoardStates = new String[height][width];
  }

  public ConwayCell getCell(int row, int col){
    return gameBoardCells[row][col];
  }

  public String getState(int row, int col){
    return gameBoardStates[row][col];
  }

  public boolean isValidLocation(int row, int col){
    return (row >= 0 && col >= 0) && (row < height && col < width);
  }

  public int countLivingNeighbors(int currentRow, int currentColumn){
    int aliveCount = 0;
    for (int i = currentRow - 1; i <= currentRow + 1; i++){
      for (int j = currentColumn - 1; j <= currentColumn + 1; j++){
        if (isValidLocation(i,j) && gameBoardCells[i][j].isAlive() && i != currentRow && j != currentColumn){ //TODO: make this not ugly af
          aliveCount++;
        }
      }
    }
    return aliveCount;
  }

  public void setPiece(int row, int col, String state){
    gameBoardCells[row][col] = new ConwayCell(state);
    gameBoardStates[row][col] = state;
  }

  public int getWidth(){
    return width;
  }

  public int getHeight(){
    return height;
  }


  public String[][] getGameBoardStates(){
    return gameBoardStates;
  }

  public void initializeGameBoardCells(){
    for (int i = 0; i < height; i ++){
      for (int j = 0; j < width; j++){
        ConwayCell cell = new ConwayCell();
        cell.toggleState();
        gameBoardCells[i][j] = cell;
      }
    }
  }


}
