/**
 *
 */
module com.example.appgui {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires lombok;

    requires java.xml.bind;
    requires org.reflections;
    requires slf4j.api;
    requires org.pcap4j.core;

    opens com.example.appgui to javafx.fxml;
    exports com.example.appgui;
    exports com.example.appgui.common;
    opens com.example.appgui.common to javafx.fxml;
    exports com.example.appgui.filters;
    opens com.example.appgui.filters to javafx.fxml;
}