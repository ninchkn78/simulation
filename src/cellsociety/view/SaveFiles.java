package cellsociety.view;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class SaveFiles {

  private static final String SAVE_DIR = "resources/Game_CSVs/";
  private static final String EXTENSION = ".csv";
  private static final String COMMA_DELIM = ",";
  private static final String NEW_LINE = "\n";


  public void saveState(String[][] currentState, String Title) throws IOException {

    FileWriter csvWriter = new FileWriter(SAVE_DIR + Title + EXTENSION);
    makeHeader(csvWriter, currentState);
    writeRows(csvWriter, currentState);
    csvWriter.flush();
    csvWriter.close();
  }

  private void makeHeader(FileWriter csvWriter, String[][] currentState) throws IOException {
    System.out.println(Arrays.deepToString(currentState));
    csvWriter.append(Integer.toString(currentState.length));
    csvWriter.append(COMMA_DELIM);
    csvWriter.append(Integer.toString(currentState[0].length));
    csvWriter.append(NEW_LINE);
  }

  private void writeRows(FileWriter csvWriter, String[][] currentState) throws IOException {
    for (String[] strings : currentState) {
      for (int column = 0; column < strings.length; column++) {
        if (column < strings.length - 1) {
          csvWriter.append(strings[column]).append(COMMA_DELIM);
        } else {
          csvWriter.append(strings[column]);
        }
      }
      csvWriter.append(NEW_LINE);
    }
  }
}
