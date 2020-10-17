package cellsociety.view;

import java.util.Properties;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;


public interface CellView{
  void updateView(String state, Properties properties);
  Node getCell();
}
