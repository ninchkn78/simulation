//package cellsociety.view;
//
//
//import cellsociety.model.ConwayGameOfLife;
//import java.util.Arrays;
//import javafx.animation.KeyFrame;
//import javafx.animation.Timeline;
//import javafx.application.Application;
//import javafx.scene.Group;
//import javafx.scene.Scene;
//import javafx.scene.paint.Color;
//import javafx.scene.paint.Paint;
//import javafx.stage.Stage;
//import javafx.stage.Window;
//import javafx.util.Duration;
//
//
//public class ConwayDisplay extends Display{
//
//  public static final String TITLE = "Simulation";
//  public static final int WIDTH = 800;
//  public static final int HEIGHT = 600;
//  public static final int FRAMES_PER_SECOND = 60;
//  public static final double SECOND_DELAY = 100 / FRAMES_PER_SECOND;
//  public static final Paint BACKGROUND = Color.AZURE;
//  public static final int GAME_WIDTH = 13;
//  public static final int GAME_HEIGHT = 11;
//
//
//  private final Group myRoot = new Group();
//  //private final ConwayGameOfLife game = new ConwayGameOfLife(GAME_WIDTH, GAME_HEIGHT);
//  private final ConwaySimulationBoard myBoard = new ConwaySimulationBoard(myRoot);
//  ConwayGameOfLife game = new ConwayGameOfLife("GAME_CSVS/new0.csv");
//  private final ButtonSetup myButtonSetup = new ButtonSetup(this);
//  //private Controller controller = new Controller();
//  private String[][] tempState;
//  private Stage myStage;
//  private Timeline animation;
//
//  /**
//   * Start the program.
//   */
//  public static void main(String[] args) {
//    launch(args);
//  }
//
//  @Override
//  public void start(Stage stage) {
//    // attach scene to the stage and display it
//    myStage = stage;
//    Scene myScene = setupScene();
//    stage.setScene(myScene);
//    stage.setTitle(TITLE);
//    stage.show();
//    // attach "game loop" to timeline to play it (basically just calling step() method repeatedly forever)
//    //startStepMethod();
//  }
//
//  public void startStepMethod() {
//    KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY));
//    animation = new Timeline();
//    animation.setCycleCount(Timeline.INDEFINITE);
//    animation.getKeyFrames().add(frame);
//    animation.play();
//  }
//
//  // TODO: 2020-10-04 some way to set up the scene based on a level file for testing different levels?
//  Scene setupScene() {
//    Scene scene = new Scene(myRoot, WIDTH, HEIGHT, BACKGROUND);
//    // respond to input
//    //scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
//    tempState = game.getGameBoard().getGameBoardStates();
//    myButtonSetup.addButtons(myRoot);
//    myButtonSetup.checkButtonStatus(tempState);
//    //nextGen(tempState);
//
//    return scene;
//  }
//
//  // TODO: 2020-10-04 this 100% needs to change, but just doing this for now to be able to update?
//  void nextGen(String[][] states) {
//    myBoard.updateMyGrid(states);
//  }
//
//  // TODO: 2020-10-04 this 100% needs to change, but just doing this for now to be able to update?
//  void step(double elapsedTime) {
////    tempState = new String[][]{{"1","0","1","1","0","1", "1", "0", "1", "0", "1", "1", "1"},      //LIKELY THIS WILL BE SOME METHOD CALL FROM BACKEND
////            {"1","0","0","1","0","1", "1", "0", "1", "0", "1", "1", "0"},
////            {"1","0","1","0","0","1", "1", "0", "1", "0", "1", "1", "1"},
////            {"1","1","1","1","0","1", "1", "0", "1", "0", "1", "0", "1"},
////            {"1","0","1","1","0","1", "1", "0", "0", "0", "1", "1", "1"},
////            {"1","0","0","1","0","1", "0", "0", "1", "0", "1", "1", "0"},
////            {"0","0","1","1","0","0", "1", "1", "1", "1", "0", "1", "0"},
////            {"1","0","1","1","0","1", "1", "0", "1", "0", "1", "1", "1"},
////            {"1","0","1","1","0","1", "1", "0", "1", "1", "0", "1", "1"},
////            {"1","0","1","1","0","1", "1", "0", "1", "0", "1", "1", "1"},
////            {"1","0","1","1","0","1", "1", "0", "1", "1", "1", "1", "1"}};
//
//    game.nextGen();
//    System.out.println(1);
//    tempState = game.getGameBoard().getGameBoardStates();
//    nextGen(tempState);
//
//
//  }
//
//
//  public void handleMouseInput(double x, double y){
//    int scaledHeight = WIDTH/game.getGameBoard().getHeight();
//    int scaledWidth =  HEIGHT/game.getGameBoard().getWidth();
//
//    int scaledX = (int)(x/scaledWidth);
//    int scaledY = (int)(y/scaledHeight);
//    game.getGameBoard().toggleState(scaledX,scaledY);
//    System.out.println(game.getGameBoard().getCell(scaledX,scaledY).getState());
//
//    System.out.println(scaledX + " " + scaledY);
//    System.out.println(Arrays.deepToString(game.getGameBoard().getGameBoardStates()));
//  }
//
//  public Window getStage() {
//    return myStage;
//  }
//
//  public void pauseGame(){
//      animation.pause();
//  }
//
//
//}
//
//
//
