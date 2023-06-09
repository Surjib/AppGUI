package com.example.appgui;

import com.example.appgui.logicalnodes.measurements.MMXU;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class GraphController {

    @FXML
    MMXU sourceMapReceived;
    @FXML
    LineChart graph;


    public void displayGraph (MMXU data){
//        sourceMapReceived = data;
//
//
//        NumberAxis x = new NumberAxis();
//        NumberAxis y = new NumberAxis();
//
//        AreaChart<Number, Number> numberLineChart = new AreaChart<Number, Number>(x,y);
//        numberLineChart.setTitle("Series");
//        XYChart.Series series1 = new XYChart.Series();
//
//
//        series1.setName("sin(x)");
//        ObservableList<XYChart.Data> data1 = FXCollections.observableArrayList();
//
//        int i = 0;
//        for (double[] element:data.IphsA) {
//            data1.add(new XYChart.Data(i,element[0]));
//            i+=1;
//        }
//
//
//        series1.setData(data1);
//
//        graph.getData().add(series1);




//    @Override
//    public void initialize(URL location, ResourceBundle resources) {


    }
}
