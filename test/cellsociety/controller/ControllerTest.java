package cellsociety.controller;

import static org.junit.jupiter.api.Assertions.*;
import exceptions.InvalidPropertiesFileException;
import java.lang.reflect.InvocationTargetException;
import org.junit.jupiter.api.Test;

class ControllerTest {
  @Test
  void missingPropertiesTest(){
    assertThrows(InvalidPropertiesFileException.class,() -> new Controller("MissingProperty.properties"));
  }

  @Test
  void notAPropertiesFileTest(){
    assertThrows(InvalidPropertiesFileException.class,() -> new Controller("default.png"));
  }

  @Test
  void invalidCSVDimensions(){
    assertThrows(InvocationTargetException.class,() -> new Controller("BadCSVConway.properties"));
  }

}