package com.example.appgui;

import com.example.appgui.packetStructure.SvPacket;
import com.example.appgui.pcapFiles.EthernetListener;
import com.example.appgui.pcapFiles.SvParser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.reflections.Reflections.log;


public class AppController implements Initializable {

    EthernetListener ethernetListener = new EthernetListener();

    HashMap<String, ArrayList<SvPacket>> sourceMap = new HashMap<>();

    ArrayList<Optional> array = new ArrayList<>();

    Set<SvPacket> setSvPckt = new HashSet<>();

    Set<String> srcMacID = new HashSet<>();


    private String currentNic = "empty";

    @FXML
    private ListView leftWindow;

    @FXML
    private ListView centerWindow;

    @FXML
    private AnchorPane rightWindow;


    @FXML
    private ComboBox<String> NicSelector;


    public void startButton(ActionEvent event){
//        if (ethernetListener.getHandle() == null){
//            ethernetListener.initializeNetworkInterface();
//        }
        ethernetListener.start();
    }


    public void stopButton(ActionEvent event){;
        ethernetListener.stop();
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


        ethernetListener.addListener(packet -> {
            Optional<SvPacket> svPacket = svParser.decode(packet);
            int noASDU = svPacket.get().getSmpValues().getApdu().getNoASDU();
            for (int i = 0; i < noASDU; i++) {


                if (svPacket.isPresent() && curCnt.get() != svPacket.get().getSmpValues().getApdu().getSeqASDU().get(i).getSmpCnt()) {

//                    System.out.println("=============================");
//                    System.out.println(svPacket.get().getMacDst());
//                    System.out.println(svPacket.get().getMacSrs());
//                    System.out.println(svPacket.get().getType());
//
//                    System.out.println("----------------------");
//                    System.out.println(svPacket.get().getSmpValues().getAppId());
//                    System.out.println(svPacket.get().getSmpValues().getLength());
//                    System.out.println(svPacket.get().getSmpValues().getReserved1());
//                    System.out.println(svPacket.get().getSmpValues().getReserved2());
//
//                    System.out.println("----------------------");
//                    System.out.println(svPacket.get().getSmpValues().getApdu().getNoASDU());
//                    System.out.println(svPacket.get().getSmpValues().getApdu().getSeqASDU().get(i).getSvID());
//                    System.out.println(svPacket.get().getSmpValues().getApdu().getSeqASDU().get(i).getSmpCnt());
//                    System.out.println(svPacket.get().getSmpValues().getApdu().getSeqASDU().get(i).getConfRev());
//                    System.out.println(svPacket.get().getSmpValues().getApdu().getSeqASDU().get(i).getSmpSynch());
//
//                    System.out.println("----------------------");
//                    System.out.println(svPacket.get().getSmpValues().getApdu().getSeqASDU().get(i).getDataset().getInstIa());
//                    System.out.println(svPacket.get().getSmpValues().getApdu().getSeqASDU().get(i).getDataset().getQIa());
//
//                    System.out.println(svPacket.get().getSmpValues().getApdu().getSeqASDU().get(i).getDataset().getInstIb());
//                    System.out.println(svPacket.get().getSmpValues().getApdu().getSeqASDU().get(i).getDataset().getQIb());
//
//                    System.out.println(svPacket.get().getSmpValues().getApdu().getSeqASDU().get(i).getDataset().getInstIc());
//                    System.out.println(svPacket.get().getSmpValues().getApdu().getSeqASDU().get(i).getDataset().getQIc());
//
//                    System.out.println(svPacket.get().getSmpValues().getApdu().getSeqASDU().get(i).getDataset().getInstIn());
//                    System.out.println(svPacket.get().getSmpValues().getApdu().getSeqASDU().get(i).getDataset().getQIn());
//
//                    System.out.println(svPacket.get().getSmpValues().getApdu().getSeqASDU().get(i).getDataset().getInstUa());
//                    System.out.println(svPacket.get().getSmpValues().getApdu().getSeqASDU().get(i).getDataset().getQUa());
//
//                    System.out.println(svPacket.get().getSmpValues().getApdu().getSeqASDU().get(i).getDataset().getInstUb());
//                    System.out.println(svPacket.get().getSmpValues().getApdu().getSeqASDU().get(i).getDataset().getQUb());
//
//                    System.out.println(svPacket.get().getSmpValues().getApdu().getSeqASDU().get(i).getDataset().getInstUc());
//                    System.out.println(svPacket.get().getSmpValues().getApdu().getSeqASDU().get(i).getDataset().getQUc());
//
//                    System.out.println(svPacket.get().getSmpValues().getApdu().getSeqASDU().get(i).getDataset().getInstIn());
//                    System.out.println(svPacket.get().getSmpValues().getApdu().getSeqASDU().get(i).getDataset().getQUn());


//                    if (!sourceMap.containsKey(svPacket.get().getMacSrs())) {
//                        sourceMap.put(svPacket.get().getMacSrs(), new ArrayList<>(svPacket));
//                    }

                    setSvPckt.add(svPacket.get());
                    System.out.println(setSvPckt.size());

                    if (setSvPckt.size() % 4000 == 0){
                        //Start placing data into blocks and calculate RMS
                        for (SvPacket data : setSvPckt) {
                            String inSert = (
                                    "SrcMAC: " + data.getMacSrs() +
                                    " SV_ID: " + data.getSmpValues().getApdu().getSeqASDU().get(0).getSvID()
                            );
                            srcMacID.add(inSert);

                            String[] s = (inSert.split("SrcMAC: "));
                            String[] MacAndID = s[1].split(" SV_ID: ");

                            if (data.getMacSrs().equals(MacAndID[0]) &&
                                    data.getSmpValues().getApdu().getSeqASDU().get(0).getSvID().equals(MacAndID[1])){

                            }


                        }
                        for (String address : srcMacID) {
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