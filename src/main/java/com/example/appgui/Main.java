package com.example.appgui;


import com.example.appgui.packetStructure.SvPacket;
import com.example.appgui.pcapFiles.EthernetListener;
import com.example.appgui.pcapFiles.SvParser;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        EthernetListener ethernetListener = new EthernetListener();

        ethernetListener.checkNic();
        ethernetListener.setNickName("VMware Virtual Ethernet Adapter for VMnet1");
        ethernetListener.getNicArray();


        SvParser svParser = new SvParser();

        AtomicInteger curCnt = new AtomicInteger();

        HashMap<String, ArrayList<SvPacket>> sourceMap = new HashMap<>();

        ArrayList<Optional> array = new ArrayList<>();

        Set<Optional> setSvPckt = new HashSet<>();

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

                    setSvPckt.add(svPacket);
                    System.out.println(setSvPckt.size());

                    if (setSvPckt.size() >= 7000) {
                        System.out.println(setSvPckt);
                    }

                    curCnt.set(svPacket.get().getSmpValues().getApdu().getSeqASDU().get(i).getSmpCnt());  //update counter else writes packet twice
                }
            }

        });

        ethernetListener.start();

    }
}