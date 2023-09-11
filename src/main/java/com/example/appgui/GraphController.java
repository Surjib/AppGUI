package com.example.appgui;

import com.example.appgui.logicalnodes.measurements.MMXU;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.security.spec.RSAOtherPrimeInfo;
import java.util.ArrayList;

public class GraphController {

    @FXML
    MMXU sourceMapReceived;
    @FXML
    LineChart<Number, Number> graph;


    public void displayGraphIA (ArrayList<double[]> data) {
        if ((data != null)) {

            XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
            int i = 0;
            for (double[] element : data) {
                series.getData().add(new XYChart.Data<>(i, element[0]));

                i += 1;
            }
            series.setName("Ia");
            graph.getData().add(series);
            graph.setCreateSymbols(false);
        }
    }
    public void displayGraphIB (ArrayList<double[]> data) {
        if ((data != null)) {

            XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
            int i = 0;
            for (double[] element : data) {
                series.getData().add(new XYChart.Data<>(i, element[0]));

                i += 1;
            }
            series.setName("Ib");
            graph.getData().add(series);
            graph.setCreateSymbols(false);
        }
    }

    public void displayGraphIC (ArrayList<double[]> data) {
        if ((data != null)) {

            XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
            int i = 0;
            for (double[] element : data) {
                series.getData().add(new XYChart.Data<>(i, element[0]));
                i += 1;
            }

            series.setName("Ic");
            graph.getData().add(series);
            graph.setCreateSymbols(false);
        }
    }

    public void displayGraphUA (ArrayList<double[]> data) {
        if ((data != null)) {

            XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
            int i = 0;
            for (double[] element : data) {
                series.getData().add(new XYChart.Data<>(i, element[0]));

                i += 1;
            }
            series.setName("Ua");
            graph.getData().add(series);
            graph.setCreateSymbols(false);
        }
    }


    public void displayGraphUB (ArrayList<double[]> data) {
        if ((data != null)) {

            XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
            int i = 0;
            for (double[] element : data) {
                series.getData().add(new XYChart.Data<>(i, element[0]));

                i += 1;
            }
            series.setName("Ub");
            graph.getData().add(series);
            graph.setCreateSymbols(false);
        }
    }

    public void displayGraphUC (ArrayList<double[]> data) {
        if ((data != null)) {

            XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
            int i = 0;
            for (double[] element : data) {
                series.getData().add(new XYChart.Data<>(i, element[0]));

                i += 1;
            }
            series.setName("Uc");
            graph.getData().add(series);
            graph.setCreateSymbols(false);
        }
    }
}
