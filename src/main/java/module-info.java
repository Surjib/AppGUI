/**
 *
 */
module com.example.appgui {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    opens com.example.appgui to javafx.fxml;
    exports com.example.appgui;
}