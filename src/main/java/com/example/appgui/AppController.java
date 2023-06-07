package com.example.appgui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class AppController implements Initializable {

    @FXML
    private Label selectorLabel;

    @FXML
    private ChoiceBox<String> NicSelector;

    private String[] nicAvailable = {"one", "two", "three"};

    public void startButton(ActionEvent event){
        System.out.println("start");
    }


    public void stopButton(ActionEvent event){
        System.out.println("stop");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        NicSelector.getItems().addAll(nicAvailable);
        NicSelector.setOnAction(this::setNic);

    }

    //вывод назавания карты в другой метод
    public void setNic(ActionEvent event){
        String food = NicSelector.getValue();
        System.out.println(food);
    }
}