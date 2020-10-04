package cellsociety.view;

import java.util.Map;
import javafx.scene.paint.Color;


public abstract class SimulationBoard {


  private Map<String, Color> stateColorMap;

  protected abstract void setStateColorMap();

  public Color chooseColor(String state) {
    return stateColorMap.get(state);
  }


}
