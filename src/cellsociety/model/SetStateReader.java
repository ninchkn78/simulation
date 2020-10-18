package cellsociety.model;

import java.util.List;

public class SetStateReader extends Reader {

  @Override
  public String[][] getStatesFromFile(String fileName) {
    List<String[]> list = readFile(fileName);
    String[] dimensions = list.remove(0);
    //TODO: add validateStates method, validateDimensions
    String[][] dataArray = new String[list.size()][];
    return list.toArray(dataArray);
  }
}

