package com.example.appgui.packetStructure;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SvPacket {
    private String macDst;

    private String macSrs;

    private String type;

    private String timestamp;

    private SampledValues smpValues = new SampledValues();


}

