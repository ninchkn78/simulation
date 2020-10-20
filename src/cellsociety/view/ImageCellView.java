package cellsociety.view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.apache.commons.lang3.ObjectUtils.Null;

public class ImageCellView extends Group implements CellView {

  ImageView cellImage;

  public ImageCellView(double width, double height, String state, Properties properties) {
    cellImage = new ImageView();
    cellImage.setFitWidth(width);
    cellImage.setFitHeight(height);
    this.getChildren().add(cellImage);
    updateView(state, properties);
    addEventListener();
  }

  public void updateView(String state, Properties properties) {
    FileInputStream inputstream = null;
    try {
      // TODO: 2020-10-13 if state doesn't exist
      inputstream = new FileInputStream(properties.getProperty(state + "image"));
    } catch (NullPointerException |  FileNotFoundException e) {
      try {
        inputstream = new FileInputStream("resources/default.png");
      } catch (FileNotFoundException fileNotFoundException) {
      }
    }
    Image image = new Image(inputstream);
    cellImage.setImage(image);
  }

  @Override
  public Node getCell() {
    return cellImage;
  }

  private void addEventListener() {
    EventHandler<MouseEvent> eventHandler = e -> System.out.println("Hello World");
    cellImage.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
  }
}

