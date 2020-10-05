package cellsociety.view;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

class ButtonSetupTest extends DukeApplicationTest {
    Scene myScene;
    private final ConwayDisplay conwayDisplay = new ConwayDisplay();


    @Override
    public void start(Stage stage) {
        // create game's scene with all shapes in their initial positions and show it
        myScene = conwayDisplay.setupScene();
        stage.setScene(myScene);
        stage.show();
        // find individual ite
    }

    @Test
    public void testButtonPlacement(){

    }
}