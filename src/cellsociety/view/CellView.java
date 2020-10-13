package cellsociety.view;

import java.io.IOException;
import java.util.Properties;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CellView extends Rectangle {


  public CellView(double width, double height, String state, Properties properties){
    super(width, height);
    this.setStroke(Color.BLACK);
    this.setColor(state, properties);

  }

  public void setColor(String state, Properties properties)  {
      // TODO: 2020-10-10 better error handling
      if (properties.getProperty(state) == null){
        this.setFill(Color.WHITE);
      }
      else {
        this.setFill(Color.web(properties.getProperty(state)));
      }
    }
  }

