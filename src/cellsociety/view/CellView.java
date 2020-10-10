package cellsociety.view;

import java.io.IOException;
import java.util.Properties;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CellView extends Rectangle {


  public CellView(double width, double height){
    super(width, height);
    this.setStroke(Color.BLACK);

  }

  public void setColor(String state, String propertiesFileName)  {
    try {
      Properties prop = new Properties();
      prop.load(CellView.class.getClassLoader().getResourceAsStream(propertiesFileName));
      // TODO: 2020-10-10 better error handling 
      if (prop.getProperty(state) == null){
        this.setFill(Color.BLACK);
      }
      else {
        this.setFill(Color.web(prop.getProperty(state)));
      }
    } catch (IOException e) {
      // TODO: 2020-10-10 handle this (property file does not exist)
    }
  }
}

