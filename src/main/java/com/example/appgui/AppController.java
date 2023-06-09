package com.example.appgui;

import com.example.appgui.logicalnodes.measurements.MMXU;
import com.example.appgui.packetStructure.SvPacket;
import com.example.appgui.pcapFiles.EthernetListener;
import com.example.appgui.pcapFiles.SvParser;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.reflections.Reflections.log;


public class AppController implements Initializable {

    EthernetListener ethernetListener = new EthernetListener();

    HashMap<String, MMXU> sourceMap = new LinkedHashMap<>();


    Set<SvPacket> allCapturedPackets = new LinkedHashSet<>();


    String selectedSource;


    private String currentNic = "empty";

    @FXML
    private ListView leftWindow;

    @FXML
    private ListView centerWindow;

    @FXML
    private AnchorPane rightWindow;




    @FXML
    private ComboBox<String> NicSelector;

    @FXML
    private Button StartButton;

    @FXML
    private Button StopButton;

    @FXML
    private Button clearButton;

    @FXML
    private Circle indicator;



    @FXML
    private Button Ia;

    @FXML
    private Button Ib;

    @FXML
    private Button Ic;

    @FXML
    private Button Ua;

    @FXML
    private Button Ub;

    @FXML
    private Button Uc;

    @FXML
    private Label appID;
    @FXML
    private Label dstMAC;
    @FXML
    private Label smpCnt;

    @FXML
    private Label srcMAC;

    @FXML
    private Label svID;

    Alert alert = new Alert(Alert.AlertType.WARNING);



    public void startButton(ActionEvent event){
//        if (ethernetListener.getHandle() == null){
//            ethernetListener.initializeNetworkInterface();
//        }
        if (currentNic.equals("empty")){
            alert.setTitle("Incorrect order");
            alert.setContentText("Select NIC");
            alert.show();
        }else ethernetListener.start();
    }


    public void stopButton(ActionEvent event){;
        if (currentNic.equals("empty")){
            alert.setTitle("Incorrect order");
            alert.setContentText("Select NIC");
            alert.show();
        }else ethernetListener.stop();
    }

    public void clearButton(ActionEvent event){;
        centerWindow.getItems().clear();
        srcMAC.setText("");
        dstMAC.setText("");
        appID.setText("");
        svID.setText("");
        smpCnt.setText("");
        Ia.setText("");
        Ib.setText("");
        Ic.setText("");
        Ua.setText("");
        Ub.setText("");
        Uc.setText("");
        allCapturedPackets.clear();
    }

    public void openGraphsWindowIA(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("graphWindow.fxml"));
        Parent root = loader.load();


