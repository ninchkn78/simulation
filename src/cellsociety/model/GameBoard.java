package cellsociety.model;

public class GameBoard {

  private final int width;
  private final int height;
  private Cell[][] gameBoardCells;
  private String[][] gameBoardStates;

  public GameBoard(int width, int height) {
    this.width = width;
    this.height = height;
    this.gameBoardCells = new Cell[height][width];
    clear();
    this.gameBoardStates = new String[height][width];
    setGameBoardStates(gameBoardCells);
  }

  public GameBoard(String[][] initialStateConfig) {
    this.width = initialStateConfig[0].length;
    this.height = initialStateConfig.length;
    this.gameBoardCells = createCellConfiguration(initialStateConfig);
    this.gameBoardStates = initialStateConfig;
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
        gameBoardCells[i][j] = new ConwayCell();
        gameBoardStates[i][j] = ConwayCell.DEAD;
      }
    }
  }

  public Cell getCell(int row, int col) {
    return gameBoardCells[row][col];
  }

  public String getState(int row, int col) {
    return gameBoardStates[row][col];
  }

  public boolean isValidLocation(int row, int col) {
    return (row >= 0 && col >= 0) && (row < height && col < width);
  }



  public void setPiece(int row, int col, String state) {
    gameBoardCells[row][col] = new ConwayCell(state); //TODO: update existing cell
    gameBoardStates[row][col] = state;
  }

  public void toggleState(int x, int y) { //TODO: make this cleaner
    if(gameBoardCells[x][y].getState().equals(ConwayCell.DEAD)) {
      gameBoardCells[x][y].setState(ConwayCell.ALIVE);
      gameBoardStates[x][y] = ConwayCell.ALIVE; // TODO: "update instance variable - more connected"
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




  public Cell[][] createCellConfiguration(String[][] stateConfig){ //TODO: make this work for all cell types
    Cell[][] cellConfig = new Cell[stateConfig.length][stateConfig[0].length];
    for (int i = 0; i < height; i++){
      for (int j = 0; j < width; j++){
        cellConfig[i][j] = new ConwayCell(stateConfig[i][j]);
      }
    }
    return cellConfig;
  }



  public void setCellConfiguration(String[][] stateConfig){
    gameBoardStates = stateConfig;
    for (int i = 0; i < height; i++){
      for (int j = 0; j < width; j++){
        gameBoardCells[i][j] = new ConwayCell(stateConfig[i][j]);
      }
    }
  }

}

