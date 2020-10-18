package cellsociety.model;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;


public abstract class Reader {

  protected static InputStream getFileInputStream(String dataSource) {
    InputStream textFile = null;
    try {
      textFile = Objects.requireNonNull(SetStateReader.class.getClassLoader().getResource(dataSource))
          .openStream();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return textFile;
  }

  protected List<String[]> readFile(String fileName)  {
    List<String[]> fileData = null;
    InputStream dataStream = getFileInputStream(fileName);
    try (CSVReader csvReader = new CSVReader(new InputStreamReader(dataStream))) {
      fileData = csvReader.readAll();
      validateCSV(fileData);
    } catch(CsvException | IOException e){
      e.printStackTrace();
      return fileData;
    }
    return fileData;
  }

  private void validateCSV(List<String[]> list) throws CsvException {
    String[] dimensions = list.get(0);
    if (Integer.parseInt(dimensions[0]) != list.size() - 1 ||
        Integer.parseInt(dimensions[1]) != list.get(1).length){
      throw new CsvException("Invalid Dimensions");
    }
  }

  public abstract String[][] getStatesFromFile(String fileName );

}
