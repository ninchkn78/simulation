package cellsociety.view;

import java.io.FileWriter;
import java.io.IOException;

public class SaveFiles {

  private static final String SAVE_DIR = "resources/Game_CSVs/";
  private static final String NEW = "new";
  private static final String EXTENSION = ".csv";
  private static final String COMMA_DELIM = ",";
  private static final String NEW_LINE = "\n";


  public void saveState(String[][] currentState, String Title) throws IOException {
    //int directoryLength = new File(SAVE_DIR).list().length;

    FileWriter csvWriter = new FileWriter(SAVE_DIR + Title + EXTENSION);
    makeHeader(csvWriter, currentState);
    writeRows(csvWriter, currentState);
    csvWriter.flush();
    csvWriter.close();
  }

  private void makeHeader(FileWriter csvWriter, String[][] currentState) throws IOException {
    System.out.println(currentState);
    csvWriter.append(Integer.toString(currentState.length));
    csvWriter.append(COMMA_DELIM);
    csvWriter.append(Integer.toString(currentState[0].length));
    csvWriter.append(NEW_LINE);
  }

  private void writeRows(FileWriter csvWriter, String[][] currentState) throws IOException {
    for (int row = 0; row < currentState.length; row++) {
      for (int column = 0; column < currentState[row].length; column++) {
        if (column < currentState[row].length - 1) {
          csvWriter.append(currentState[row][column] + COMMA_DELIM);
        } else {
          csvWriter.append(currentState[row][column]);
        }
      }
      csvWriter.append(NEW_LINE);
    }
  }
}
