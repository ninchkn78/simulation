package cellsociety.model;


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


  public abstract void updateCell(GameBoard gameBoard, int row, int col);


  public void nextGen() { //TODO: put in abstract class
    GameBoard nextBoard = new GameBoard(board.getWidth(), board.getHeight());
    for (int i = 0; i < board.getHeight(); i++) {
      for (int j = 0; j < board.getWidth(); j++) {
        updateCell(nextBoard, i,j);
      }
    }
    this.generation++;
    board = nextBoard;

  }





}
