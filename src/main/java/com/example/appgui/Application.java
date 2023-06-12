package com.example.appgui;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;


public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("SV_scanner.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(getClass().getResource("list _styling.css").toExternalForm());

        Image icon = new Image("D:\\DZ\\10sem\\Algorythmy RZA\\KP\\AppGUI\\src\\main\\resources\\com\\example\\appgui\\logo11.png");
        stage.setTitle("SV traffic app");
        stage.setResizable(false);
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();



    }


    public static void main(String[] args) {
        launch(args);
    }
}