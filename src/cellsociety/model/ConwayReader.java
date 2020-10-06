package cellsociety.model;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class ConwayReader extends Reader {


  @Override
  public String[][] readFile(String fileName) {
    String[][] states = new String[5][6];
    InputStream data = getFileInputStream(fileName);
    try (CSVReader csvReader = new CSVReader(new InputStreamReader(data))) {
      List<String[]> list = csvReader.readAll();
      list.remove(0);
      String[][] dataArr = new String[list.size()][];
      return list.toArray(dataArr);
    } catch (IOException | CsvException e) {
      e.printStackTrace();
      return states;
    }
  }
}

