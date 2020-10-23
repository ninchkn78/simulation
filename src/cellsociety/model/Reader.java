package cellsociety.model;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import exceptions.InvalidCSVFormatException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

/**
 * Reads data from a CSV and writes it as a 2D array of states
 *
 * @author alexc
 */
public abstract class Reader {

  protected static InputStream getFileInputStream(String dataSource) {
    InputStream textFile = null;
    try {
      textFile = Objects
          .requireNonNull(SetStateReader.class.getClassLoader().getResource(dataSource))
          .openStream();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return textFile;
  }

  protected List<String[]> readFile(String fileName) {
    List<String[]> fileData = null;
    InputStream dataStream = getFileInputStream(fileName);
    try (CSVReader csvReader = new CSVReader(new InputStreamReader(dataStream))) {
      fileData = csvReader.readAll();
      //validateCSV(fileData);
    } catch (CsvException | IOException e) {
      e.printStackTrace();
      return fileData;
    }
    return fileData;
  }

  /**
   * Given the name of a CSV, gets a 2D array of states that are represented by the format of the
   * CSV.
   *
   * @param fileName
   * @return
   * @throws InvalidCSVFormatException for invalid states and dimensions
   */
  public abstract String[][] getStatesFromFile(String fileName) throws InvalidCSVFormatException;
}


