package cellsociety.controller;

import static org.junit.jupiter.api.Assertions.*;
import exceptions.InvalidPropertiesFileException;
import org.junit.jupiter.api.Test;

class ControllerTest {
  @Test
  void missingPropertiesTest(){
    assertThrows(InvalidPropertiesFileException.class,() -> new Controller("MissingProperty.properties"));
  }

}