package cellsociety.view;

import java.io.FileInputStream;
import java.util.Properties;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ImageCellView extends Group implements CellView {

  String state;
  ImageView cellImage;

  public ImageCellView(double width, double height, String state, Properties properties) {
    cellImage = new ImageView();
    cellImage.setFitWidth(width);
    cellImage.setFitHeight(height);
    setState(state);
    this.getChildren().add(cellImage);
    updateView(state, properties);
    addEventListener();
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public void updateView(String state, Properties properties) {
    FileInputStream inputstream = null;
    try {
      // TODO: 2020-10-13 if state doesn't exist
      inputstream = new FileInputStream(properties.getProperty(state + "image"));
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (inputstream != null) {
      Image image = new Image(inputstream);
      cellImage.setImage(image);
    }
  }

  @Override
  public Node getCell() {
    return cellImage;
  }

  private void addEventListener() {
    EventHandler<MouseEvent> eventHandler = e -> System.out.println("Hello World");
    //Registering the event filter
    cellImage.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
  }


}

