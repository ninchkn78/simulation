package cellsociety.view;

import java.util.Properties;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class RectangleCellView extends Group implements CellView {

  String state;
  Rectangle rectangle;


  public RectangleCellView(double width, double height, String state, Properties properties) {
    rectangle = new Rectangle(width, height);
    setState(state);
    this.getChildren().add(rectangle);
    rectangle.setStroke(Color.BLACK);
    updateView(state, properties);
    addEventListener();
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public double getWidth() {
    return rectangle.getWidth();
  }

  public double getHeight() {
    return rectangle.getHeight();
  }

  public Paint getFill() {
    return rectangle.getFill();
  }

  public void setFill(Paint color) {
    rectangle.setFill(color);
  }

  public void updateView(String state, Properties properties) {
    // TODO: 2020-10-10 better error handling
    if (properties.getProperty(state) == null) {
      rectangle.setFill(Color.WHITE);
    } else {
      rectangle.setFill(Color.web(properties.getProperty(state)));
    }
  }

  @Override
  public Node getCell() {
    return rectangle;
  }

  private void addEventListener() {
    EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {

        System.out.println("Hello World");
        rectangle.setFill(Color.DARKSLATEBLUE);
      }
    };
    //Registering the event filter
    rectangle.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
  }


}

