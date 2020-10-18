package cellsociety.model;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

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

