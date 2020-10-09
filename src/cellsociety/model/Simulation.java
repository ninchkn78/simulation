package cellsociety.model;


public abstract class Simulation {

  private GameBoard board;
  private int generation;

  public Simulation(String config) {
    Reader reader = new Reader();
    this.board = new GameBoard(reader.readFile(config));
    this.generation = 1;
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


  public void reset() {
    this.board.clear();
    this.generation = 0;
  }

  public void handleMouseInput(double x, double y) {
//    int scaledHeight = boardPanel.getHeight()/g.getHeight();
//    int scaledWidth =  boardPanel.getWidth()/g.getWidth();
//
//    int x = e.getX()/scaledWidth;
//    int y = e.getY()/scaledHeight;
//    g.toggleState(x, y);
  }

//  public GameBoard getGameBoard() {
//    return board;
//  }

}
