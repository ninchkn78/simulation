package cellsociety.view;

import java.util.Properties;
import javafx.scene.Node;

/**
 * An interface for views of a cell that are displayed in the program
 * @author alexc
 */
public interface CellView {

  /**
   * updates the cell to the given state
   * @param state
   * @param properties the Properties object from the current simulation that is being run
   */
  void updateView(String state, Properties properties);

  void handleClick();

  Node getCell();
}
