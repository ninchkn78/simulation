package cellsociety.view;

import cellsociety.model.GameBoard;
import cellsociety.model.games.Simulation;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.Chart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class StateGraph {
  private LineChart<Number, Number> myChart;
  private XYChart.Series series0;
  private XYChart.Series series1;
  private XYChart.Series series2;
  private Simulation mySimulation;
  private List<Integer> cellCounts;
  private List<XYChart.Series> seriesList;
  LineChart<Number,Number> lineChart;
  private int count = 0;

  public StateGraph(Group root, Simulation simulation) {
    mySimulation = simulation;
    myChart = generateGraph(root);
    root.getChildren().add(myChart);
  }

  public void updateGraph(){
    for(int dex =0; dex<cellCounts.size(); dex++){
      XYChart.Series<Number, Number> currentSeries= new XYChart.Series<>();
      currentSeries.setName("State "+dex);
      currentSeries.getData().add(new Data<>(count, cellCounts.get(dex)));
      seriesList.add(currentSeries);
    }

    for(XYChart.Series line: seriesList){
      lineChart.getData().addAll(line);
    }

  }

  public LineChart<Number, Number> generateGraph(Group root) {
    final NumberAxis xAxis = new NumberAxis();
    final NumberAxis yAxis = new NumberAxis();

    xAxis.setLabel("Time");

    lineChart = new LineChart<Number,Number>(xAxis,yAxis);
    lineChart.setLayoutX(75);
    lineChart.setLayoutY(75);
    lineChart.setTitle("State Graph");


    cellCounts = mySimulation.getGraphCounts();
    seriesList = new ArrayList<>();
    updateGraph();


    return lineChart;


  }





}
