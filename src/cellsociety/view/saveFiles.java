package cellsociety.view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class saveFiles {

    public void saveState(String[][] currentState) throws IOException {
        int directoryLength = new File("resources/Game_CSVs/").list().length;

        FileWriter csvWriter = new FileWriter("resources/Game_CSVs/new"+ directoryLength +".csv");
        makeHeader(csvWriter,currentState);
        writeRows(csvWriter,currentState);
        csvWriter.flush();
        csvWriter.close();

    }

    private void makeHeader(FileWriter csvWriter, String[][] currentState) throws IOException {
        csvWriter.append(Integer.toString(currentState.length));
        csvWriter.append(",");
        csvWriter.append(Integer.toString(currentState[0].length));
        csvWriter.append("\n");
    }

    private void writeRows(FileWriter csvWriter, String[][] currentState) throws IOException {
        for(int row =0; row< currentState.length; row++){
            for(int column = 0; column <currentState[row].length; column++){
                if (column < currentState[row].length-1) {
                    csvWriter.append(currentState[row][column] + ",");
                } else {
                    csvWriter.append(currentState[row][column]);
                }
            }
            csvWriter.append("\n");
        }

    }


}
