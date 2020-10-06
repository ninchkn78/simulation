package cellsociety.model;

public class GameBoard {

  private final int width;
  private final int height;
  private ConwayCell[][] gameBoardCells;
  private String[][] gameBoardStates;

  public GameBoard(int width, int height) {
    this.width = width;
    this.height = height;
    this.gameBoardCells = new ConwayCell[height][width];
    initializeGameBoardCells();
    this.gameBoardStates = new String[height][width];
    setGameBoardStates(gameBoardCells);
  }

  public GameBoard(ConwayCell[][] initialCellConfig) {
    this.width = initialCellConfig[0].length;
    this.height = initialCellConfig.length;
    this.gameBoardCells = initialCellConfig;
    this.gameBoardStates = new String[height][width];
    setGameBoardStates(initialCellConfig);
  }

  private void setGameBoardStates(Cell[][] initialState){
    for (int i = 0; i < initialState.length; i++){
      for (int j = 0; j < initialState[0].length; j++){
        gameBoardStates[i][j] = gameBoardCells[i][j].getState();
      }
    }
  }

  public void clear(){
    for (int i = 0; i < height; i++){
      for (int j = 0; j < width; j++){
        gameBoardCells[i][j].setState(ConwayCell.DEAD);
        gameBoardStates[i][j] = ConwayCell.DEAD;

      }
    }
  }

  public ConwayCell getCell(int row, int col) {
    return gameBoardCells[row][col];
  }

  public String getState(int row, int col) {
    return gameBoardStates[row][col];
  }

  public boolean isValidLocation(int row, int col) {
    return (row >= 0 && col >= 0) && (row < height && col < width);
  }

  public int countLivingNeighbors(int currentRow, int currentColumn) {
    int aliveCount = 0;
    for (int i = currentRow - 1; i <= currentRow + 1; i++){
      for (int j = currentColumn - 1; j <= currentColumn + 1; j++){
        if (isValidLocation(i,j) && gameBoardCells[i][j].isAlive()){ //TODO: make this not ugly af
          aliveCount++;
        }
      }
    }
    if (gameBoardCells[currentRow][currentColumn].isAlive()) {
      aliveCount--;
    }
    return aliveCount;
  }

  public void setPiece(int row, int col, String state) {
    gameBoardCells[row][col] = new ConwayCell(state);
    gameBoardStates[row][col] = state;
  }

  public void toggleState(int x, int y) { //TODO: make this cleaner
    if(gameBoardCells[x][y].getState().equals(ConwayCell.DEAD)) {
      gameBoardCells[x][y].setState(ConwayCell.ALIVE);
      gameBoardStates[x][y] = ConwayCell.ALIVE;
    }
    else{
      gameBoardCells[x][y].setState(ConwayCell.DEAD);
      gameBoardStates[x][y] = ConwayCell.DEAD;
    }
  }

  public int getWidth(){
    return width;
  }

  public int getHeight() {
    return height;
  }

  public String[][] getGameBoardStates() {
    return gameBoardStates;
  }



  public void setCellConfiguration(String[][] stateConfig){
    gameBoardStates = stateConfig;
    for (int i = 0; i < height; i++){
      for (int j = 0; j < width; j++){
        gameBoardCells[i][j] = new ConwayCell(stateConfig[i][j]);
      }
    }
  }


  public void initializeGameBoardCells() {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        ConwayCell cell = new ConwayCell();
        if (j == 5 && i < 6 && i > 2) {
          cell.toggleState();
        }
        gameBoardCells[i][j] = cell;
      }
    }
  }

  }
