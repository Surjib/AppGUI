package com.example.appgui.pcapFiles;

import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.pcap4j.core.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
public class EthernetListener {


    @Getter @Setter
    private String nickName;

    @Getter @Setter
    private ArrayList<String> nicArray = new ArrayList<>();
    @Getter @Setter
    private PcapHandle handle;

    final List <PacketListener> listeners = new CopyOnWriteArrayList<>();

    private PacketListener defaultPacketListener = packet -> {
       listeners.forEach(listener -> listener.gotPacket(packet));
    };

    public void checkNic(){
        try {
            for (PcapNetworkInterface nic : Pcaps.findAllDevs()) {
                nicArray.add(nic.getDescription());
            }
        } catch (PcapNativeException e) {
            throw new RuntimeException(e);
        }
    }
    @SneakyThrows
    public void start(){
        if (nickName != null && !nickName.isEmpty()){
            //                initializeNetworkInterface();
                if (handle != null) {
                    String filter = "ether proto 0x88ba";  //  proto 0x88ba = IEC SV

                    handle.setFilter(filter, BpfProgram.BpfCompileMode.OPTIMIZE);

                    Thread captureThread = new Thread(() -> {
                        try {
                            log.info("Starting Packet Capturing");
                            handle.loop(0, defaultPacketListener); //packetCount should be x2. 0 for unlimited capture
                        } catch (PcapNativeException e) {
                            log.info("Finished Packet Capturing");
                        } catch (InterruptedException e) {
                            log.info("Finished Packet Capturing");
                        } catch (NotOpenException e) {
                            log.info("Finished Packet Capturing");
                        }


                    });

                    captureThread.start();
                }
        } else log.error("Select NIC first");
    }

    @SneakyThrows
    public void stop(){
        if (nickName != null && !nickName.isEmpty()) {
            if (handle != null) {
                handle.breakLoop();
//                handle.close();
                log.info("Packet Capturing Stopped");
            }else {
                log.error("Packet Capturing ALREADY Stopped");
            }
        } else log.error("Select NIC first");
    }

    @SneakyThrows
    public void initializeNetworkInterface() {
        Optional<PcapNetworkInterface> nic = Pcaps.findAllDevs().stream()
                .filter(i -> nickName.equals(i.getDescription()))
                .findFirst();

        if (nic.isPresent()) {
            handle = nic.get().openLive(1500, PcapNetworkInterface.PromiscuousMode.PROMISCUOUS, 10);
            log.info("Network handler: {}", nic);
        }
        else {
            log.error("Network Interface not found");
        }
    }

    public void addListener(PacketListener listener) {
        listeners.add(listener);
    }
}
