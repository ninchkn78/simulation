package cellsociety.view;

import java.io.FileWriter;
import java.io.IOException;

public class saveFiles {

    public void saveState(String[][] currentState) throws IOException {
        FileWriter csvWriter = new FileWriter("resources/Game_CSVs/new.csv");
        makeHeader(csvWriter,currentState);



    }

    private void makeHeader(FileWriter csvWriter, String[][] currentState) throws IOException {
        csvWriter.append(Integer.toString(currentState.length));
        csvWriter.append(",");
        csvWriter.append(Integer.toString(currentState[0].length));
        csvWriter.append("\n");
    }



}
