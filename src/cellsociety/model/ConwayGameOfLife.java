package cellsociety.model;


public class ConwayGameOfLife {

  private GameBoard board;
  private int generation;

  public ConwayGameOfLife(int width, int height) {
    this.board = new GameBoard(width, height);
    this.generation = 1; //TODO: fix magic number
  }

  public ConwayGameOfLife(GameBoard board) {
    this.board = board;
    this.generation = 1; //TODO: fix magic number
  }

  public boolean aliveNextGen(int row, int col) {
    ConwayCell currentCell = board.getCell(row, col);
    if (currentCell.isAlive()) {
      return board.countLivingNeighbors(row, col) > 1 && board.countLivingNeighbors(row, col) < 4;
    }
    return board.countLivingNeighbors(row, col) == 3;
  }

  public void nextGen() {
    System.out.println("AH SHIT OK");
    GameBoard nextBoard = new GameBoard(board.getWidth(), board.getHeight());
    for (int i = 0; i < board.getHeight(); i++) {
      for (int j = 0; j < board.getWidth(); j++) {
        if (aliveNextGen(i, j)) {
          nextBoard.setPiece(i, j, ConwayCell.ALIVE);
        } else {
          nextBoard.setPiece(i, j, ConwayCell.DEAD);
        }
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

  public GameBoard getGameBoard() {
    return board;
  }

}