        GraphController graphController = loader.getController();
        graphController.displayGraphIA(sourceMap.get(selectedSource));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Ia");
        stage.setScene(scene);
        stage.show();
    }

    public void openGraphsWindowIB(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("graphWindow.fxml"));
        Parent root = loader.load();


        GraphController graphController = loader.getController();
        graphController.displayGraphIB(sourceMap.get(selectedSource));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Ib");
        stage.setScene(scene);
        stage.show();
    }

    public void openGraphsWindowIC(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("graphWindow.fxml"));
        Parent root = loader.load();


        GraphController graphController = loader.getController();
        graphController.displayGraphIC(sourceMap.get(selectedSource));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Ic");
        stage.setScene(scene);
        stage.show();
    }

    public void openGraphsWindowUA(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("graphWindow.fxml"));
        Parent root = loader.load();


        GraphController graphController = loader.getController();
        graphController.displayGraphUA(sourceMap.get(selectedSource));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Ua");
        stage.setScene(scene);
        stage.show();
    }

    public void openGraphsWindowUB(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("graphWindow.fxml"));
        Parent root = loader.load();


        GraphController graphController = loader.getController();
        graphController.displayGraphUB(sourceMap.get(selectedSource));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Ub");
        stage.setScene(scene);
        stage.show();
    }

    public void openGraphsWindowUC(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("graphWindow.fxml"));
        Parent root = loader.load();


        GraphController graphController = loader.getController();
        graphController.displayGraphUC(sourceMap.get(selectedSource));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Uc");
        stage.setScene(scene);
        stage.show();
    }

    //вывод назавания карты в другой метод
    public void setNic(ActionEvent event){
        String nic = NicSelector.getValue();
        ethernetListener.setNickName(nic);
        if (!nic.equals(currentNic)) {
            ethernetListener.initializeNetworkInterface();
        }
        log.info("Active NIC: {}", nic);
        currentNic = nic;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ethernetListener.checkNic();
        ethernetListener.getNicArray();

        NicSelector.getItems().addAll(ethernetListener.getNicArray());
        NicSelector.setOnAction(this::setNic);


        SvParser svParser = new SvParser();

        AtomicInteger curCnt = new AtomicInteger();


        leftWindow.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                try {
                    selectedSource = leftWindow.getSelectionModel().getSelectedItem().toString();

                    for (SvPacket packet : sourceMap.get(selectedSource).getSvPackets()) {
                        centerWindow.getItems().add("Packet " + ((int) sourceMap.get(selectedSource).getSvPackets().indexOf(packet) + 1));
                    }

                } catch (NullPointerException e) {
                }
            }
        });

        centerWindow.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                try {
                    String selectedPacket = centerWindow.getSelectionModel().getSelectedItem().toString();

                    String[] s = (selectedPacket.split("Packet "));
                    int packetNumber = (Integer.parseInt(s[1]) - 1);

                    srcMAC.setText(sourceMap.get(selectedSource).getSvPackets().get(packetNumber).getMacSrs());
                    srcMAC.setAlignment(Pos.CENTER);
                    dstMAC.setText(sourceMap.get(selectedSource).getSvPackets().get(packetNumber).getMacDst());
                    dstMAC.setAlignment(Pos.CENTER);
                    appID.setText(sourceMap.get(selectedSource).getSvPackets().get(packetNumber).getSmpValues().getAppId());
                    appID.setAlignment(Pos.CENTER);
                    svID.setText(sourceMap.get(selectedSource).getSvPackets().get(packetNumber).getSmpValues().getApdu().getSeqASDU().get(0).getSvID());
                    svID.setAlignment(Pos.CENTER);
                    smpCnt.setText(String.valueOf(sourceMap.get(selectedSource).getSvPackets().get(packetNumber).getSmpValues().getApdu().getSeqASDU().get(0).getSmpCnt()));
                    smpCnt.setAlignment(Pos.CENTER);
//                    Ia.setText(String.valueOf(sourceMap.get(selectedSource).getSvPackets().get(packetNumber).getSmpValues().getApdu().getSeqASDU().get(0).getDataset().getInstIa() / 100d));
//                    Ia.setAlignment(Pos.CENTER);
//                    Ib.setText(String.valueOf(sourceMap.get(selectedSource).getSvPackets().get(packetNumber).getSmpValues().getApdu().getSeqASDU().get(0).getDataset().getInstIb() / 100d));
//                    Ib.setAlignment(Pos.CENTER);
//                    Ic.setText(String.valueOf(sourceMap.get(selectedSource).getSvPackets().get(packetNumber).getSmpValues().getApdu().getSeqASDU().get(0).getDataset().getInstIc() / 100d));
//                    Ic.setAlignment(Pos.CENTER);
//                    Ua.setText(String.valueOf(sourceMap.get(selectedSource).getSvPackets().get(packetNumber).getSmpValues().getApdu().getSeqASDU().get(0).getDataset().getInstUa() / 100d));
//                    Ua.setAlignment(Pos.CENTER);
//                    Ub.setText(String.valueOf(sourceMap.get(selectedSource).getSvPackets().get(packetNumber).getSmpValues().getApdu().getSeqASDU().get(0).getDataset().getInstUb() / 100d));
//                    Ub.setAlignment(Pos.CENTER);
//                    Uc.setText(String.valueOf(sourceMap.get(selectedSource).getSvPackets().get(packetNumber).getSmpValues().getApdu().getSeqASDU().get(0).getDataset().getInstUc() / 100d));
//                    Uc.setAlignment(Pos.CENTER);

                    Ia.setText(String.valueOf(sourceMap.get(selectedSource).IphsA.get(packetNumber)[0] + "∠" +sourceMap.get(selectedSource).IphsA.get(packetNumber)[1]));
                    Ia.setAlignment(Pos.CENTER);
                    Ib.setText(String.valueOf(sourceMap.get(selectedSource).IphsB.get(packetNumber)[0] + "∠" +sourceMap.get(selectedSource).IphsB.get(packetNumber)[1]));
                    Ib.setAlignment(Pos.CENTER);
                    Ic.setText(String.valueOf(sourceMap.get(selectedSource).IphsC.get(packetNumber)[0] + "∠" +sourceMap.get(selectedSource).IphsC.get(packetNumber)[1]));
                    Ic.setAlignment(Pos.CENTER);
                    Ua.setText(String.valueOf(sourceMap.get(selectedSource).UphsA.get(packetNumber)[0] + "∠" +sourceMap.get(selectedSource).UphsA.get(packetNumber)[1]));
                    Ua.setAlignment(Pos.CENTER);
                    Ub.setText(String.valueOf(sourceMap.get(selectedSource).UphsB.get(packetNumber)[0] + "∠" +sourceMap.get(selectedSource).UphsB.get(packetNumber)[1]));
                    Ub.setAlignment(Pos.CENTER);
                    Uc.setText(String.valueOf(sourceMap.get(selectedSource).UphsC.get(packetNumber)[0] + "∠" +sourceMap.get(selectedSource).UphsC.get(packetNumber)[1]));
                    Uc.setAlignment(Pos.CENTER);




                } catch (NullPointerException e) {
                }
            }
        });


        ethernetListener.addListener(packet -> {
            Optional<SvPacket> svPacket = svParser.decode(packet);
            int noASDU = svPacket.get().getSmpValues().getApdu().getNoASDU();
            for (int i = 0; i < noASDU; i++) {


                if (svPacket.isPresent() && curCnt.get() != svPacket.get().getSmpValues().getApdu().getSeqASDU().get(i).getSmpCnt()) {

                    allCapturedPackets.add(svPacket.get());
                    System.out.println(allCapturedPackets.size());

                    if (allCapturedPackets.size() % 4000 == 0){


                        //Start placing data into blocks and calculate RMS
                        for (SvPacket data : allCapturedPackets) {
                            String inSert = (
                                    "SrcMAC: " + data.getMacSrs() +
                                    " SV_ID: " + data.getSmpValues().getApdu().getSeqASDU().get(0).getSvID()
                            );

                            if (!sourceMap.containsKey(inSert)){
                                sourceMap.put(inSert, new MMXU());
                            }


                            String[] s = (inSert.split("SrcMAC: "));
                            String[] MacAndID = s[1].split(" SV_ID: ");

                            if (data.getMacSrs().equals(MacAndID[0]) &&
                                    data.getSmpValues().getApdu().getSeqASDU().get(0).getSvID().equals(MacAndID[1]) &&
                                    !sourceMap.get(inSert).getSvPackets().contains(data)){
                                sourceMap.get(inSert).getSvPackets().add(data);
                                sourceMap.get(inSert).process(data.getSmpValues().getApdu().getSeqASDU().get(0).getDataset());
                            }
                        }
                        for (String address : sourceMap.keySet()) {
                            if (!leftWindow.getItems().contains(address)){
                                leftWindow.getItems().add(address);
                            }
                        }

                    }



                    curCnt.set(svPacket.get().getSmpValues().getApdu().getSeqASDU().get(i).getSmpCnt());  //update counter else writes packet twice
                }
            }
        });
    }


}