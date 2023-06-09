package com.example.appgui;

import com.example.appgui.logicalnodes.measurements.MMXU;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.security.spec.RSAOtherPrimeInfo;

public class GraphController {

    @FXML
    MMXU sourceMapReceived;
    @FXML
    LineChart<Number, Number> graph;


    public void displayGraphIA (MMXU data) {
        if ((data != null)) {
//            System.out.println(data.IphsA.get(0)[0]); // data received

            XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
            int i = 0;
            for (double[] element : data.IphsA) {
                series.getData().add(new XYChart.Data<>(i, element[0]));

                i += 1;
            }
            graph.getData().add(series);
        }
    }
    public void displayGraphIB (MMXU data) {
        if ((data != null)) {
//            System.out.println(data.IphsB.get(0)[0]); // data received

            XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
            int i = 0;
            for (double[] element : data.IphsB) {
                series.getData().add(new XYChart.Data<>(i, element[0]));

                i += 1;
            }
            graph.getData().add(series);
        }
    }

    public void displayGraphIC (MMXU data) {
        if ((data != null)) {

            XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
            int i = 0;
            for (double[] element : data.IphsC) {
                series.getData().add(new XYChart.Data<>(i, element[0]));

                i += 1;
            }
            graph.getData().add(series);
        }
    }

    public void displayGraphUA (MMXU data) {
        if ((data != null)) {

            XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
            int i = 0;
            for (double[] element : data.UphsC) {
                series.getData().add(new XYChart.Data<>(i, element[0]));

                i += 1;
            }
            graph.getData().add(series);
        }
    }


    public void displayGraphUB (MMXU data) {
        if ((data != null)) {

            XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
            int i = 0;
            for (double[] element : data.UphsB) {
                series.getData().add(new XYChart.Data<>(i, element[0]));

                i += 1;
            }
            graph.getData().add(series);
        }
    }

    public void displayGraphUC (MMXU data) {
        if ((data != null)) {

            XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
            int i = 0;
            for (double[] element : data.UphsC) {
                series.getData().add(new XYChart.Data<>(i, element[0]));

                i += 1;
            }
            graph.getData().add(series);
        }
    }
}
