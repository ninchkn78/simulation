package cellsociety.view;

import cellsociety.controller.Controller;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

class ButtonSetupTest extends DukeApplicationTest {
    Scene myScene;
    private final Display myDisplay = new Display();


    @Override
    public void start(Stage stage) {
        // create game's scene with all shapes in their initial positions and show i
        myScene = myDisplay.setupScene();
        stage.setScene(myScene);
        stage.show();
        javafxRun(() -> myDisplay.setController(new Controller("TestConway.properties")));
    }

    @Test
    public void testButtonGeneration(){

        lookup("#loadFileButton").queryButton();
        lookup("#runButton").queryButton();
        lookup("#saveFileButton").queryButton();
        lookup("#pauseButton").queryButton();
        lookup("#chooseImageButton").queryButton();
    }

    @Test
    public void testRunButton(){
        Button runButton = lookup("#runButton").queryButton();

        Rectangle cell1 = lookup("#cell1,0").query();
        Rectangle cell2 = lookup("#cell0,1").query();
        Assertions.assertEquals(Color.BLUE, cell1.getFill());
        Assertions.assertEquals(Color.RED, cell2.getFill());
        runButton.fire();
        //TODO = don't use the sleep = how do we fix this

        //javafxRun(() -> myDisplay.step(myDisplay.getAnimationSpeed()));
        sleep(1100);
        Assertions.assertEquals(Color.RED, cell1.getFill());
        Assertions.assertEquals(Color.BLUE, cell2.getFill());

    }
    @Test

    public void testPauseButton(){


        Button pause = lookup("#pauseButton").queryButton();

        Rectangle cell1 = lookup("#cell1,0").query();
        Rectangle cell2 = lookup("#cell0,1").query();
        Assertions.assertEquals(Color.BLUE, cell1.getFill());
        Assertions.assertEquals(Color.RED, cell2.getFill());


        javafxRun(() -> myDisplay.nextGen());
        Assertions.assertEquals(Color.RED, cell1.getFill());
        Assertions.assertEquals(Color.BLUE, cell2.getFill());

        pause.fire();

        javafxRun(() -> myDisplay.step(myDisplay.getAnimationSpeed()));
        Assertions.assertEquals(Color.RED, cell1.getFill());
        Assertions.assertEquals(Color.BLUE, cell2.getFill());

    }

    @Test
    public void testStepButton(){

        Button stepButton = lookup("#stepButton").queryButton();

        Rectangle cell1 = lookup("#cell1,0").query();
        Rectangle cell2 = lookup("#cell0,1").query();
        Assertions.assertEquals(Color.BLUE, cell1.getFill());
        Assertions.assertEquals(Color.RED, cell2.getFill());

        stepButton.fire();
        Assertions.assertEquals(Color.RED, cell1.getFill());
        Assertions.assertEquals(Color.BLUE, cell2.getFill());

    }


}
